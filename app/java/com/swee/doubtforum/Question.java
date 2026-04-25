package com.swee.doubtforum;

public class Question {
    private String id;
    private String title;
    private String description;
    private String userId;
    private long timestamp;

    public Question(String id, String title, String description, String userId, long timestamp) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getUserId() { return userId; }
    public long getTimestamp() { return timestamp; }
}