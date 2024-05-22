package me.chnu.sample.domain.article;

import lombok.RequiredArgsConstructor;
import me.chnu.sample.common.annotation.WriteService;
import me.chnu.sample.domain.article.entity.Article;
import me.chnu.sample.domain.article.entity.ArticleRepository;
import me.chnu.sample.presentation.dto.ArticleDTOs;

@WriteService
@RequiredArgsConstructor
public class ArticleWriteService {
    private final ArticleRepository articleRepository;

    public void create(ArticleDTOs.Create createRequest) {
        articleRepository.save(createRequest.toArticle());
    }

    public void update(
            Long articleId,
            ArticleDTOs.Update updateRequest
    ) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("해당 게시글을 찾을 수 없습니다."));

        article.update(updateRequest.title(), updateRequest.content());
    }

    public void delete(Long articleId) {
        articleRepository.deleteById(articleId);
    }
}
