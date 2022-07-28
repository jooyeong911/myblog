package com.example.myblog.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {

    private Integer page;

    private Integer size;

    private Integer totalItems;

    private Integer totalPages;

    private String title;

    private String email;


    public Criteria() {
        this.page = 0; // 현재 페이지번호
        this.size = 3; // 페이지당 데이터 건수
    }
}











