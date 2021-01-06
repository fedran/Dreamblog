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
    public static final String ARTICLE = "articles";

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
    @ReadOnlyProperty
    long likes;
    @ReadOnlyProperty
    long dislikes;
    @ReadOnlyProperty
    long views;

    public static ArticleRow of(
        Long userId, String caption, String content, LocalDateTime created,
        LocalDateTime updated, long likes, long dislikes, long views
    ) {
        return new ArticleRow(
            null, userId, caption, content,
            created, updated, likes, dislikes, views
        );
    }

    public static ArticleRow of(
        final Long userId, final String caption, final String content,
        final long likes, final long dislikes, final long views
    ) {
        return new ArticleRow(
            null, userId, caption, content,
            null, null, likes, dislikes, views);
    }
}
