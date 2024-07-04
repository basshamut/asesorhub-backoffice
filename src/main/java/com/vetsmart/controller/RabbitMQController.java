package com.vetsmart.controller;

import lombok.RequiredArgsConstructor;
import com.vetsmart.service.SenderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RabbitMQController {

    private final SenderService senderService;

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        senderService.send(message);
        return "Message sent: " + message;
    }
}
