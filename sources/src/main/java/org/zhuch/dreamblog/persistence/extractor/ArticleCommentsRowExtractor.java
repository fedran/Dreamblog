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
                String content = rs.getString("content");
                LocalDateTime created = rs.getTimestamp("created").toLocalDateTime();
                LocalDateTime updated = rs.getTimestamp("updated").toLocalDateTime();
                comments = new HashSet<>();
                articleComments = new ArticleCommentsRow(articleId, userId, content, created, updated, comments);
                first = false;
            }
            String commentId = rs.getString("comment_id");
            if (commentId != null) {
                String content = rs.getString("content_comment");
                LocalDateTime created = rs.getTimestamp("created_comment").toLocalDateTime();
                LocalDateTime updated = rs.getTimestamp("updated_comment").toLocalDateTime();
                comments.add(new CommentRow(Long.parseLong(commentId), articleId, content, created, updated));
            }
        }
        return Optional.ofNullable(articleComments);
    }
}
