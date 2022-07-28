package com.example.myblog.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ResponseMessage {
    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }
}











