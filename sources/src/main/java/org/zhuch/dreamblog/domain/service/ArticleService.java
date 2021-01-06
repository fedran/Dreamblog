package org.zhuch.dreamblog.domain.service;

import org.zhuch.dreamblog.persistence.repository.IArticleCommentsRepository;
import org.zhuch.dreamblog.persistence.repository.IArticleRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.zhuch.dreamblog.persistence.row.ArticleRow;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.zhuch.dreamblog.domain.Article;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@Service
@Transactional
public class ArticleService {
    private final IArticleRepository articleRepository;
    private final IArticleCommentsRepository joinedRepository;

    @Autowired
    public ArticleService(
        final IArticleRepository articleRepository,
        final IArticleCommentsRepository joinedRepository
    ) {
        this.articleRepository = articleRepository;
        this.joinedRepository = joinedRepository;
    }

    @NotNull
    public Optional<Article> findById(@NotNull final Long id) {
        return joinedRepository
            .findById(id)
            .map(Article::fromJoined);
    }

    @NotNull
    public Article save(@NotNull Article article) {
        article = article.withCreated(LocalDateTime.now());
        return Article.fromRow(articleRepository.save(article.toRow()));
    }

    public void delete(@NotNull final Article article) {
        articleRepository.delete(article.toRow());
    }

    public void deleteById(@NotNull final Long id) {
        articleRepository.deleteById(id);
    }

    @NotNull
    public List<Article> find(
        @Nullable final String pattern,
        @Nullable Integer page,
        @Nullable Integer size
    ) {
        page = page == null ? 0 : page;
        size = size == null ? 25 : size;
        final Page<ArticleRow> articles =
            articleRepository.findAll(PageRequest.of(page, size));
        return articles.stream()
            .map(Article::fromRow)
            .collect(Collectors.toList());
    }

    public void incrementLike(@NotNull final Long id) {
        articleRepository.likeIncrement(id);
    }

    public void incrementDislike(@NotNull final Long id) {
        articleRepository.dislikeIncrement(id);
    }

    public void incrementView(@NotNull final Long id) {
        articleRepository.viewIncrement(id);
    }
}
