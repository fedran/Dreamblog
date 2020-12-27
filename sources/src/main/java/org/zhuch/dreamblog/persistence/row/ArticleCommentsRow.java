package org.zhuch.dreamblog.persistence.row;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Set;

import static org.zhuch.dreamblog.persistence.row.ArticleRow.ARTICLE;

@Value
@Table(ARTICLE)
public class ArticleCommentsRow {
    @Id
    Long articleId;
    Long userId;
    String content;
    LocalDateTime created;
    LocalDateTime updated;
    @MappedCollection(idColumn = "article_id")
    Set<CommentRow> comments;
}
