package org.zhuch.dreamblog.persistence;

import java.time.LocalDateTime;

public interface Updatable<T> {

    T getId();

    LocalDateTime getCreated();

    Updatable withCreated(LocalDateTime updated);

    Updatable withUpdated(LocalDateTime updated);
}
