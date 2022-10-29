package com.colutti.lettucePubSub.common;

import io.lettuce.core.codec.ByteArrayCodec;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.codec.StringCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ChannelCommon {

    @Value("${redis.channel.name}")
    protected String redisChannelName;

    protected RedisCodec<String, byte[]> redisCodecPartner() {
        RedisCodec<String, byte[]> codec = RedisCodec.of(new StringCodec(), new ByteArrayCodec());
        return codec;
    }

}
