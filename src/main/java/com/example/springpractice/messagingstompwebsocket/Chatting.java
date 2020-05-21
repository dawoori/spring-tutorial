package com.example.springpractice.messagingstompwebsocket;

import lombok.Getter;

@Getter
public class Chatting {

    private String content;

    public Chatting() {

    }

    public Chatting(String content) {
        this.content = content;
    }
}
