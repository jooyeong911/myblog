<template>
  <div class="ml-5">
    <!--    새 회원가입일 경우 submitted = false -->
    <div v-if="!submitted">
      <div class="mb-3">
        <label for="title" class="form-label">제목</label>
        <input
          type="text"
          class="form-control"
          id="title"
          required
          name="title"
          v-model="board.title"
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
          v-model="board.content"
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
          v-model="board.writer"
        />
      </div>
      <div class="mb-3">
        <button @click="saveBoard" class="btn btn-primary">Submit</button>
      </div>
    </div>

    <div v-else>
      <div class="alert alert-success" role="alert">
        글쓰기가 완료되었습니다!
      </div>
      <button @click="newBoard" class="btn btn-primary">새 글 쓰기</button>
    </div>
  </div>
</template>

<script>
import BoardDataService from "@/services/BoardDataService";

export default {
  name: "add-board",
  data() {
    // 객체 , 변수 초기화
    return {
      board: {
        idx: null,
        title: "",
        content: "",
        writer: "",
      },
      submitted: false,
    };
  },
  methods: {
    // 저장 메소드 호출
    saveBoard() {
      //  회원 임시 객체 만들기
      var data = {
        title: this.board.title,
        content: this.board.content,
        writer: this.board.writer
      }
      //  임시 회원 객체를 서버쪽으로 전달해서 DB에 insert 요청
      BoardDataService.create(data)
          //  성공하면 then으로 들어옴
          .then(response => {
            this.board.idx = response.data.idx;
            this.submitted = true; // DB insert 성공
          })
          //  실패하면 catch으로 에러메세지가 들어옴
          .catch(e => {
            alert(e);
          })

    },
    // 새 회원가입을 위한 빈 form을 만드는 메소드(변수 초기화)
    newBoard() {
      this.submitted = false;
      this.board = {};
    },
  },
};
</script>
