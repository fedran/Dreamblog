package org.zhuch.dreamblog.persistence.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.zhuch.dreamblog.persistence.row.ArticleRow;

import java.util.List;

import static org.zhuch.dreamblog.persistence.row.ArticleRow.ARTICLE;
import static org.zhuch.dreamblog.persistence.row.UserRow.USER;

@RepositoryRestResource(collectionResourceRel = ARTICLE, path = ARTICLE)
public interface IArticleRepository extends PagingAndSortingRepository<ArticleRow, Long> {

    @Query("SELECT * FROM " + ARTICLE + " WHERE user_id = ( SELECT user_id FROM " + USER + " WHERE username = :username ) ")
    List<ArticleRow> findByUsername(String username);
}
