package com.codefoo.model;

import java.io.Serializable;

public class Author implements Serializable {
    private String contentId;
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public Author(String contentId, String name) {
        this.contentId = contentId;
        this.name = name;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                '}';
    }
}
