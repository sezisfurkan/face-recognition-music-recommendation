<template>
  <div>
    <h1>Kamera Aç/Kapat</h1>
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
<style></style>
