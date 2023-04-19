
<template>
  <div class="menubar">
    <h1 style="color: #fff;" class="menubar">Kamera Aç/Kapat</h1>
    <button @click="openCamera">Kamera Aç</button>
    <button @click="closeCamera">Kamera Kapat</button>
    <canvas ref="canvasElement" style="display:none;"></canvas>
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
        const response = await fetch('http://127.0.0.1:5000/start', { method: 'POST', mode: 'no-cors'});
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
        const canvas = this.$refs.canvasElement;
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
        if (response.status === 500) {
          const errorData = await response.json();
          console.error('Error from server:', errorData);
          return;
        }
        const data = await response.blob();
        const reader = new FileReader();
        reader.onloadend = () => {
          this.$refs.videoElement.src = reader.result;
        };
        reader.readAsDataURL(data);
      } catch (error) {
        console.error(error);
      }
    }
  }
};
</script>