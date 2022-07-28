<template>
  <div class="container mt-3">
    <div class="row justify-content-md-center">
      <div class="col-8">
        <label class="btn btn-default p-0">
          <input
            type="file"
            accept="image/*"
            ref="file"
            @change="selectImage"
          />
        </label>
      </div>

      <div class="col-4">
        <button
          class="btn btn-success btn-sm float-right"
          :disabled="!currentImage"
          @click="upload"
        >
          Upload
        </button>
      </div>
    </div>

    <div v-if="currentImage" class="progress">
      <div
        class="progress-bar progress-bar-info"
        role="progressbar"
        :style="{ width: progress + '%' }"
        :aria-valuenow="progress"
        aria-valuemin="0"
        aria-valuemax="100"
      >
        {{ progress }}%
      </div>
    </div>

    <div v-if="previewImage">
      <div>
        <img class="preview my-3" :src="previewImage" alt="" />
      </div>
    </div>

    <div v-if="message" class="alert alert-secondary" role="alert">
      {{ message }}
    </div>

    <div class="card mt-3" 
        v-for="(image, index) in imageInfo" 
        :key="index"
    >
    <div class="card-header">이미지 번호 : {{ image.id }}</div>
      <div class="row">
        <div class="col-md-5 col-md-offset-0">
          <figure>
            <img class="img-thumbnail" :src="image.url" />
          </figure>
        </div>
        <div class="col-md-6 col-md-offset-0 description">
          <p v-html="image.name"></p>
          <p class="price">{{ image.url }}</p>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import UploadService from "../services/UploadFilesService";

export default {
  name: "upload-image",
  data() {
    // 변수 초기화
    return {
      currentImage: undefined, 
      previewImage: undefined, 
      progress: 0, 
      message: "",
      imageInfo: [], 
    };
  },
  methods: {
    selectImage() {
      this.currentImage = this.$refs.file.files.item(0);
      this.previewImage = URL.createObjectURL(this.currentImage);
      this.progress = 0;
      this.message = "";
    },
    upload() {
      this.progress = 0;

      UploadService.upload(this.currentImage, (event) => {
        this.progress = Math.round((100 * event.loaded) / event.total);
      })
        .then((response) => {
          this.message = response.data.message;

          return UploadService.getFiles();
        })
        .then((image) => {
          this.imageInfo = image.data;
        })

        .catch((err) => {
          this.progress = 0;
          this.message = "Could not upload the image!" + err;
          this.currentImage = undefined;
        });
    },
  },
  mounted() {
    UploadService.getFiles()
      .then((response) => {
        this.imageInfo = response.data;
      });
  },
};
</script>

<style>
.preview {
  max-width: 200px;
}
</style>