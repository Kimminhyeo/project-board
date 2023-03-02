package com.min.projectboard.dto;

import com.min.projectboard.domain.Article;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class ArticleWithCommentsDto {
    private Long id;
    private UserAccountDto userAccountDto;
    private Set<ArticleCommentDto> articleCommentDtos;
    private String title;
    private String content;
    private String hashtag;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    private ArticleWithCommentsDto(Long id,
                                  UserAccountDto userAccountDto,
                                  Set<ArticleCommentDto> articleCommentDtos,
                                  String title,
                                  String content,
                                  String hashtag,
                                  LocalDateTime createdAt,
                                  String createdBy,
                                  LocalDateTime modifiedAt,
                                  String modifiedBy) {
        this.id = id;
        this.userAccountDto = userAccountDto;
        this.articleCommentDtos = articleCommentDtos;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
    }

    public static ArticleWithCommentsDto of(Long id,
                                            UserAccountDto userAccountDto,
                                            Set<ArticleCommentDto> articleCommentDtos,
                                            String title,
                                            String content,
                                            String hashtag,
                                            LocalDateTime createdAt,
                                            String createdBy,
                                            LocalDateTime modifiedAt,
                                            String modifiedBy) {
        return new ArticleWithCommentsDto(id, userAccountDto, articleCommentDtos, title, content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ArticleWithCommentsDto from(Article entity) {
        return new ArticleWithCommentsDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getArticleComments().stream()
                        .map(ArticleCommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}
