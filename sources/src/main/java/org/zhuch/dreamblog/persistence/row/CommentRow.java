package org.zhuch.dreamblog.persistence.row;

import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.zhuch.dreamblog.persistence.Updatable;
import org.springframework.data.annotation.Id;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;

@Value
@Table(CommentRow.COMMENT)
public class CommentRow implements Updatable {
    public static final String COMMENT = "comments";

    @Id
    @With
    Long commentId;
    Long articleId;
    Long userId;
    String content;
    @ReadOnlyProperty
    LocalDateTime created;
    @With
    LocalDateTime updated;
    @ReadOnlyProperty
    long likes;
    @ReadOnlyProperty
    long dislikes;

    public static CommentRow of(
        Long articleId, Long userId, String content, LocalDateTime created,
        LocalDateTime updated, long likes, long dislikes
    ) {
        return new CommentRow(
            null, articleId, userId, content,
            created, updated, likes, dislikes
        );
    }
}
