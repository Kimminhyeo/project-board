package com.min.projectboard.dto.request;

import com.min.projectboard.dto.ArticleDto;
import com.min.projectboard.dto.HashtagDto;
import com.min.projectboard.dto.UserAccountDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ArticleRequest {
    private String title;
    private String content;

    protected ArticleRequest(){}

    private ArticleRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static ArticleRequest of(String title,
                                    String content
                                    ){
        return new ArticleRequest(title, content);
    }

    public ArticleDto toDto(UserAccountDto userAccountDto){
        return toDto(userAccountDto, null);
    }

    public ArticleDto toDto(UserAccountDto userAccountDto, Set<HashtagDto> hashtagDtos){
        return ArticleDto.of(
                userAccountDto,
                title,
                content,
                hashtagDtos
        );
    }
}
