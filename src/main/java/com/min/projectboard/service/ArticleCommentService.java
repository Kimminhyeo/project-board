package com.min.projectboard.service;

import com.min.projectboard.domain.Article;
import com.min.projectboard.domain.ArticleComment;
import com.min.projectboard.domain.UserAccount;
import com.min.projectboard.dto.ArticleCommentDto;
import com.min.projectboard.repository.ArticleCommentRepository;
import com.min.projectboard.repository.ArticleRepository;
import com.min.projectboard.repository.UserAccountRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class ArticleCommentService {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;
    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    public List<ArticleCommentDto> searchArticleComments(Long articleId) {
        return articleCommentRepository.findByArticle_Id(articleId)
                .stream()
                .map(ArticleCommentDto::from)
                .collect(Collectors.toList());
    }

    public void saveArticleComment(ArticleCommentDto dto){
        try{
            Article article = articleRepository.getReferenceById(dto.getArticleId());
            UserAccount userAccount = userAccountRepository.getReferenceById(dto.getUserAccountDto().getUserId());
            ArticleComment articleComment = dto.toEntity(article, userAccount);

            if(dto.getParentCommentId() != null){
                ArticleComment parentComment = articleCommentRepository.getReferenceById(dto.getParentCommentId());
                parentComment.addChildComment(articleComment);
            }else{
                articleCommentRepository.save(articleComment);

            }
        }catch (EntityNotFoundException e){
            log.warn("?????? ?????? ??????, ?????? ????????? ????????? ????????? ?????? ??? ???????????? - {}", e.getLocalizedMessage());
        }
    }

    @Deprecated
    public void updateArticleComment(ArticleCommentDto dto){
        try {
            ArticleComment articleComment = articleCommentRepository.getReferenceById(dto.getId());
            if (dto.getContent() != null) { articleComment.setContent(dto.getContent()); }
        } catch (EntityNotFoundException e) {
            log.warn("?????? ???????????? ??????. ????????? ?????? ??? ???????????? - dto: {}", dto);
        }
    }

    public void deleteArticleComment(Long articleCommentId, String userId){
        articleCommentRepository.deleteByIdAndUserAccount_UserId(articleCommentId, userId);
    }
}
