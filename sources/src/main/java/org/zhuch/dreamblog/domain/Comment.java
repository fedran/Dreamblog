package org.zhuch.dreamblog.domain;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.zhuch.dreamblog.persistence.row.CommentRow;
import org.zhuch.dreamblog.input.json.CommentDto;
import lombok.Value;
import lombok.With;

import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.Set;

@Value
public class Comment {
    @With
    Long commentId;
    Long articleId;
    Long userId;
    String content;
    @With
    LocalDateTime created;
    @With
    LocalDateTime updated;
    @ReadOnlyProperty
    long likes;
    @ReadOnlyProperty
    long dislikes;

    public static Comment fromDto(final CommentDto dto) {
        return new Comment(
            dto.getCommentId(), dto.getArticleId(), dto.getUserId(),
            dto.getContent(), null, null,
            dto.getLikes(), dto.getDislikes()
        );
    }

    public static Comment fromRow(final CommentRow row) {
        return new Comment(
            row.getCommentId(), row.getArticleId(), row.getUserId(),
            row.getContent(), row.getCreated(), row.getUpdated(),
            row.getLikes(), row.getDislikes()
        );
    }

    public static Set<Comment> fromRowSet(final Set<CommentRow> joined) {
        return joined.stream()
            .map(Comment::fromRow)
            .collect(Collectors.toSet());
    }

    public CommentRow toRow() {
        return new CommentRow(
            commentId, articleId, userId, content,
            created, updated, likes, dislikes
        );
    }
}

