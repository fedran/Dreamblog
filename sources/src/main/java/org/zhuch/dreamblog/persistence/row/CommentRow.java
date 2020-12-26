package org.zhuch.dreamblog.persistence.row;

import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.zhuch.dreamblog.persistence.Updatable;

import java.time.LocalDateTime;

@Value
@Table(CommentRow.COMMENT)
public class CommentRow implements Updatable<Long> {
    public static final String COMMENT = "comments";

    @Id
    @With
    Long commentId;
    Long articleId;
    String content;
    @With
    LocalDateTime created;
    @With
    LocalDateTime updated;

    @Override
    public Long getId() {
        return commentId;
    }

    public static CommentRow of(Long articleId, String content, LocalDateTime created, LocalDateTime updated) {
        return new CommentRow(null, articleId, content, created, updated);
    }
}
