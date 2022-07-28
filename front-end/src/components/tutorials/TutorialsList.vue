<template>
  <div class="list row container mt-3">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <!--        Todo: 수정 시작 F#1 -->
        <input
          type="text"
          class="form-control"
          placeholder="Search by title"
          v-model="searchTitle"
        />
        <div class="input-group-append">
          <button
            class="btn btn-outline-secondary"
            type="button"
            @click="
              page = 1;
              retrieveTutorials();
            "
          >
            Search
          </button>
        </div>
        <!--        Todo : 수정 끝 #1 -->
      </div>
    </div>

    <!--    Todo : page 바 태그 추가 -->
    <div class="col-md-12">
      <div class="mb-3">
        Items per Page:
        <select v-model="pageSize" @change="handlePageSizeChange($event)">
          <option v-for="size in pageSizes" :key="size" :value="size">
            {{ size }}
          </option>
        </select>
      </div>

      <!--      Todo : page bar 추가-->
      <b-pagination
        v-model="page"
        :total-rows="count"
        :per-page="pageSize"
        prev-text="Prev"
        next-text="Next"
        @change="handlePageChange"
      >
      </b-pagination>
    </div>
    <!--    Todo : page 바 끝-->

    <div class="col-md-6">
      <h4>Tutorials List</h4>
      <ul class="list-group">
        <li
          class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(tutorial, index) in tutorials"
          :key="index"
          @click="setActiveTutorial(tutorial, index)"
        >
          {{ tutorial.title }}
        </li>
      </ul>

      <button class="m-3 btn btn-sm btn-danger" @click="removeAllTutorials">
        Remove All
      </button>
    </div>
    <div class="col-md-6">
      <div v-if="currentTutorial">
        <h4>Tutorial</h4>
        <div>
          <label><strong>Title:</strong></label> {{ currentTutorial.title }}
        </div>
        <div>
          <label><strong>Description:</strong></label>
          {{ currentTutorial.description }}
        </div>
        <div>
          <label><strong>Status:</strong></label>
          {{ currentTutorial.published == "Y" ? "Published" : "Pending" }}
        </div>

        <router-link
          :to="'/tutorials/' + currentTutorial.id"
          class="badge badge-primary"
          >Edit</router-link
        >
      </div>
      <div v-else>
        <br />
        <p>Please click on a Tutorial...</p>
      </div>
    </div>
  </div>
</template>

<script>
import TutorialDataService from "@/services/TutorialDataService";

export default {
  name: "tutorials-list",
  data() {
    return {
      tutorials: [],
      currentTutorial: null,
      currentIndex: -1,
      // Todo : title -> searchTitle 변경
      searchTitle: "",
      //  Todo : 아래 변수 4개 추가
      page: 1, // 페이지번호
      count: 0,
      // pageSize : 한페이지당 건수
      pageSize: 3,
      pageSizes: [3, 6, 9], // 한페이지당 건수(3, 6, 9건수)
    };
  },
  methods: {
    // Todo : getRequestParams 추가
    // springboot 쪽으로 URL params 전송
    // ex) http://localhost:8000/api/tutorials?title=''&page=1&size=3
    getRequestParams(searchTitle, page, pageSize) {
      let params = {};

      // searchTitle 값이 있으면 params객체에 title로 저장
      if (searchTitle) {
        params["title"] = searchTitle;
      }
      // page 값이 있으면 params객체에 page 저장
      if (page) {
        params["page"] = page - 1;
      }
      // pageSize 값이 있으면 params객체에 size 저장
      if (pageSize) {
        params["size"] = pageSize;
      }

      return params;
    },
    // Todo : 아래 메소드 수정
    // Todo : getAll() -> getAll(params)
    retrieveTutorials() {
      const params = this.getRequestParams(
        this.searchTitle,
        this.page,
        this.pageSize
      );
      // Todo : 백엔드 쪽으로 전체 데이터 요청(페이징처리)
      TutorialDataService.getAll(params)
        //  성공하면 then 으로 결과데이터가 들어옴
        .then((response) => {
          // Todo : 아래 수정
          // 임시변수 tutorials, totalItems(서버의 결과데이터가 들어옴)
          const { tutorials, totalItems } = response.data;
          this.tutorials = tutorials; // 객체
          this.count = totalItems; // 총건수

          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    // Todo : 메소드 2개 추가 , handlePageChange, handlePageSizeChange
    // 역할 : 현재페이지번호에 해당하는 데이터 다시 조회
    handlePageChange(value) {
      // 페이지번호 저장
      this.page = value;
      // 다시 데이터 조회
      this.retrieveTutorials();
    },
    // 역할 : 페이지당건수가 변경되면 다시 조회하는 메소드
    handlePageSizeChange(event) {
      // 한 페이지 당 건수 저장
      this.pageSize = event.target.value; // 셀렉트박스 변경시 값 가져옴
      this.page = 1;
      // 다시 데이터 조회
      this.retrieveTutorials();
    },
    refreshList() {
      this.retrieveTutorials();
      this.currentTutorial = null;
      this.currentIndex = -1;
    },

    setActiveTutorial(tutorial, index) {
      this.currentTutorial = tutorial;
      this.currentIndex = index;
    },

    removeAllTutorials() {
      TutorialDataService.deleteAll()
        .then((response) => {
          console.log(response.data);
          this.refreshList();
        })
        .catch((e) => {
          console.log(e);
        });
    },
    // Todo : searchTitle() 주석으로 막음 또는 삭제
    //
    // searchTitle() {
    //   TutorialDataService.findByTitle(this.title)
    //     .then(response => {
    //       this.tutorials = response.data;
    //       console.log(response.data);
    //     })
    //     .catch(e => {
    //       console.log(e);
    //     });
    // }
  },
  mounted() {
    this.retrieveTutorials();
  },
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}

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