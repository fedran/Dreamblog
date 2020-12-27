package org.zhuch.dreamblog.persistence.row;

import lombok.Value;
import lombok.With;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

import static org.zhuch.dreamblog.persistence.row.UserRow.USER;

@Slf4j
@Value
@Table(USER)
public class UserArticlesRow {
    @Id
    Long userId;
    String username;
    @With
    @MappedCollection(idColumn = "user_id")
    Set<ArticleRow> articles;
}
