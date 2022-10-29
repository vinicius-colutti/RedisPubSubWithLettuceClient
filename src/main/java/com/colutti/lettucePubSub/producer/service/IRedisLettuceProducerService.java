package com.colutti.lettucePubSub.producer.service;

import com.colutti.lettucePubSub.dto.MessageDTO;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface IRedisLettuceProducerService {
    void producerMessage(MessageDTO messageDTO) throws IOException;
    MessageDTO getMessageFromRedisCache(Integer messageId) throws ExecutionException, InterruptedException;
    void storageMessageOnCache(MessageDTO messageDTO) throws IOException;
}
