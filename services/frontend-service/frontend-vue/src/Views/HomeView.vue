<!--  <template>
    <div>
      <h1 style="color: #fff;">Kamera Aç/Kapat</h1>
      <button @click="openCamera">Kamera Aç</button>
      <button @click="closeCamera">Kamera Kapat</button>
      <video ref="videoElement" autoplay></video>
    </div>
  </template>
  <script>
  export default {
    methods: {
      async openCamera() {
        try {
          this.stream = await navigator.mediaDevices.getUserMedia({ video: true });
          this.$refs.videoElement.srcObject = this.stream;
        } catch (error) {
          console.error(error);
        }
      },
      closeCamera() {
        if (this.stream) {
          const tracks = this.stream.getTracks();
          tracks.forEach(track => track.stop());
          this.$refs.videoElement.srcObject = null;
          this.stream = null;
        }
      }
    }
  }
  </script>
  <style>

  </style>-->

<template>
  <div>
    <h1 style="color: #fff;">Kamera Aç/Kapat</h1>
    <button @click="openCamera">Kamera Aç</button>
    <button @click="closeCamera">Kamera Kapat</button>
    <video ref="videoElement" autoplay></video>
  </div>
</template>

<script>
export default {
  data() {
    return {
      stream: null,
      intervalId: null
    };
  },
  methods: {
    async openCamera() {
      try {
        this.stream = await navigator.mediaDevices.getUserMedia({ video: true });
        this.$refs.videoElement.srcObject = this.stream;
        this.intervalId = setInterval(this.detectFaces, 1000);
      } catch (error) {
        console.error(error);
      }
    },
    closeCamera() {
      clearInterval(this.intervalId);
      if (this.stream) {
        const tracks = this.stream.getTracks();
        tracks.forEach(track => track.stop());
        this.$refs.videoElement.srcObject = null;
        this.stream = null;
      }
    },
    async detectFaces() {
      try {
        const canvas = document.createElement('canvas');
        const video = this.$refs.videoElement;
        canvas.width = video.videoWidth;
        canvas.height = video.videoHeight;
        const ctx = canvas.getContext('2d');
        ctx.drawImage(video, 0, 0, canvas.width, canvas.height);
        const blob = await new Promise(resolve => canvas.toBlob(resolve, 'image/jpeg', 0.95));
        const formData = new FormData();
        formData.append('image', blob, 'image.jpg');
        const response = await fetch('http://127.0.0.1:5000/detect', {
          method: 'POST',
          body: formData
        });
        const data = await response.json();
        this.$refs.videoElement.src = `data:image/jpeg;base64,${data.content}`;
      } catch (error) {
        console.error(error);
      }
    }
  }
};
//
</script>

<style>

</style>
