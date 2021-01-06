package org.zhuch.dreamblog.input.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.zhuch.dreamblog.domain.Comment;
import org.jetbrains.annotations.NotNull;
import lombok.Value;
import lombok.With;

import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.Set;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentDto {
    @With
    Long commentId;
    Long articleId;
    Long userId;
    String content;
    @With
    LocalDateTime created;
    @With
    LocalDateTime updated;
    long likes;
    long dislikes;

    @NotNull
    public static CommentDto fromDomain(@NotNull final Comment domain) {
        return new CommentDto(
            domain.getCommentId(), domain.getArticleId(),
            domain.getUserId(), domain.getContent(), domain.getCreated(),
            domain.getUpdated(), domain.getLikes(), domain.getDislikes()
        );
    }

    @NotNull
    public static Set<CommentDto> fromDomainSet(@NotNull Set<Comment> domain) {
        return domain.stream()
            .map(CommentDto::fromDomain)
            .collect(Collectors.toSet());
    }
}
