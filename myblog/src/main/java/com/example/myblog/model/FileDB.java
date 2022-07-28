package com.example.myblog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class FileDB {

    private String id;
    private String name;
    private String type;
    private byte[] data;

    public FileDB(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    /**
     * packageName : com.example.customerspring.model
     * fileName : Customer
     * author : ds
     * date : 2022-06-07
     * description :
     * ===========================================================
     * DATE            AUTHOR             NOTE
     * -----------------------------------------------------------
     * 2022-06-07         ds          최초 생성
     */
    @Getter
    @Setter
    @ToString
    public static class Customer {
    //    Long(객체), long(일반자료형) => 8byte 정수형
        private Long id; // 회원아이디
        private String firstName; // 회원 이름
        private String lastName; // 회원 성
        private String email;  // email
        private String phone;  // 핸드폰 번호
        private String deleteYn; // 삭제 여부
        private String insertTime; // 생성날짜
        private String updateTime; // 수정날짜
        private String deleteTime; // 삭제날짜
    }
}







