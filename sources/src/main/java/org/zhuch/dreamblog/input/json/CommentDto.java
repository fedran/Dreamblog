package org.zhuch.dreamblog.input.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Value;
import lombok.With;
import org.jetbrains.annotations.NotNull;
import org.zhuch.dreamblog.domain.Comment;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentDto {
    @With
    Long commentId;
    Long articleId;
    String content;
    @With
    LocalDateTime created;
    @With
    LocalDateTime updated;

    @NotNull
    public static CommentDto fromDomain(@NotNull Comment domain) {
        return new CommentDto(domain.getCommentId(), domain.getArticleId(), domain.getContent(),
            domain.getCreated(), domain.getUpdated());
    }

    @NotNull
    public static Set<CommentDto> fromDomainSet(@NotNull Set<Comment> domain) {
        return domain.stream()
            .map(CommentDto::fromDomain)
            .collect(Collectors.toSet());
    }
}
