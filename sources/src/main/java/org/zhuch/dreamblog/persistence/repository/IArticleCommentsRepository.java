package org.zhuch.dreamblog.persistence.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.zhuch.dreamblog.persistence.extractor.ArticleCommentsRowExtractor;
import org.zhuch.dreamblog.persistence.row.ArticleCommentsRow;

import java.util.Optional;

import static org.zhuch.dreamblog.persistence.row.ArticleRow.ARTICLE;
import static org.zhuch.dreamblog.persistence.row.CommentRow.COMMENT;

/*
 * https://docs.spring.io/spring-data/data-jdbc/docs/current/reference/html/#preface
 */
public interface IArticleCommentsRepository extends Repository<ArticleCommentsRow, Long> {

    @Query(
            value = "SELECT a.article_id as article_id, a.user_id as user_id, " +
                        "a.content as content, a.created as created, a.updated as updated, " +
                        "comment_id, c.content as content_comment, c.created as created_comment, c.updated as updated_comment " +
                    "FROM " + ARTICLE + " a LEFT JOIN " + COMMENT + " c " +
                    "USING(article_id) " +
                    "WHERE article_id = :article_id",
            resultSetExtractorClass = ArticleCommentsRowExtractor.class
    )
    Optional<ArticleCommentsRow> findById(@Param("article_id") Long id);
}
