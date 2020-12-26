package org.zhuch.dreamblog.persistence.callback;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;
import org.springframework.stereotype.Component;
import org.zhuch.dreamblog.persistence.Updatable;

import java.time.LocalDateTime;

@Slf4j
@Order(1)
@Component
public class UpdatableCallback implements BeforeConvertCallback<Updatable> {

    @NotNull
    @Override
    public Updatable onBeforeConvert(@NotNull final Updatable aggregate) {
        var now = LocalDateTime.now();
        log.debug("update aggregate - {} - time -  {}", aggregate, now);
        var article = aggregate.withUpdated(now);
        if (aggregate.getId() == null && aggregate.getCreated() == null) {
            article = article.withCreated(LocalDateTime.now());
        }
        return article;
    }
}
