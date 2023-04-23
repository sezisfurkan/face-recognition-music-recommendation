<template>
  <div class="menubar">
    <h1 style="color: #fff;" class="menubar">Kamera Aç/Kapat</h1>
    <button @click="openCamera">Kamera Aç</button>
    <button @click="closeCamera">Kamera Kapat</button>
    <canvas ref="canvasElement" style="display:none;"></canvas>
    <div class="video-container">
      <video ref="videoElement" autoplay></video>
      <canvas ref="overlayCanvas" class="overlay-canvas"></canvas>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      stream: null,
      intervalId: null,
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    async init() {
      // Yüz tanıma modelini yükle
      await faceapi.nets.tinyFaceDetector.loadFromUri('/models');
      await faceapi.nets.faceLandmark68Net.loadFromUri('/models');

      // Kamera açıldığında yüz analizini başlat
      this.$refs.videoElement.addEventListener('loadedmetadata', this.analyzeFaces);
    },
    async openCamera() {
      try {
        const response = await fetch('http://127.0.0.1:5000/start', { method: 'POST', mode: 'no-cors' });
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
        tracks.forEach((track) => track.stop());
        this.$refs.videoElement.srcObject = null;
        this.stream = null;
      }
    },
    async detectFaces() {
      // Önceki kodunuz burada yer alıyor
    },
    async analyzeFaces() {
      const overlayCanvas = this.$refs.overlayCanvas;
      const video = this.$refs.videoElement;
      overlayCanvas.width = video.videoWidth;
      overlayCanvas.height = video.videoHeight;
      const context = overlayCanvas.getContext('2d');

      const options = new faceapi.TinyFaceDetectorOptions({
        inputSize: 416, // Bu değeri artırarak hassasiyeti artırın (128, 160, 224, 320, 416, 512, 608)
        scoreThreshold: 0.5, // Bu değeri düşürerek hassasiyeti artırın (0 ile 1 arasında)
      });

      while (video.paused !== true) {
        const detections = await faceapi.detectAllFaces(video, options).withFaceLandmarks();

        // Canvas üzerinde yüzü çevreleyen kareyi çiz
        context.clearRect(0, 0, overlayCanvas.width, overlayCanvas.height);
        detections.forEach((detection) => {
          const box = detection.detection.box;
          context.strokeStyle = 'yellow';
          context.lineWidth = 2;
          context.strokeRect(box.x, box.y, box.width, box.height);
        });

        // 100ms bekle ve tekrar analiz yap
        await new Promise((resolve) => setTimeout(resolve, 100));
      }
    },
  },
};
</script>
<style>
.video-container {
  position: relative;
}

video,
.overlay-canvas {
  position: absolute;
  top: 0;
  left: 0;
}
</style>