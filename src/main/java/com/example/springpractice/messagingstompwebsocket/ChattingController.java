package com.example.springpractice.messagingstompwebsocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChattingController {

    @MessageMapping("/hello")
    @SendTo("/topic/chattings")
    public Chatting chatting(HelloMessage message) throws InterruptedException {
        Thread.sleep(1000);
        return new Chatting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
