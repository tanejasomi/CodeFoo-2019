package com.codefoo.model;

import java.io.Serializable;

public class Tag implements Serializable {
    private String contentId;
    private String tag;

    public Tag(String tag) {
        this.tag = tag;
    }

    public Tag(String contentId, String tag) {
        this.contentId = contentId;
        this.tag = tag;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tag='" + tag + '\'' +
                '}';
    }
}
