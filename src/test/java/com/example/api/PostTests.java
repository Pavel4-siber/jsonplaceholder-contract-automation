package com.example.api;

import jdk.jfr.Description;
import model.generated.Post;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.example.api.PostAssertions.assertPostEquals;
import static io.qameta.allure.Allure.step;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PostTests {
    private final PostClient client = new PostClient();

    @Test
    @Tag("smoke")
    @Description("GET post")
    void getPost() {
        Response response = step("Get post", () -> client.getPost(1));

        Post post = response.as(Post.class);

        step("Validate schema", () ->
                response.then().assertThat()
                        .body(matchesJsonSchemaInClasspath("schemas/post.json")));

        assertThat(post.getId()).isEqualTo(1);
        assertThat(response).extracting(Response::statusCode).isEqualTo(200);
    }

    @Test
    @Tag("regression")
    void createPost() {
        Post request = new PostTestDataBuilder().build();

        Response response = step("Create post", () -> client.createPost(request));
        Post responsePost = response.as(Post.class);

        assertPostEquals(request, responsePost);
    }

    @Test
    void updatePost() {
        Post request = new PostTestDataBuilder()
                .withTitle("updated")
                .build();

        Response response = client.updatePost(1, request);
        Post updated = response.as(Post.class);

        assertPostEquals(request, updated);
    }

    @Test
    void deletePost() {
        Response response = client.deletePost(1);
        assertThat(response.statusCode()).isEqualTo(200);
    }
}
