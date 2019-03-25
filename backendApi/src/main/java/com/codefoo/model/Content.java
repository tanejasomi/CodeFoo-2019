package com.codefoo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Content implements Serializable {
    private String contentID;
    private ContentType contentType;
    private String title;
    private String headline;
    private String description;
    private Date publishDate;
    // private String description2;
    private String slug;
    private String state;
    private Integer duration;
    private String videoSeries;

    private List<Thumbnail> thumbnails = new ArrayList<Thumbnail>();
    private List<Tag> tags = new ArrayList<Tag>();
    private List<Author> authors = new ArrayList<Author>();

    public Content(String contentID) {
        this.contentID = contentID;
    }

    public Content(String contentID, ContentType contentType, String title, String headline, String description, Date publishDate, String slug, String state, Integer duration, String videoSeries) {
        this.contentID = contentID;
        this.contentType = contentType;
        this.title = title;
        this.headline = headline;
        this.description = description;
        this.publishDate = publishDate;
        //this.description2 = description;
        this.slug = slug;
        this.state = state;
        this.duration = duration;
        this.videoSeries = videoSeries;
    }

    public String getContentID() {
        return contentID;
    }

    public void setContentID(String contentID) {
        this.contentID = contentID;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getVideoSeries() {
        return videoSeries;
    }

    public void setVideoSeries(String videoSeries) {
        this.videoSeries = videoSeries;
    }

    public List<Thumbnail> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<Thumbnail> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }


    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void addThumbnail(Thumbnail thumbnail) {
        thumbnails.add(thumbnail);
    }

    @Override
    public String toString() {
        return "Content{" +
                "contentID='" + contentID + '\'' +
                ", contentType=" + contentType +
                ", title='" + title + '\'' +
                ", headline='" + headline + '\'' +
                ", description='" + description + '\'' +
                ", publishDate=" + publishDate +
                ", slug='" + slug + '\'' +
                ", state='" + state + '\'' +
                ", duration=" + duration +
                ", videoSeries='" + videoSeries + '\'' +
                ", thumbnails=" + thumbnails +
                ", tags=" + tags +
                ", authors=" + authors +
                '}';
    }
}
