package edu.goit.galamaga.module13.todos;

import java.util.Objects;

public class Todos {

    private int userId;
    private int id;
    private String title;
    private boolean completed;

    public Todos(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Todos todos)) return false;
        return getUserId() == todos.getUserId() && getId() == todos.getId() && isCompleted() == todos.isCompleted() && Objects.equals(getTitle(), todos.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getId(), getTitle(), isCompleted());
    }

    @Override
    public String toString() {
        return "Todos{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
