package com.min.projectboard.dto.response;

import com.min.projectboard.dto.ArticleDto;
import com.min.projectboard.dto.HashtagDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class ArticleResponse {
    private Long id;
    private String title;
    private String content;
    private Set<String> hashtags;
    private LocalDateTime createdAt;
    private String email;
    private String nickname;

    public ArticleResponse(Long id,
                           String title,
                           String content,
                           Set<String> hashtags,
                           LocalDateTime createdAt,
                           String email,
                           String nickname) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hashtags = hashtags;
        this.createdAt = createdAt;
        this.email = email;
        this.nickname = nickname;
    }

    public static ArticleResponse of(Long id,
                                     String title,
                                     String content,
                                     Set<String> hashtags,
                                     LocalDateTime createdAt,
                                     String email,
                                     String nickname) {
        return new ArticleResponse(id, title, content, hashtags, createdAt, email, nickname);
    }

    public static ArticleResponse from(ArticleDto dto) {
        String nickname = dto.getUserAccountDto().getNickname();
        if (nickname == null || nickname.isBlank()) {
            nickname = dto.getUserAccountDto().getUserId();
        }

        return new ArticleResponse(
                dto.getId(),
                dto.getTitle(),
                dto.getContent(),
                dto.getHashtagDtos().stream()
                        .map(HashtagDto::getHashtagName)
                        .collect(Collectors.toUnmodifiableSet()),
                dto.getCreatedAt(),
                dto.getUserAccountDto().getEmail(),
                nickname
        );
    }
}
