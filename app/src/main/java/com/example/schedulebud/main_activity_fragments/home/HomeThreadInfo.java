package com.example.schedulebud.main_activity_fragments.home;

public class HomeThreadInfo {
    private int thread_id;
    private int user_id;
    private String title;
    private String content;
    private int like_count;
    private int comment_count;
    private boolean belongsToUser;

    public HomeThreadInfo(int thread_id, int user_id, String content) {
        this.thread_id = thread_id;
        this.user_id = user_id;
        this.title = "Title";
        this.content = content;
        this.like_count = 0;
        this.comment_count = 0;
        this.belongsToUser = false; //currently assumes all are not by user
    }

    public HomeThreadInfo(BackendThreadInfo backendThreadInfo) {
        this.thread_id = backendThreadInfo.getThread_id();
        this.user_id = backendThreadInfo.getUser_id();
        this.title = "Title";
        this.content = backendThreadInfo.getContent();
        this.like_count = 0;
        this.comment_count = 0;
        this.belongsToUser = false; //currently assumes all are not by user
    }

    public int getThread_id() {
        return thread_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public boolean isBelongsToUser() {
        return belongsToUser;
    }
}
