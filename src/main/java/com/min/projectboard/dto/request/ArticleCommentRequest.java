package com.min.projectboard.dto.request;

import com.min.projectboard.domain.ArticleComment;
import com.min.projectboard.dto.ArticleCommentDto;
import com.min.projectboard.dto.UserAccountDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleCommentRequest {

    private Long articleId;

    private Long parentCommentId;

    private String content;

    protected ArticleCommentRequest(){}

    private ArticleCommentRequest(Long articleId, Long parentCommentId, String content) {
        this.articleId = articleId;
        this.parentCommentId = parentCommentId;
        this.content = content;
    }

    public static ArticleCommentRequest of(Long articleId, String content){
        return ArticleCommentRequest.of(articleId, null, content);
    }

    public static ArticleCommentRequest of(Long articleId, Long parentCommentId, String content){
        return new ArticleCommentRequest(articleId, parentCommentId, content);
    }

    public ArticleCommentDto toDto(UserAccountDto userAccountDto){
        return ArticleCommentDto.of(
                articleId,
                userAccountDto,
                parentCommentId,
                content
        );
    }


}
