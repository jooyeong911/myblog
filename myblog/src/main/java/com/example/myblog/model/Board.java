package com.example.myblog.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Board {
    private Long idx;
    private String title;
    private String content;
    private String writer;
    private int viewCnt;
    private String noticeYn;
    private String deleteYn;
    private String secretYn;
    private String insertTime;
    private String updateTime;
    private String deleteTime;
}











