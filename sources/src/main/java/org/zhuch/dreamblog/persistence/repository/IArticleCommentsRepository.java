package org.zhuch.dreamblog.persistence.repository;

import org.zhuch.dreamblog.persistence.extractor.ArticleCommentsRowExtractor;
import org.zhuch.dreamblog.persistence.row.ArticleCommentsRow;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.Repository;

import java.util.Optional;

import static org.zhuch.dreamblog.persistence.row.ArticleRow.ARTICLE;
import static org.zhuch.dreamblog.persistence.row.CommentRow.COMMENT;

public interface IArticleCommentsRepository
    extends Repository<ArticleCommentsRow, Long> {

    @Query(
        value = "SELECT a.article_id as article_id, a.user_id as user_id, " +
                    "a.caption as caption, a.content as content, " +
                    "a.created as created, a.updated as updated, " +
                    "a.likes as likes, a.dislikes as dislikes, a.views as views, " +
                    "comment_id, c.content as content_comment, " +
                    "c.created as created_comment, c.updated as updated_comment " +
                "FROM " + ARTICLE + " a LEFT JOIN " + COMMENT + " c " +
                "USING(article_id) " +
                "WHERE article_id = :article_id",
        resultSetExtractorClass = ArticleCommentsRowExtractor.class
    )
    Optional<ArticleCommentsRow> findById(@Param("article_id") Long id);
}
