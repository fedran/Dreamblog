package org.zhuch.dreamblog.persistence.row;

import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.zhuch.dreamblog.persistence.Updatable;

import java.time.LocalDateTime;

@Value
@Table(ArticleRow.ARTICLE)
public class ArticleRow implements Updatable<Long> {
    public static final String ARTICLE = "article";

    @Id
    @With
    Long articleId;
    Long userId;
    String content;
    @With
    LocalDateTime created;
    @With
    LocalDateTime updated;

    public static ArticleRow of(Long userId, String content, LocalDateTime created, LocalDateTime updated) {
        return new ArticleRow(null, userId, content, created, updated);
    }

    public static ArticleRow of(Long userId, String content) {
        return new ArticleRow(null, userId, content, null, null);
    }

    @Override
    public Long getId() {
        return articleId;
    }
}
