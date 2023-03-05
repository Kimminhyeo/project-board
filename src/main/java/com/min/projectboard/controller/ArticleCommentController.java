package com.min.projectboard.controller;

import com.min.projectboard.dto.UserAccountDto;
import com.min.projectboard.dto.request.ArticleCommentRequest;
import com.min.projectboard.dto.security.BoardPrincipal;
import com.min.projectboard.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/comments")
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping("/new")
    public String postNewArticleComment( @AuthenticationPrincipal BoardPrincipal boardPrincipal,
                                         ArticleCommentRequest articleCommentRequest){
        articleCommentService.saveArticleComment(articleCommentRequest.toDto(boardPrincipal.toDto()));

        return "redirect:/articles/" + articleCommentRequest.getArticleId();
    }

    @PostMapping("/{commentId}/delete")
    public String deleteArticleComment(@PathVariable Long articleCommentId,
                                       @AuthenticationPrincipal BoardPrincipal boardPrincipal,
                                       Long articleId){
        articleCommentService.deleteArticleComment(articleCommentId, boardPrincipal.getUsername());

        return "redirect:/articles/" + articleId;
    }
}
