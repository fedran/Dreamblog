package org.zhuch.dreamblog.domain;

import org.zhuch.dreamblog.persistence.row.ArticleCommentsRow;
import org.zhuch.dreamblog.persistence.row.ArticleRow;
import org.zhuch.dreamblog.input.json.ArticleDto;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;
import java.util.Set;

@Value
public class Article {
    @With
    Long articleId;
    Long userId;
    String caption;
    String content;
    @With
    LocalDateTime created;
    @With
    LocalDateTime updated;
    Set<Comment> comments;
    long likes;
    long dislikes;
    long views;

    public static Article fromDto(final ArticleDto dto) {
        return new Article(
            dto.getArticleId(), dto.getUserId(), dto.getCaption(),
            dto.getContent(), null, null, null,
            dto.getLikes(), dto.getDislikes(), dto.getViews()
        );
    }

    public static Article fromRow(final ArticleRow row) {
        return new Article(
            row.getArticleId(), row.getUserId(), row.getCaption(),
            row.getContent(), row.getCreated(), row.getUpdated(),
            null, row.getLikes(), row.getDislikes(), row.getViews()
        );
    }

    public static Article fromJoined(final ArticleCommentsRow joined) {
        return new Article(
            joined.getArticleId(), joined.getUserId(), joined.getCaption(),
            joined.getContent(), joined.getCreated(), joined.getUpdated(),
            Comment.fromRowSet(joined.getComments()), joined.getLikes(),
            joined.getDislikes(), joined.getViews()
        );
    }

    public ArticleRow toRow() {
        return new ArticleRow(
            articleId, userId, caption, content,
            created, updated, likes, dislikes, views
        );
    }
}
