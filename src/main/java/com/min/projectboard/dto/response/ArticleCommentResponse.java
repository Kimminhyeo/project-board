package com.min.projectboard.dto.response;

import com.min.projectboard.dto.ArticleCommentDto;
import com.min.projectboard.dto.ArticleWithCommentsDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleCommentResponse {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private String email;
    private String nickname;

    protected ArticleCommentResponse() {
    }
    private ArticleCommentResponse(Long id,
                                   String content,
                                   LocalDateTime createdAt,
                                   String email,
                                   String nickname){
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.email = email;
        this.nickname = nickname;
    }
    public static ArticleCommentResponse of(Long id,
                                            String content,
                                            LocalDateTime createdAt,
                                            String email,
                                            String nickname){
        return new ArticleCommentResponse(id, content, createdAt, email, nickname);
    }

    public static ArticleCommentResponse from(ArticleCommentDto dto){
        String nickname = dto.getUserAccountDto().getNickname();
        if(nickname == null || nickname.isBlank()){
            nickname = dto.getUserAccountDto().getUserId();
        }

        return new ArticleCommentResponse(
                dto.getId(),
                dto.getContent(),
                dto.getCreatedAt(),
                dto.getUserAccountDto().getEmail(),
                nickname
        );
    }

}
