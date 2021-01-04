package org.zhuch.dreamblog.persistence.row;

import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.zhuch.dreamblog.persistence.Updatable;
import org.springframework.data.annotation.Id;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;

@Value
@Table(ArticleRow.ARTICLE)
public class ArticleRow implements Updatable {
    public static final String ARTICLE = "article";

    @Id
    @With
    Long articleId;
    Long userId;
    String caption;
    String content;
    @ReadOnlyProperty
    LocalDateTime created;
    @With
    LocalDateTime updated;
    int likes;
    int dislikes;
    int views;

    public static ArticleRow of(
        final Long userId, final String caption, final String content,
        final LocalDateTime created, final LocalDateTime updated,
        final int likes, final int dislikes, final int views
    ) {
        return new ArticleRow(
            null, userId, caption, content,
            created, updated, likes, dislikes, views
        );
    }

    public static ArticleRow of(
        final Long userId, final String caption, final String content,
        final int likes, final int dislikes, final int views
    ) {
        return new ArticleRow(
            null, userId, caption, content,
            null, null, likes, dislikes, views);
    }
}
