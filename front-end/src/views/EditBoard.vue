<template>
  <div v-if="currentBoard" class="mt-5" style="margin-left : 50px;">
      <div class="mb-3">
        <label for="title" class="form-label">제목</label>
        <input
          type="text"
          class="form-control"
          id="title"
          required
          name="title"
          v-model="currentBoard.title"
        />
      </div>
     <div class="mb-3">
        <label for="content" class="form-label">글내용</label>
        <input
          type="text"
          class="form-control"
          id="content"
          required
          name="content"
          v-model="currentBoard.content"
        />
      </div>
      <div class="mb-3">
        <label for="writer" class="form-label">작성자</label>
        <input
          type="text"
          class="form-control"
          id="writer"
          required
          name="writer"
          v-model="currentBoard.writer"
        />
      </div>

    <div class="mb-3">
      <button @click="updateBoard"
              class="btn btn-primary me-3">수정</button>
      <button @click="deleteBoard"
              class="btn btn-danger">삭제</button>
    </div>

<!--    수정버튼을 클릭후 성공했을때 아래가 보임 -->
    <div class="alert alert-success"
         role="alert"
         v-if="message"
    >
      {{ message }}
    </div>
  </div>
</template>

<script>
// 화면 설명 : 회원정보 수정 /삭제 화면
import BoardDataService from "@/services/BoardDataService";

export default {
  name: "edit-board",
  data() {
    return {
      // 객체 ( 회원정보 )
      currentBoard: null,
      message: ''
    }
  },
  methods: {
    // id에 해당하는 회원정보를 조회하는 서비스를 요청(springboot)
    getBoard(idx) {
      // axios 통신이용 서비스 호출( springboot )
      BoardDataService.get(idx)
      // 성공하면 then으로 결과 데이터가 들어옴( response.data )
          .then(response => {
            this.currentBoard = response.data;
          })
    //  실패하면 catch로 에러 데이터가 들어옴
          .catch(e => {
            alert(e);
          })
    },
  //  회원정보 수정 서비스 요청( springboot )
    updateBoard() {
    //  axios 통신으로 수정 서비스 요청( springboot )
      BoardDataService
          .update(this.currentBoard.idx, this.currentBoard )
    //  성공하면 then 으로 결과 데이터가 들어옴
          .then(() => {
            this.message = '수정 성공';
          })
    //  실패하면 catch 로 에러 데이터가 들어옴
          .catch(e => {
            alert(e);
          })
    },
  //  회원삭제 서비스 요청(springboot)
    deleteBoard() {
    //  axios 이용해서 회원 삭제 요청( springboot )
      BoardDataService.delete(this.currentBoard.idx)
    //  성공하면 then으로 결과 데이터가 들어옴
          .then(() => {
            // router/index.js 안에 name
            // 화면 이동 : customers 화면으로 이동됨
            this.$router.push({ name: 'board'});
          })
    //  실패하면 catch로 에러 데이터가 들어옴
          .catch(e => {
            alert(e);
          //  console.log(e)
          })
    }
  },
//  화면이 뜨면 실행되는 이벤트
//  http://localhost:8080/:id
//  $route : router/index.js , params.id
  mounted() {
    this.getBoard(this.$route.params.idx);
  }
}
</script>