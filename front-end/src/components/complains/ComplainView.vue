<template>
  <div class="container mt-3">
      <h1 class="mt-3">complain</h1>

    <div class="col-md-8">
      <div class="input-group mb-3">
        <input
          type="text"
          class="form-control"
          placeholder="Search by Title"
          v-model="searchTitle"
        />
        <div class="input-group-append">
          <button
            class="btn btn-outline-secondary"
            type="button"
            @click="
              page = 1;
              retrieveComplains();
            "
          >
            Search
          </button>
        </div>
      </div>
    </div>

    <div class="col-md-12">
      <div class="md-3" v-if="false">
        Items per Page :
        <select v-model="pageSize" @change="handlePageSizeChange($event)">
          <!-- pageSizes : [3,6,9] -->
          <option v-for="size in pageSizes" :key="size" :value="size">
            {{ size }}
          </option>
        </select>
      </div>

    </div>

            <router-link style="float : right;"
          :to="'/complains/'" class="btn btn-dark m-3"
          >글쓰기</router-link
        >


    <table class="table table-sm">
      <thead>
        <tr>
          <th scope="col">No</th>
          <th scope="col">제목</th>
          <th scope="col">불편 사항</th>
          <th scope="col">대상 페이지</th>
          <th scope="col">작성자</th>
        </tr>
      </thead>
      <tbody v-for="(complain, index) in complains" :key="index">
        <tr>
          <td>{{index}}</td>
          <td>{{ complain.title }}</td>
          <td>{{ complain.content }}</td>
          <td>{{ complain.destinationPage }}</td>
          <td>{{ complain.writer }}</td>
        </tr>
      </tbody>
    </table>
          <b-pagination
        v-model="page"
        :total-rows="count"
        :per-page="pageSize"
        prev-text="이전"
        next-text="다음"
        @change="handlePageChange"
      >
      </b-pagination>
  </div>
</template>

<script>
import ComplainDataService from "@/services/ComplainDataService";

/* eslint-disable */
export default {
  name: "complains",
  // 변수를 초기화 하기
  data() {
    return {
      complains: [],
      searchTitle: "",
      page: 1,
      count: 0,
      pageSize: 9,
      pageSizes: [9]
    };
  },
  methods: {
    getRequestParams(searchTitle, page, pageSize) {
      let params = {};

      if (searchTitle) {
        params["title"] = searchTitle;
      }

      if (page) {
        params["page"] = page - 1;
      }

      if (pageSize) {
        params["size"] = pageSize;
      }
      return params;
    },

    retrieveComplains() {
      const params = this.getRequestParams(
        this.searchTitle,
        this.page,
        this.pageSize
      );

      ComplainDataService.getAll(params)
        .then((response) => {
          const { complains, totalItems } = response.data;
          this.complains = complains;
          this.count = totalItems;
        })
        .catch((e) => {
          alert(e);
        });
    },
    // todo : 신규 메소드 2개 추가
    handlePageChange(value) {
      this.page = value;
      this.retrieveComplains();
    },
    handlePageSizeChange(event) {
      this.pageSize = event.target.value; 
      this.page = 1;
      this.retrieveComplains();
    },
  },
  mounted() {
    this.retrieveComplains();
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
 font-weight:bold;
 background-color: #f1f1f1;
 border-color: #ccc;
 
}

.page-link:focus, .page-link:hover {
  color: #000;
  background-color: #fafafa; 
  border-color: #ccc;
}
</style>
