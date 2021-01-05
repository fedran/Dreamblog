package org.zhuch.dreamblog.domain.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhuch.dreamblog.domain.Article;
import org.zhuch.dreamblog.persistence.repository.IArticleCommentsRepository;
import org.zhuch.dreamblog.persistence.repository.IArticleRepository;
import org.zhuch.dreamblog.persistence.row.ArticleRow;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Article save(@NotNull final Article article) {
        final ArticleRow articleRow = article.toRow();
        final ArticleRow saveResult = articleRepository.save(articleRow);
//TODO: make changes: created: LocalDateTime.now, updated: null;
        //при сохранении нового article saveResult возвращается с
        // created: null, updated: LocalDateTime.now;
        return Article.fromRow(saveResult);
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
        final Page<ArticleRow> arPage = articleRepository.findAll(PageRequest.of(page, size));
        return arPage.stream()
            .map(Article::fromRow)
            .collect(Collectors.toList());
    }
}
