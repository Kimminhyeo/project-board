package com.min.projectboard.dto.request;

import com.min.projectboard.dto.ArticleDto;
import com.min.projectboard.dto.UserAccountDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleRequest {
    private String title;
    private String content;
    private String hashtag;

    protected ArticleRequest(){}

    private ArticleRequest(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static ArticleRequest of(String title,
                                    String content,
                                    String hashtag){
        return new ArticleRequest(title, content, hashtag);
    }

    public ArticleDto toDto(UserAccountDto userAccountDto){
        return ArticleDto.of(
                userAccountDto,
                title,
                content,
                hashtag
        );
    }
}
