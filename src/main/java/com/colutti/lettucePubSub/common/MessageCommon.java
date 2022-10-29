package com.colutti.lettucePubSub.common;

import com.colutti.lettucePubSub.dto.MessageDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.io.IOException;

@Component
public class MessageCommon {

    public byte[] generateBytesOfObjectMessage(MessageDTO messageDTO) throws IOException {
        byte byteArray[]
                = SerializationUtils.serialize(messageDTO);
        return byteArray;
    }

    public MessageDTO transformytesInMessageObject(byte[] bytesObject) {
        MessageDTO receivedMessage = (MessageDTO) SerializationUtils.deserialize(bytesObject);
        return receivedMessage;
    }

}
