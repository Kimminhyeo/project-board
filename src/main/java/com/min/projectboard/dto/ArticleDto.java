package com.min.projectboard.dto;

import com.min.projectboard.domain.Article;
import com.min.projectboard.domain.UserAccount;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class ArticleDto {
    private Long id;
    private UserAccountDto userAccountDto;
    private String title;
    private String content;
    private Set<HashtagDto> hashtagDtos;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    protected ArticleDto(){}

    private ArticleDto(Long id,
                       UserAccountDto userAccountDto,
                       String title,
                       String content,
                       Set<HashtagDto> hashtagDtos,
                       LocalDateTime createdAt,
                       String createdBy,
                       LocalDateTime modifiedAt,
                       String modifiedBy){
        this.id = id;
        this.userAccountDto = userAccountDto;
        this.title = title;
        this.content = content;
        this.hashtagDtos = hashtagDtos;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
    }

    public static ArticleDto of(UserAccountDto userAccountDto,
                                String title,
                                String content,
                                Set<HashtagDto> hashtagDtos) {
        return new ArticleDto(null, userAccountDto, title, content, hashtagDtos, null, null, null, null);
    }

    public static ArticleDto of(Long id,
                                UserAccountDto userAccountDto,
                                String title,
                                String content,
                                Set<HashtagDto> hashtagDtos,
                                LocalDateTime createdAt,
                                String createdBy,
                                LocalDateTime modifiedAt,
                                String modifiedBy){
        return new ArticleDto(id, userAccountDto, title, content, hashtagDtos, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ArticleDto from(Article entity){
        return new ArticleDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtags().stream()
                        .map(HashtagDto::from)
                        .collect(Collectors.toUnmodifiableSet()),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Article toEntity(UserAccount userAccount){
        return Article.of(
                userAccount,
                title,
                content
        );
    }
}
