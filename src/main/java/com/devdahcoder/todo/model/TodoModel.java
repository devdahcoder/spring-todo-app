package com.devdahcoder.todo.model;

import com.devdahcoder.todo.contract.TodoPriorityEnum;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class TodoModel {

    private int id;
    private UUID userId;
    private String note;
    private String tags;
    private Date dueDate;
    private int progress;
    private Date startDate;
    private String taskName;
    private String category;
    private String assignedTo;
    private boolean completed;
    private int estimatedTime;
    private Date completedDate;
//    private Date reminder;
    private TodoPriorityEnum priority;

    public TodoModel() { }

    public TodoModel(
            int id,
            UUID userId,
            String note,
            String tags,
            Date dueDate,
            int progress,
            Date startDate,
            String taskName,
            String category,
            String assignedTo,
            boolean completed,
            int estimatedTime,
            Date completedDate,
            TodoPriorityEnum priority
    ) {

        this.id = id;
        this.userId = userId;
        this.note = note;
        this.tags = tags;
        this.dueDate = dueDate;
        this.progress = progress;
        this.taskName = taskName;
        this.category = category;
        this.priority = priority;
        this.startDate = startDate;
        this.completed = completed;
        this.assignedTo = assignedTo;
        this.estimatedTime = estimatedTime;
        this.completedDate = completedDate;

    }

    public int getId() {

        return id;

    }

    public void setId(int id) {

        this.id = id;

    }

    public UUID getUserId() {

        return userId;

    }

    public void setUserId(UUID userId) {

        this.userId = userId;

    }

    public String getNote() {

        return note;

    }

    public void setNote(String note) {

        this.note = note;

    }

    public String getTags() {

        return tags;

    }

    public void setTags(String tags) {

        this.tags = tags;

    }

    public Date getDueDate() {

        return dueDate;

    }

    public void setDueDate(Date dueDate) {

        this.dueDate = dueDate;

    }

    public int getProgress() {

        return progress;

    }

    public void setProgress(int progress) {

        this.progress = progress;

    }

    public Date getStartDate() {

        return startDate;

    }

    public void setStartDate(Date startDate) {

        this.startDate = startDate;

    }

    public String getTaskName() {

        return taskName;

    }

    public void setTaskName(String taskName) {

        this.taskName = taskName;

    }

    public String getCategory() {

        return category;

    }

    public void setCategory(String category) {

        this.category = category;

    }

    public String getAssignedTo() {

        return assignedTo;

    }

    public void setAssignedTo(String assignedTo) {

        this.assignedTo = assignedTo;

    }

    public boolean isCompleted() {

        return completed;

    }

    public void setCompleted(boolean completed) {

        this.completed = completed;

    }

    public int getEstimatedTime() {

        return estimatedTime;

    }

    public void setEstimatedTime(int estimatedTime) {

        this.estimatedTime = estimatedTime;

    }

    public Date getCompletedDate() {

        return completedDate;

    }

    public void setCompletedDate(Date completedDate) {

        this.completedDate = completedDate;

    }

    public TodoPriorityEnum getPriority() {

        return priority;

    }

    public void setPriority(TodoPriorityEnum priority) {

        this.priority = priority;

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof TodoModel todoModel)) return false;

        return getId() == todoModel.getId() && getProgress() == todoModel.getProgress() && isCompleted() == todoModel.isCompleted() && getEstimatedTime() == todoModel.getEstimatedTime() && Objects.equals(getUserId(), todoModel.getUserId()) && Objects.equals(getNote(), todoModel.getNote()) && Objects.equals(getTags(), todoModel.getTags()) && Objects.equals(getDueDate(), todoModel.getDueDate()) && Objects.equals(getStartDate(), todoModel.getStartDate()) && Objects.equals(getTaskName(), todoModel.getTaskName()) && Objects.equals(getCategory(), todoModel.getCategory()) && Objects.equals(getAssignedTo(), todoModel.getAssignedTo()) && Objects.equals(getCompletedDate(), todoModel.getCompletedDate()) && getPriority() == todoModel.getPriority();

    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getUserId(), getNote(), getTags(), getDueDate(), getProgress(), getStartDate(), getTaskName(), getCategory(), getAssignedTo(), isCompleted(), getEstimatedTime(), getCompletedDate(), getPriority());

    }

    @Override
    public String toString() {

        return "TodoModel{" +
                "id=" + id +
                ", userId=" + userId +
                ", note='" + note + '\'' +
                ", tags='" + tags + '\'' +
                ", dueDate=" + dueDate +
                ", progress=" + progress +
                ", startDate=" + startDate +
                ", taskName='" + taskName + '\'' +
                ", category='" + category + '\'' +
                ", assignedTo='" + assignedTo + '\'' +
                ", completed=" + completed +
                ", estimatedTime=" + estimatedTime +
                ", completedDate=" + completedDate +
                ", priority=" + priority +
                '}';

    }

}
