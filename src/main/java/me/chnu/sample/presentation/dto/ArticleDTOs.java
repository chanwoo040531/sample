package me.chnu.sample.presentation.dto;

import me.chnu.sample.domain.article.entity.Article;

import java.time.LocalDateTime;

public class ArticleDTOs {

    public record Create(String title, String content) {
        public Article toArticle() {
            return Article.builder()
                    .title(title)
                    .content(content)
                    .build();
        }
    }

    public record ListResponse(Long id, String title) {
        public static ListResponse from(Article article) {
            return new ListResponse(article.getId(), article.getTitle());
        }
    }

    public record Response(
            Long id,
            String title,
            String content,
            LocalDateTime createdAt,
            LocalDateTime lastUpdatedAt
    ) {
        public static Response from(Article article) {
            return new Response(
                    article.getId(),
                    article.getTitle(),
                    article.getContent(),
                    article.getCreatedAt(),
                    article.getLastUpdatedAt()
            );
        }
    }

    public record Update(String title, String content) { }
}
