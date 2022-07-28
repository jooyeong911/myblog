import http from '../http-common'

// springboot 연결하기 위한 메소드 정의
class BoardDataService {
    // 모든 회원 조회
    getAll(params) {
        return http.get("/boards",{params});
    }

//    id로 회원 조회
    get(idx) {
        return http.get(`/boards/${idx}`);
    }

//    회원 데이터를 생성(insert 문 호출)
    create(data) {
        return http.post("/boards", data);
    }

//    회원 데이터를 수정 요청(update 문 호출)
    update(idx, data) {
        return http.put(`/boards/${idx}`, data);
    }

//    회원 데이터를 삭제 요청(update 문 호출)
    delete(idx) {
        return http.put(`/boards/deletion/${idx}`);
    }
}

export default new BoardDataService()







