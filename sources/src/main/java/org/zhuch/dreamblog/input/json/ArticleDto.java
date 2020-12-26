package org.zhuch.dreamblog.input.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Value;
import lombok.With;
import org.jetbrains.annotations.NotNull;
import org.zhuch.dreamblog.domain.Article;

import java.time.LocalDateTime;
import java.util.Set;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDto {
    @With
    Long articleId;
    Long userId;
    String content;
    @With
    LocalDateTime created;
    @With
    LocalDateTime updated;
    Set<CommentDto> comments;

    @NotNull
    public static ArticleDto fromDomain(@NotNull Article domain) {
        var comments = domain.getComments() == null
            ? null : CommentDto.fromDomainSet(domain.getComments());
        return new ArticleDto(domain.getArticleId(), domain.getUserId(), domain.getContent(),
            domain.getCreated(), domain.getUpdated(), comments);
    }
}
