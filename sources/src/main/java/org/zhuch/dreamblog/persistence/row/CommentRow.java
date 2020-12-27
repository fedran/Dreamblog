package org.zhuch.dreamblog.persistence.row;

import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.Table;
import org.zhuch.dreamblog.persistence.Updatable;

import java.time.LocalDateTime;

@Value
@Table(CommentRow.COMMENT)
public class CommentRow implements Updatable {
    public static final String COMMENT = "comments";

    @Id
    @With
    Long commentId;
    Long articleId;
    String content;
    @ReadOnlyProperty
    LocalDateTime created;
    @With
    LocalDateTime updated;

    public static CommentRow of(Long articleId, String content, LocalDateTime created, LocalDateTime updated) {
        return new CommentRow(null, articleId, content, created, updated);
    }
}
