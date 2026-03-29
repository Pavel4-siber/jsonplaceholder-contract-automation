package com.example.api;

import model.generated.Post;

public class PostTestDataBuilder {
    private Integer userId = 1;
    private String title = "default title";
    private String body = "default body";

    public PostTestDataBuilder withUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public PostTestDataBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public PostTestDataBuilder withBody(String body) {
        this.body = body;
        return this;
    }

    public Post build() {
        Post post = new Post();
        post.setUserId(userId);
        post.setTitle(title);
        post.setBody(body);
        return post;
    }
}