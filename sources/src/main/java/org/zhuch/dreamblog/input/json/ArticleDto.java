package org.zhuch.dreamblog.input.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.zhuch.dreamblog.domain.Article;
import org.jetbrains.annotations.NotNull;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;
import java.util.Set;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDto {
    @With
    Long articleId;
    Long userId;
    String caption;
    String content;
    @With
    LocalDateTime created;
    @With
    LocalDateTime updated;
    Set<CommentDto> comments;
    long likes;
    long dislikes;
    long views;

    @NotNull
    public static ArticleDto fromDomain(@NotNull Article domain) {
        var comments = domain.getComments() == null
            ? null : CommentDto.fromDomainSet(domain.getComments());
        return new ArticleDto(
            domain.getArticleId(), domain.getUserId(), domain.getCaption(),
            domain.getContent(), domain.getCreated(), domain.getUpdated(),
            comments, domain.getLikes(), domain.getDislikes(), domain.getViews()
        );
    }
}
