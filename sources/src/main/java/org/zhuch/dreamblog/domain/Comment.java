package org.zhuch.dreamblog.domain;

import lombok.Value;
import lombok.With;
import org.zhuch.dreamblog.persistence.row.CommentRow;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Value
public class Comment {
    @With
    Long commentId;
    Long articleId;
    String content;
    @With
    LocalDateTime created;
    @With
    LocalDateTime updated;

    public static Comment fromRow(CommentRow row) {
        return new Comment(row.getCommentId(), row.getArticleId(), row.getContent(),
            row.getCreated(), row.getUpdated());
    }

    public static Set<Comment> fromRowSet(Set<CommentRow> joined) {
        return joined.stream()
            .map(Comment::fromRow)
            .collect(Collectors.toSet());
    }
}

