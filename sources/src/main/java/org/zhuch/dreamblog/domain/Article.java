package org.zhuch.dreamblog.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.zhuch.dreamblog.input.json.ArticleDto;
import org.zhuch.dreamblog.persistence.row.ArticleCommentsRow;
import org.zhuch.dreamblog.persistence.row.ArticleRow;

import java.time.LocalDateTime;
import java.util.Set;

@Value
public class Article {
    @With
    Long articleId;
    Long userId;
    String content;
    @With
    LocalDateTime created;
    @With
    LocalDateTime updated;
    Set<Comment> comments;

    public static Article fromDto(ArticleDto dto) {
        return new Article(dto.getArticleId(), dto.getUserId(), dto.getContent(), dto.getCreated(),
            dto.getUpdated(), null);
    }

    public static Article fromRow(ArticleRow row) {
        return new Article(row.getArticleId(), row.getUserId(), row.getContent(), row.getCreated(),
            row.getUpdated(), null);
    }

    public static Article fromJoined(ArticleCommentsRow joined) {
        return new Article(joined.getArticleId(), joined.getUserId(), joined.getContent(), joined.getCreated(),
            joined.getUpdated(), Comment.fromRowSet(joined.getComments()));
    }

    public ArticleRow toRow() {
        return new ArticleRow(articleId, userId, content, created, updated);
    }

}
