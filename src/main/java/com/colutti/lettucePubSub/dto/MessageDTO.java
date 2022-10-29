package com.colutti.lettucePubSub.dto;

import java.io.Serializable;

public class MessageDTO implements Serializable {

    private static final long serialVersionUID = 1234567L;

    private Integer id;

    private String name;

    public MessageDTO() {
    }

    public MessageDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
