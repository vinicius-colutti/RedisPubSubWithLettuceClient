package com.colutti.lettucePubSub.consumer.listener;

import com.colutti.lettucePubSub.common.ChannelCommon;
import com.colutti.lettucePubSub.common.MessageCommon;
import com.colutti.lettucePubSub.dto.MessageDTO;
import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.RedisPubSubAdapter;
import io.lettuce.core.pubsub.RedisPubSubListener;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class RedisLettuceConsumer extends ChannelCommon {

    @Autowired
    private MessageCommon messageCommon;

    @Autowired
    RedisClient redisClient;

    @Bean
    public void listenerChannelRedis() {
        StatefulRedisPubSubConnection<String, byte[]> con = redisClient.connectPubSub(redisCodecPartner());

        RedisPubSubListener<String, byte[]> listener = new RedisPubSubAdapter<>() {
            @Override
            public void message(String channel, byte[] message) {
                System.out.println(String.format("Channel: %s", channel));
                MessageDTO messageDTO = messageCommon.transformytesInMessageObject(message);
                System.out.println(String.format("Message name: %s", messageDTO.getName()));
            }
        };

        con.addListener(listener);
        RedisPubSubCommands<String, byte[]> sync = con.sync();
        sync.subscribe(redisChannelName);
    }

}
