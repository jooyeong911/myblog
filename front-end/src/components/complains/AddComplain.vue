<template>
  <div class="container mt-3">
    <div v-if="!submitted">
      <div class="mb-3">
        <label for="title" class="form-label">제목</label>
        <input type="text" class="form-control" id="title" required name="title" v-model="complain.title">
      </div>
      <div class="mb-3">
        <label for="content" class="form-label">불편 사항</label>
        <input type="text" class="form-control" id="comtent" required name="comtent" v-model="complain.content">
      </div>
      <div class="mb-3">
        <label for="destinationPage" class="form-label">대상 페이지</label>
        <input type="text" class="form-control" id="destinationPage" required name="destinationPage" v-model="complain.destinationPage">
      </div>
            <div class="mb-3">
        <label for="writer" class="form-label">작성자</label>
        <input type="text" class="form-control" id="writer" required name="writer" v-model="complain.writer">
      </div>
      <div class="mb-3">
        <button @click="saveComplain" class="btn btn-primary">작성하기</button>
      </div>
    </div>

    <div v-else>
      <div class="alert alert-success" role="alert">
        작성되었습니다!
      </div>
      <button @click="newComplain" class="btn btn-primary">새글쓰기</button>
    </div>
  </div>
</template>

<script>
import ComplainDataService from "@/services/ComplainDataService";

export default {
  name: "add-complain",
  data() {
    // 객체 , 변수 초기화
    return {
      complain: {
        id: null,
        title: "",
        content: "",
        destinationPage: "",
        writer: ""
      },
      submitted: false
    }
  },
  methods: {
    saveComplain() {
      var data = {
        title: this.complain.title,
        content: this.complain.content,
        destinationPage: this.complain.destinationPage,
        writer: this.complain.writer
      }
      ComplainDataService.create(data)
          .then(response => {
            this.complain.id = response.data.id;
            this.submitted = true;
          })
          .catch(e => {
            alert(e);
          })

    },
    newComplain() {
      this.submitted = false;
      this.complain = {};
    }
  }
}
</script>








