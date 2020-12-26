package org.zhuch.dreamblog.persistence;

import java.time.LocalDateTime;

public interface Updatable {

    Updatable withUpdated(LocalDateTime updated);
}
