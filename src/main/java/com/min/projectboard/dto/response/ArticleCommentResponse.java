package com.min.projectboard.dto.response;

import com.min.projectboard.dto.ArticleCommentDto;
import com.min.projectboard.dto.ArticleWithCommentsDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
public class ArticleCommentResponse {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private String email;
    private String nickname;
    private String userId;
    private Long parentCommentId;
    private Set<ArticleCommentResponse> childComments;

    protected ArticleCommentResponse() {
    }
    private ArticleCommentResponse(Long id,
                                   String content,
                                   LocalDateTime createdAt,
                                   String email,
                                   String nickname,
                                   String userId,
                                   Long parentCommentId,
                                   Set<ArticleCommentResponse> childComments){
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.email = email;
        this.nickname = nickname;
        this.userId = userId;
        this.parentCommentId = parentCommentId;
        this.childComments = childComments;
    }
    public static ArticleCommentResponse of(Long id,
                                            String content,
                                            LocalDateTime createdAt,
                                            String email,
                                            String nickname,
                                            String userId){
        return ArticleCommentResponse.of(id, content, createdAt, email, nickname, userId, null);
    }

    public static ArticleCommentResponse of(Long id,
                                            String content,
                                            LocalDateTime createdAt,
                                            String email,
                                            String nickname,
                                            String userId,
                                            Long parentCommentId){
        Comparator<ArticleCommentResponse> childCommentComparator = Comparator
                .comparing(ArticleCommentResponse::getCreatedAt)
                .thenComparingLong(ArticleCommentResponse::getId);
        return new ArticleCommentResponse(id, content, createdAt, email, nickname, userId, parentCommentId, new TreeSet<>(childCommentComparator));
    }

    public static ArticleCommentResponse from(ArticleCommentDto dto){
        String nickname = dto.getUserAccountDto().getNickname();
        if(nickname == null || nickname.isBlank()){
            nickname = dto.getUserAccountDto().getUserId();
        }

        return ArticleCommentResponse.of(
                dto.getId(),
                dto.getContent(),
                dto.getCreatedAt(),
                dto.getUserAccountDto().getEmail(),
                nickname,
                dto.getUserAccountDto().getUserId(),
                dto.getParentCommentId()
        );
    }

    public boolean hasParentComment(){
        return parentCommentId != null;
    }

}
