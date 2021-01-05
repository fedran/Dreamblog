package org.zhuch.dreamblog.input.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zhuch.dreamblog.domain.Article;
import org.zhuch.dreamblog.domain.service.ArticleService;
import org.zhuch.dreamblog.input.json.ArticleDto;

import java.util.List;
import java.util.stream.Collectors;

/*
 * https://docs.spring.io/spring-framework/docs/current/reference/html/web.html
 */
@Slf4j
@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleDto> getArticle(@PathVariable("id") Long id) {
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
        return articleService.find(pattern, page, size).stream()
            .map(ArticleDto::fromDomain)
            .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto createArticle(@RequestBody ArticleDto article) {
        return ArticleDto.fromDomain(articleService.save(Article.fromDto(article)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateArticle(@RequestBody ArticleDto article) {
        articleService.save(Article.fromDto(article));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteArticle(@PathVariable("id") Long id) {
        articleService.deleteById(id);
    }
}
