package com.example.api;

import io.restassured.response.Response;
import model.generated.Post;

public class  PostClient extends BaseClient {

    public Response getPost(int id) {
        return request().get("/posts/" + id);
    }

    public Response createPost(Post post) {
        return request().body(post).post("/posts");
    }

    public Response updatePost(int id, Post post) {
        return request().body(post).put("/posts/" + id);
    }

    public Response deletePost(int id) {
        return request().delete("/posts/" + id);
    }
}
