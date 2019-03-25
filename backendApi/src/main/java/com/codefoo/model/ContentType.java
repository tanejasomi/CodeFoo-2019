package com.codefoo.model;

import java.util.HashMap;
import java.util.Map;

public enum ContentType {

    VIDEO("video"),
    ARTICLE("article"),
    UNKNOWN("unknown");

    private String name;
    private static Map<String, ContentType> nameToTypeMap = new HashMap<String, ContentType>();

    static {
        ContentType[] values = ContentType.values();
        for (ContentType value : values) {
            nameToTypeMap.put(value.name, value);
        }
    }

    ContentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ContentType getTypeFromName(String name) {
        return nameToTypeMap.get(name);
    }
}
