package com.example.demo.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author chenrunzheng
 * @since 2020/4/8 9:32
 */
public enum AlarmType {
    ALL_ALARM(0,"ALL"),
    /**
     * 虚关告警
     */
    VIRTUAL_CLOSE(1,"openVirtual"),
    /**
     * 防撬告警
     */
    VIOLENCE_VLINK(2,"openViolence"),
    /**
     * 门长时间未关告警
     */
    DOOR_OPENED(3,"openOpened"),
    /**
     * 门内开开关告警
     */
    DOOR_INNER_OPEN(4,"openInner"),
    ;

    private int type;

    private String name;

    AlarmType(int type,String name) {
        this.type = type;
        this.name = name;
    }

    @JsonCreator
    public static AlarmType getType(int type) {
        for (AlarmType alarmType : AlarmType.values()) {
            if (alarmType.getType() == type) {
                return alarmType;
            }
        }
        return null;
    }

    @JsonValue
    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
    public static AlarmType getTypeByname(String name) {
        for (AlarmType alarmType : AlarmType.values()) {
            if (alarmType.getName().equals(name)) {
                return alarmType;
            }
        }
        return null;
    }
}
