package com.example.myblog.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Complain {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String destinationPage;
    private String deleteYn;
    private String insertTime;
    private String updateTime;
    private String deleteTime;
}











