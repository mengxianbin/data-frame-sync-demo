package org.mdev.dataframesync.common.protocol;

import java.util.HashMap;
import java.util.Map;

public enum ProtocolType {

    CS_A(1),
    CS_B(2),
    CS_C(3),
    CS_Frame(4),

    SC_A(11),
    SC_B(12),
    SC_C(13),
    SC_Frame(14),

    ;

    private static final Map<Integer, ProtocolType> map = new HashMap<>();

    static {
        for (ProtocolType type : ProtocolType.values()) {
            map.put(type.getId(), type);
        }
    }

    public static ProtocolType getType(int id) {
        return map.get(id);
    }

    private final int id;

    ProtocolType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
