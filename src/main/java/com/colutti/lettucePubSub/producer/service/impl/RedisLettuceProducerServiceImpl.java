package com.colutti.lettucePubSub.producer.service.impl;

import com.colutti.lettucePubSub.common.ChannelCommon;
import com.colutti.lettucePubSub.common.MessageCommon;
import com.colutti.lettucePubSub.dto.MessageDTO;
import com.colutti.lettucePubSub.producer.service.IRedisLettuceProducerService;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.codec.ByteArrayCodec;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class RedisLettuceProducerServiceImpl extends ChannelCommon implements IRedisLettuceProducerService {

    @Autowired
    private MessageCommon messageCommon;

    @Autowired
    RedisClient redisClient;

    @Override
    public void producerMessage(MessageDTO messageDTO) throws IOException {
        RedisCodec<String, byte[]> codec = RedisCodec.of(new StringCodec(), new ByteArrayCodec());
        StatefulRedisConnection<String, byte[]> connection = redisClient.connect(codec);
        connection.sync().publish(redisChannelName,
                messageCommon.generateBytesOfObjectMessage(messageDTO)
        );
        storageMessageOnCache(messageDTO);
    }

    @Override
    public MessageDTO getMessageFromRedisCache(Integer messageId) throws ExecutionException, InterruptedException {
        StatefulRedisConnection<String, byte[]> connection = redisClient.connect(redisCodecPartner());
        byte[] bytesMessage = connection.sync().get(messageId.toString());
        return messageCommon.transformytesInMessageObject(bytesMessage);
    }

    @Override
    public void storageMessageOnCache(MessageDTO messageDTO) throws IOException {
        RedisCommands<String, byte[]> redisCommands = redisClient.connect(redisCodecPartner()).sync();
        redisCommands.set(messageDTO.getId().toString(),
                messageCommon.generateBytesOfObjectMessage(messageDTO));
    }
}
