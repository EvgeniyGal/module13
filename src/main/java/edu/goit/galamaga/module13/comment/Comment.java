package edu.goit.galamaga.module13.comment;

import java.util.Objects;

public class Comment {

    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    public Comment(int postId, int id, String name, String email, String body) {
        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment post)) return false;
        return getPostId() == post.getPostId() && getId() == post.getId() &&
                Objects.equals(getName(), post.getName()) && Objects.equals(getEmail(), post.getEmail()) &&
                Objects.equals(getBody(), post.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPostId(), getId(), getName(), getEmail(), getBody());
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

