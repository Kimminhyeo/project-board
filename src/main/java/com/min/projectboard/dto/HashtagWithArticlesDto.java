package com.min.projectboard.dto;

import com.min.projectboard.domain.Hashtag;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class HashtagWithArticlesDto {
    private Long id;

    private Set<ArticleDto> articles;

    private String hashtagName;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime modifiedAt;

    private String modifiedBy;

    protected HashtagWithArticlesDto(){}

    private HashtagWithArticlesDto(Long id, Set<ArticleDto> articles, String hashtagName, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        this.id = id;
        this.articles = articles;
        this.hashtagName = hashtagName;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
    }

    public static HashtagWithArticlesDto of(Set<ArticleDto> articles, String hashtagName){
        return new HashtagWithArticlesDto(null, articles, hashtagName, null, null, null, null);
    }

    public static HashtagWithArticlesDto of(Long id, Set<ArticleDto> articles, String hashtagName, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new HashtagWithArticlesDto(id, articles, hashtagName, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static HashtagWithArticlesDto from(Hashtag entity) {
        return new HashtagWithArticlesDto(
                entity.getId(),
                entity.getArticles().stream()
                        .map(ArticleDto::from)
                        .collect(Collectors.toUnmodifiableSet())
                ,
                entity.getHashtagName(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Hashtag toEntity() {
        return Hashtag.of(hashtagName);
    }

}
