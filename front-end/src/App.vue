<template>
  <div id="app">
    <nav
      class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top bg-dark"
      id="sideNav"
    >
      <!--      m(margin), l(left), r(right), t(top), b(bottom), -1~5-->
      <a class="navbar-brand js-scroll-trigger" href="/home2">
        <img
          src="@/assets/img/cat.jpeg"
          class="img-fluid img-profile rounded-circle mx-auto mb-2"
          alt=""
        />
      </a>
      <button
        class="navbar-toggler dropend"
        type="button"
        data-toggle="collapse"
        data-target="#navbarNavDropdown"
        aria-controls="navbarNavDropdown"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav">
          <!-- dropdown #1 시작 -->
          <li class="nav-item dropdown mr-4 dropend">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdownMenuLink"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              About
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <router-link to="/home2" class="dropdown-item">
                Home
              </router-link>
            </div>
          </li>
          <!-- dropdown #1 끝 -->

          <!-- dropdown #2 시작 -->
          <li class="nav-item dropdown mr-4 dropend">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdownMenuLink"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              Tutorial 메뉴
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <router-link to="/tutorials" class="dropdown-item">
                Tutorials
              </router-link>
              <router-link to="/add" class="dropdown-item"> Add </router-link>
              <router-link to="/upload" class="dropdown-item">
                Upload Image
              </router-link>
            </div>
          </li>
          <!-- dropdown #2 끝 -->

          <!-- dropdown #3 시작 -->
          <!-- dropdown #3 끝 -->
          <!-- dropdown #4 시작 -->
          <li class="nav-item dropdown mr-4 dropend">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdownMenuLink"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              Complain
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <router-link to="/complain" class="dropdown-item">
                Complain
              </router-link>
            </div>
          </li>
          <!-- dropdown #4 끝 -->
          <!-- dropdown #5시작 -->
          <li class="nav-item dropdown mr-4 dropend">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdownMenuLink"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              자유게시판
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <router-link to="/board" class="dropdown-item">
                자유게시판
              </router-link>
              <router-link to="/add-board" class="dropdown-item">
                글쓰기
              </router-link>
            </div>
          </li>
          <!-- dropdown #5 끝 -->
        </ul>
      </div>
    </nav>

    <!--    <div class="container mt-3">-->
    <!-- 위의 router-link 있는 메뉴 화면이 아래위치에 표시 -->
    <router-view></router-view>
    <!--    </div>-->
  </div>
</template>

<script>
export default {
  name: "app",
};
</script>

<style>
@import "@/assets/css/styles.css";
</style>
