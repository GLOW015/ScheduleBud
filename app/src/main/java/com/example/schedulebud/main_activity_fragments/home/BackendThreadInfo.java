package com.example.schedulebud.main_activity_fragments.home;

public class BackendThreadInfo {
    private int thread_id;
    private int user_id;
    private String content;
    private int is_anonymous;

    public BackendThreadInfo(int thread_id, int user_id, String content, int is_anonymous) {
        this.thread_id = thread_id;
        this.user_id = user_id;
        this.content = content;
        this.is_anonymous = is_anonymous;
    }

    public int getThread_id() {
        return thread_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getContent() {
        return content;
    }

    public int getIs_anonymous() {
        return is_anonymous;
    }
}
