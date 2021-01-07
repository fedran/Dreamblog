package org.zhuch.dreamblog.persistence.extractor;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.zhuch.dreamblog.persistence.row.ArticleCommentsRow;
import org.zhuch.dreamblog.persistence.row.CommentRow;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ArticleCommentsRowExtractor implements ResultSetExtractor<Optional<ArticleCommentsRow>> {
    @Override
    public Optional<ArticleCommentsRow> extractData(final ResultSet rs) throws SQLException, DataAccessException {
        Long articleId = null;
        Set<CommentRow> comments = null;
        ArticleCommentsRow articleComments = null;
        boolean first = true;
        while (rs.next()) {
            if (first) {
                articleId = rs.getLong("article_id");
                Long userId = rs.getLong("user_id");
                final String caption = rs.getString("caption");
                String content = rs.getString("content");
                LocalDateTime created = rs.getTimestamp("created").toLocalDateTime();
                LocalDateTime updated = rs.getTimestamp("updated").toLocalDateTime();
                comments = new HashSet<>();
                final int likes = rs.getInt("likes");
                final int dislikes = rs.getInt("dislikes");
                final int views = rs.getInt("views");
                articleComments = new ArticleCommentsRow(articleId, userId, caption, content, created, updated, comments, likes, dislikes, views);
                first = false;
            }
            String commentId = rs.getString("comment_id");
            if (commentId != null) {
                String content = rs.getString("content_comment");
                final long userId = rs.getLong("user_id_comment");
                LocalDateTime created = rs.getTimestamp("created_comment").toLocalDateTime();
                LocalDateTime updated = rs.getTimestamp("updated_comment").toLocalDateTime();
                final long likes = rs.getLong("likes_comment");
                final long dislikes = rs.getLong("dislikes_comment");
                comments.add(new CommentRow(
                    Long.parseLong(commentId), articleId, userId,
                    content, created, updated, likes, dislikes
                ));
            }
        }
        return Optional.ofNullable(articleComments);
    }
}
