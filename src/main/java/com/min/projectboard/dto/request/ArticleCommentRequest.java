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

    private String content;

    protected ArticleCommentRequest(){}

    private ArticleCommentRequest(Long articleId, String content) {
        this.articleId = articleId;
        this.content = content;
    }

    public static ArticleCommentRequest of(Long articleId, String content){
        return new ArticleCommentRequest(articleId, content);
    }

    public ArticleCommentDto toDto(UserAccountDto userAccountDto){
        return ArticleCommentDto.of(
                articleId,
                userAccountDto,
                content
        );
    }


}
