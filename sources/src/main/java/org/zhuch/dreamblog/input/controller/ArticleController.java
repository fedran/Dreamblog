package org.zhuch.dreamblog.input.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.zhuch.dreamblog.domain.service.ArticleService;
import org.springframework.web.bind.annotation.*;
import org.zhuch.dreamblog.input.json.ArticleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.zhuch.dreamblog.domain.Article;
import org.springframework.http.MediaType;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(final ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleDto> getArticle(@PathVariable("id") final Long id) {
        return articleService.findById(id)
            .map(a -> ResponseEntity.ok().body(ArticleDto.fromDomain(a)))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArticleDto> getArticles(
        @RequestParam(name = "pattern", required = false) String pattern,
        @RequestParam(name = "page", required = false) Integer page,
        @RequestParam(name = "size", required = false) Integer size
    ) {
        final List<Article> articles = articleService.find(pattern, page, size);
        return articles.stream()
            .map(ArticleDto::fromDomain)
            .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto createArticle(@RequestBody final ArticleDto articleDto) {
        final Article article = Article.fromDto(articleDto);
        final Article saveResult = articleService.save(article);
        return ArticleDto.fromDomain(saveResult);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateArticle(@RequestBody final ArticleDto article) {
        articleService.save(Article.fromDto(article));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteArticle(@PathVariable("id") final Long id) {
        articleService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping(
        value = "/{id}/like/inc",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void incrementLike(@PathVariable("id") final Long id) {
        articleService.incrementLike(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping(
        value = "/{id}/dislike/inc",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void incrementDislike(@PathVariable("id") final Long id) {
        articleService.incrementDislike(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping(
        value = "/{id}/view/inc",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void incrementView(@PathVariable("id") final Long id) {
        articleService.incrementView(id);
    }
}
