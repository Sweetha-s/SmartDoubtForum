package com.swee.doubtforum;

import com.google.firebase.firestore.DocumentId;
import java.util.Date;
import java.util.List;

public class Doubt {
    @DocumentId
    private String id;
    private String title;
    private String description;
    private String userId;
    private String userEmail;
    private Date timestamp;
    private List<String> likes;

    public Doubt() {} // Required for Firestore

    public Doubt(String title, String description, String userId, String userEmail, Date timestamp, List<String> likes) {
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.userEmail = userEmail;
        this.timestamp = timestamp;
        this.likes = likes;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getUserId() { return userId; }
    public String getUserEmail() { return userEmail; }
    public Date getTimestamp() { return timestamp; }
    public List<String> getLikes() { return likes; }
}
