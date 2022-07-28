<template>
  <div class="container mt-3">
    <!-- TODO: 수정시작 -->
    <!-- search 관련 입력박스 시작 -->
    <div class="col-md-8">
          <h3>자유게시판</h3>

      <div class="input-group mb-3">
        <input
          type="text"
          class="form-control"
          placeholder="제목을 검색하세요."
          v-model="searchTitle"
        />
        <div class="input-group-append">
          <button
            class="btn btn-outline-secondary"
            type="button"
            @click="
              page = 1;
              retrieveBoards();
            "
          >
            Search
          </button>
        </div>
      </div>
    </div>
    <!-- search 관련 입력박스 끝 -->

    <!-- TODO: 페이지 바 시작 -->
    <div class="col-md-12">
      <div class="md-3">
        Items per Page :
        <select v-model="pageSize" @change="handlePageSizeChange($event)">
          <!-- pageSizes : [3,6,9] -->
          <option v-for="size in pageSizes" :key="size" :value="size">
            {{ size }}
          </option>
        </select>
      </div>
    </div>
    <!-- <button type="button" class="btn btn-primary mb-3 btn float-right" onclick="location.href='AddBoard.vue'">
      글쓰기
    </button>
    <button type="button" class="btn btn-danger mb-3 btn float-right">
      삭제
    </button> -->
    <table class="table">
      <thead>
        <tr>
          <th scope="col">No</th>
          <th scope="col">제목</th>
          <th scope="col">내용</th>
          <th scope="col">작성자</th>
        </tr>
      </thead>
      <!--      조회 데이터 생성 부분-->
      <!-- <tbody> -->
      <tbody v-for="(board, index) in boards" :key="index">
        <tr>
          <td>{{ board.idx }}</td>
          <td>{{ board.title }}</td>
          <td>{{ board.content }}</td>
          <td>{{ board.writer }}</td>
          <td>
            <a :href="'/boards/' + board.idx" class="btn btn-success">
              수정
            </a>
          </td>
        </tr>
      </tbody>
    </table>
    <!-- page : 현재페이지, count : 총 개수, pageSize : 1페이지당 개수 -->
    <b-pagination
      v-model="page"
      :total-rows="count"
      :per-page="pageSize"
      prev-text="Prev"
      next-text="Next"
      @change="handlePageChange"
    >
    </b-pagination>

    <!-- 페이지네이션 끝 -->
  </div>
</template>
<script>
import BoardDataService from "@/services/BoardDataService";

/* eslint-disable */
export default {
  name: "boards",
  data() {
    return {
      boards: [],
      // Todo: title => searchEmail 변경 수정
      searchTitle: "",
      // Todo:아래 변수 추가
      page: 1,
      count: 0,
      pageSize: 3,
      pageSizes: [3, 6, 9],
    };
  },
  methods: {
    getRequestParams(searchTitle, page, pageSize) {
      let params = {};
      // searchEmail값이 있으면 매개변수로 전송

      if (searchTitle) {
        params["title"] = searchTitle;
      }

      if (page) {
        params["page"] = page - 1;
      }

      if (pageSize) {
        params["size"] = pageSize;
      }

      // ex ) 3개가 다 들어왔다면
      // params {
      // "email" : "forbob@naver.com"
      // }

      return params;
    },
    retrieveBoards() {
      const params = this.getRequestParams(
        this.searchTitle,
        this.page,
        this.pageSize
      );

      BoardDataService.getAll(params)
        .then((response) => {
          const { boards, totalItems } = response.data;
          this.boards = boards;
          this.count = totalItems;
        })
        //  실패하면 catch로 에러메세지가 들어옴
        .catch((e) => {
          console.log(e);
        });
    },
    handlePageChange(value) {
      // 현재 페이지 변경
      this.page = value;
      // 재조회
      this.retrieveBoards();
    },
    handlePageSizeChange(event) {
      this.pageSize = event.target.value; //select 박스의 값 (3,6,9)
      this.page = 1;

      this.retrieveBoards();
    },
  },
  //화면이 뜨자마자 실행되는 이벤트(모든 회원조회가 실행됨)
  mounted() {
    this.retrieveBoards();
  },
};
</script>


<style>
/* 페이지네이션 색상 변경 */
.page-link {
  color: #000;
  background-color: #fff;
  border: 1px solid #ccc;
}

.page-item.active .page-link {
  z-index: 1;
  color: #555;
  font-weight: bold;
  background-color: #f1f1f1;
  border-color: #ccc;
}

.page-link:focus,
.page-link:hover {
  color: #000;
  background-color: #fafafa;
  border-color: #ccc;
}
</style>
