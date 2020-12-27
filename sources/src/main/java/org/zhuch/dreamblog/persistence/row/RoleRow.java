package org.zhuch.dreamblog.persistence.row;

import lombok.Value;
import org.springframework.data.relational.core.mapping.Table;

@Value
@Table("roles")
public class RoleRow {
    long role_id;
    String name;

    RoleRow(long role_id, String name) {
        this.role_id = role_id;
        this.name = name;
    }
}
