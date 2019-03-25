package com.codefoo.model;

import java.io.Serializable;

public class Thumbnail implements Serializable {
    private String contentID;
    private String thumbnailURL;
    private String size;
    private Float height;
    private Float width;

    public Thumbnail() {

    }

    public Thumbnail(String thumbnailURL, String size, Float height, Float width) {
        this.thumbnailURL = thumbnailURL;
        this.size = size;
        this.height = height;
        this.width = width;
    }

    public Thumbnail(String contentID, String thumbnailURL, String size, Float height, Float width) {
        this.contentID = contentID;
        this.thumbnailURL = thumbnailURL;
        this.size = size;
        this.height = height;
        this.width = width;
    }

    public String getContentID() {
        return contentID;
    }

    public void setContentID(String contentID) {
        this.contentID = contentID;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }


    @Override
    public String toString() {
        return "Thumbnail{" +
                "thumbnailURL='" + thumbnailURL + '\'' +
                ", size='" + size + '\'' +
                ", height=" + height +
                ", width=" + width +
                '}';
    }
}
