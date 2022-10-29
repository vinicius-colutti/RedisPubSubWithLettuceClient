package com.colutti.lettucePubSub.producer.controller;

import com.colutti.lettucePubSub.dto.MessageDTO;
import com.colutti.lettucePubSub.producer.service.IRedisLettuceProducerService;
import com.colutti.lettucePubSub.producer.service.impl.RedisLettuceProducerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/productions")
public class RedisLettuceController {

    @Autowired
    private IRedisLettuceProducerService redisLettuceProducerService;

    @PostMapping
    public String publishEvent(@RequestBody MessageDTO messageDTO) throws IOException {
        redisLettuceProducerService.producerMessage(messageDTO);
        return messageDTO.getName() + " published!";
    }

    @GetMapping("/{messageId}")
    public MessageDTO getMessageFromCache(@PathVariable Integer messageId) throws ExecutionException, InterruptedException {
        return redisLettuceProducerService.getMessageFromRedisCache(messageId);
    }

}
