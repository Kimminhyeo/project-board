package com.min.projectboard.dto.response;

import com.min.projectboard.dto.ArticleCommentDto;
import com.min.projectboard.dto.ArticleWithCommentsDto;
import com.min.projectboard.dto.HashtagDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
public class ArticleWithCommentsResponse {
    private Long id;
    private String title;
    private String content;
    private Set<String> hashtags;
    private LocalDateTime createdAt;
    private String email;
    private String nickname;
    private String userId;
    private Set<ArticleCommentResponse> articleCommentsResponse;

    private ArticleWithCommentsResponse(Long id, String title, String content, Set<String> hashtags, LocalDateTime createdAt, String email, String nickname, String userId, Set<ArticleCommentResponse> articleCommentsResponse) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hashtags = hashtags;
        this.createdAt = createdAt;
        this.email = email;
        this.nickname = nickname;
        this.userId = userId;
        this.articleCommentsResponse = articleCommentsResponse;
    }

    public static ArticleWithCommentsResponse of(Long id,
                                                 String title,
                                                 String content,
                                                 Set<String> hashtags,
                                                 LocalDateTime createdAt,
                                                 String email,
                                                 String nickname,
                                                 String userId,
                                                 Set<ArticleCommentResponse> articleCommentResponses) {
        return new ArticleWithCommentsResponse(id, title, content, hashtags, createdAt, email, nickname, userId, articleCommentResponses);
    }

    public static ArticleWithCommentsResponse from(ArticleWithCommentsDto dto) {
        String nickname = dto.getUserAccountDto().getNickname();
        if (nickname == null || nickname.isBlank()) {
            nickname = dto.getUserAccountDto().getUserId();
        }

        return new ArticleWithCommentsResponse(
                dto.getId(),
                dto.getTitle(),
                dto.getContent(),
                dto.getHashtagDtos().stream()
                        .map(HashtagDto::getHashtagName)
                        .collect(Collectors.toUnmodifiableSet()),
                dto.getCreatedAt(),
                dto.getUserAccountDto().getEmail(),
                nickname,
                dto.getUserAccountDto().getUserId(),
                organizeChildComments(dto.getArticleCommentDtos())
        );
    }

    private static Set<ArticleCommentResponse> organizeChildComments(Set<ArticleCommentDto> dtos){
        Map<Long, ArticleCommentResponse> map = dtos.stream()
                .map(ArticleCommentResponse::from)
                .collect(Collectors.toMap(ArticleCommentResponse::getId, Function.identity()));

        map.values().stream()
                .filter(ArticleCommentResponse::hasParentComment)
                .forEach(comment -> {
                    ArticleCommentResponse parentComment = map.get(comment.getParentCommentId());
                    parentComment.getChildComments().add(comment);
                });

        return map.values().stream()
                .filter(comment -> !comment.hasParentComment())
                .collect(Collectors.toCollection(() ->
                        new TreeSet<>(Comparator
                                .comparing(ArticleCommentResponse::getCreatedAt)
                                .reversed()
                                .thenComparingLong(ArticleCommentResponse::getId)
                        )
                ));
    }
}
