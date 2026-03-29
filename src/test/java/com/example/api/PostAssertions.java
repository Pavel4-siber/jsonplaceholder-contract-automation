package com.example.api;

import model.generated.Post;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostAssertions {

    public static void assertPostEquals(Post expected, Post actual) {
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getBody(), actual.getBody());
    }
}
