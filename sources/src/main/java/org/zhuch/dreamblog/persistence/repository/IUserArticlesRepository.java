package org.zhuch.dreamblog.persistence.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.zhuch.dreamblog.persistence.extractor.UserArticlesRowExtractor;
import org.zhuch.dreamblog.persistence.row.UserArticlesRow;

import java.util.Optional;

import static org.zhuch.dreamblog.persistence.row.ArticleRow.ARTICLE;
import static org.zhuch.dreamblog.persistence.row.UserRow.USER;

public interface IUserArticlesRepository extends Repository<UserArticlesRow, Long> {

    @Query(
            value = "SELECT user_id, username, article_id, content, a.created as created, updated " +
                    "FROM " + USER + " u LEFT JOIN " + ARTICLE + " a " +
                    "USING(user_id) " +
                    "WHERE user_id = :user_id",
            resultSetExtractorClass = UserArticlesRowExtractor.class
    )
    Optional<UserArticlesRow> findById(@Param("user_id") Long id);

    @Query(
            value = "SELECT user_id, username, article_id, content, a.created as created, updated " +
                    "FROM " + USER + " u LEFT JOIN " + ARTICLE + " a " +
                    "USING(user_id) " +
                    "WHERE username = :username",
            resultSetExtractorClass = UserArticlesRowExtractor.class
    )
    Optional<UserArticlesRow> findByUsername(@Param("username") String username);
}
