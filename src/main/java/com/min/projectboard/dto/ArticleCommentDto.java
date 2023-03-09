package com.min.projectboard.dto;

import com.min.projectboard.domain.Article;
import com.min.projectboard.domain.ArticleComment;
import com.min.projectboard.domain.UserAccount;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleCommentDto {

    private Long id;
    private Long articleId;
    private UserAccountDto userAccountDto;
    Long parentCommentId;
    private String content;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    private ArticleCommentDto(Long id,
                              Long articleId,
                              UserAccountDto userAccountDto,
                              Long parentCommentId,
                              String content,
                              LocalDateTime createdAt,
                              String createdBy,
                              LocalDateTime modifiedAt,
                              String modifiedBy) {
        this.id = id;
        this.articleId = articleId;
        this.userAccountDto = userAccountDto;
        this.parentCommentId = parentCommentId;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
        this.content = content;

    }

    public static ArticleCommentDto of(
                                       Long articleId,
                                       UserAccountDto userAccountDto,
                                       String content
                                       ){
        return ArticleCommentDto.of(articleId, userAccountDto, null, content);
    }

    public static ArticleCommentDto of(
            Long articleId,
            UserAccountDto userAccountDto,
            Long parentCommentId,
            String content
    ){
        return ArticleCommentDto.of(null, articleId, userAccountDto, parentCommentId, content, null, null, null, null);
    }

    public static ArticleCommentDto of(Long id,
                                       Long articleId,
                                       UserAccountDto userAccountDto,
                                       Long parentCommentId,
                                       String content,
                                       LocalDateTime createdAt,
                                       String createdBy,
                                       LocalDateTime modifiedAt,
                                       String modifiedBy){
        return new ArticleCommentDto(id, articleId, userAccountDto, parentCommentId, content, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ArticleCommentDto from(ArticleComment entity){
        return new ArticleCommentDto(entity.getId(),
                entity.getArticle().getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getParentCommentId(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy());
    }

    public ArticleComment toEntity(Article article, UserAccount userAccount){
        return ArticleComment.of(
                article,
                userAccount,
                content
        );
    }
}
