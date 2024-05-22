package me.chnu.sample.domain.article;

import lombok.RequiredArgsConstructor;
import me.chnu.sample.common.annotation.WriteService;
import me.chnu.sample.domain.article.entity.Article;
import me.chnu.sample.domain.article.entity.ArticleRepository;

import java.util.List;

@WriteService
@RequiredArgsConstructor
public class ArticleReadService {
    private final ArticleRepository articleRepository;

    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    public Article get(Long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("해당 게시글을 찾을 수 없습니다."));
    }
}
