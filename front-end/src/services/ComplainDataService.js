import http from '../http-common'

// springboot 연결하기 위한 메소드 정의
class ComplainDataService {
    // 모든 회원 조회
    getAll(params) {
        // http://localhost:8080/customers
        return http.get("/complains",{params});
    }

//    id로 회원 조회
    get(id) {
        return http.get(`/complains/${id}`);
    }

//    회원 데이터를 생성(insert 문 호출)
    create(data) {
        return http.post("/complains", data);
    }

//    회원 데이터를 수정 요청(update 문 호출)
    update(id, data) {
        return http.put(`/complains/${id}`, data);
    }

//    회원 데이터를 삭제 요청(update 문 호출)
    delete(id) {
        return http.put(`/complains/deletion/${id}`);
    }
}

export default new ComplainDataService()







