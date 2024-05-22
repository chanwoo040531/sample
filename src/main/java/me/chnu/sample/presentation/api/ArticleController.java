package me.chnu.sample.presentation.api;

import lombok.RequiredArgsConstructor;
import me.chnu.sample.domain.article.ArticleReadService;
import me.chnu.sample.domain.article.ArticleWriteService;
import me.chnu.sample.domain.article.entity.Article;
import me.chnu.sample.presentation.dto.ApiResponse;
import me.chnu.sample.presentation.dto.ArticleDTOs;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleWriteService articleWriteService;
    private final ArticleReadService articleReadService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<String> create(@RequestBody ArticleDTOs.Create createRequest) {
        articleWriteService.create(createRequest);

        return ApiResponse.ok("성공적으로 게시글이 생성되었습니다.");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<ArticleDTOs.ListResponse>> getAll() {
        List<ArticleDTOs.ListResponse> response = articleReadService.getAll()
                .stream()
                .map(ArticleDTOs.ListResponse::from)
                .toList();

        return ApiResponse.ok(response);
    }

    @GetMapping("/{article-id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ArticleDTOs.Response> get(@PathVariable("article-id") Long articleId) {
        Article article = articleReadService.get(articleId);
        ArticleDTOs.Response response = ArticleDTOs.Response.from(article);

        return ApiResponse.ok(response);
    }

    @PutMapping("/{article-id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<String> update(
            @PathVariable("article-id") Long articleId,
            @RequestBody ArticleDTOs.Update updateRequest
    ) {
        articleWriteService.update(articleId, updateRequest);

        return ApiResponse.ok("성공적으로 게시글이 수정되었습니다.");
    }

    @DeleteMapping("/{article-id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<String> delete(@PathVariable("article-id") Long articleId) {
        articleWriteService.delete(articleId);

        return ApiResponse.ok("성공적으로 게시글이 삭제되었습니다.");
    }
}
