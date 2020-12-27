package org.zhuch.dreamblog.persistence.extractor;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.zhuch.dreamblog.persistence.row.ArticleRow;
import org.zhuch.dreamblog.persistence.row.UserArticlesRow;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserArticlesRowExtractor implements ResultSetExtractor<Optional<UserArticlesRow>> {

    @Override
    public Optional<UserArticlesRow> extractData(final ResultSet rs) throws SQLException, DataAccessException {
        Long userId = null;
        Set<ArticleRow> articles = null;
        UserArticlesRow userArticles = null;
        boolean first = true;
        while (rs.next()) {
            if (first) {
                userId = rs.getLong("user_id");
                String username = rs.getString("username");
                articles = new HashSet<>();
                userArticles = new UserArticlesRow(userId, username, articles);
                first = false;
            }
            String articleId = rs.getString("article_id");
            if (articleId != null) {
                String content = rs.getString("content");
                LocalDateTime created = rs.getTimestamp("created").toLocalDateTime();
                LocalDateTime updated = rs.getTimestamp("updated").toLocalDateTime();
                ArticleRow articleRow = new ArticleRow(Long.parseLong(articleId), userId, content, created, updated);
                articles.add(articleRow);
            }
        }
        return Optional.ofNullable(userArticles);
    }
}
