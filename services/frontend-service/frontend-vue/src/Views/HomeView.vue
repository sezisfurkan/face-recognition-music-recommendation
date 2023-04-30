<template>
  <div class="menubar">
    <Toast />
    <h1 style="color: #fff;" class="menubar">Kamera Aç/Kapat</h1>
    <button @click="openCamera">Kamera Aç</button>
    <button @click="closeCamera">Kamera Kapat</button>
    <button @click="start">Start</button>
    <button @click="stop">Stop</button>
    <button v-if="showPlaylistButton" @click="goToPlaylist">Şarkıya Git</button>
    <div v-if="running" class="countdown">{{ count }}</div>
    <div v-if="!running" class="message">{{ message }}</div>
    <div v-if="timerStarted">{{ remainingTime }}</div>
    <div v-if="timerEnded">{{ errorData }}</div>
    <canvas ref="canvasElement" style="display:none;"></canvas>
    <div class="video-container">
      <video ref="videoElement" autoplay></video>
      <canvas ref="overlayCanvas" class="overlay-canvas"></canvas>
    </div>
<!--    <div v-for="(color, emotion) in emotions" :style="{ backgroundColor: color, textAlign: 'right' }">-->
<!--      {{ emotion }}-->
<!--    </div>-->


    <div id="error-box" class="error">
      <div v-if="errorData">{{ errorData }}</div>
    </div>

    <div class="card flex justify-content-center">
      <Chart type="pie" :data="chartData" :options="chartOptions" class="w-full md:w-30rem" />
    </div>




  </div>
</template>
<script>
let currnetmode ='';
export default {

  data() {
    return {
      emotions: {
        'angry': 'red',
        'disgusted': 'yellow',
        'fearful': 'purple',
        'happy': 'green',
        'neutral': 'grey',
        'sad': 'blue',
        'surprised': 'orange'
      },
      stream: null,
      intervalId: null,
      errorData: null, // Yeni eklenen değişken
      timerStarted: false,
      timerEnded: false,
      remainingTime: 10,
      running: false,
      count: 0,
      message: '',
      showPlayListButton: false,

      chartData: null,
      chartOptions: {
        plugins: {
          legend: {
            labels: {
              usePointStyle: true
            }
          }
        }
      }
    };


  },
  mounted() {
    this.init();
    this.chartData = this.setChartData();

  },
  methods: {
    start() {
      this.running = true;
      this.count = 0;
      this.intervalId = setInterval(() => {
        this.count++;
        if (this.count >= 5) {
          this.stop();
          this.closeCamera();
          this.message =currnetmode.message+" playlist will come";
          this.showInfo();
          this.showPlayListButton = true;
        }

      }, 1000);
    },
    stop() {
      clearInterval(this.intervalId);
      this.running = false;
      this.count = 0;
      this.message = '';

    },
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
        this.errorData = error.message; // errorData değişkenine hata mesajını aktar
      }
    },
    closeCamera() {
      // Kamerayı durdur ve analiz fonksiyonunu iptal et
      clearInterval(this.intervalId);
      if (this.stream) {
        const tracks = this.stream.getTracks();
        tracks.forEach(track => track.stop());
        this.$refs.videoElement.srcObject = null;
        this.stream = null;
      }

      // Sarı kareyi temizle
      const overlayCanvas = this.$refs.overlayCanvas;
      const context = overlayCanvas.getContext('2d');
      context.clearRect(0, 0, overlayCanvas.width, overlayCanvas.height);

      this.errorData = null;
      const errorDiv = document.getElementById('error-box');
      errorDiv.style.display = 'none';
    },
    showError(errorData) {
      const errorBox = document.getElementById('error-box');
      errorBox.innerText = JSON.stringify(errorData, null, 2);
      errorBox.style.display = 'block';
    },
    showMode(mode) {
      return JSON.stringify(mode);
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

        /*if (response.status === 500) {
          const errorData = await response.json();
          console.error('Error from server:', errorData);
          return;
        }*/
        if (response.status === 200) {
          const errorData = await response.json();
          currnetmode =errorData;
        /*  console.error('Error from server:',errorData);
          console.error('deneme',this.showMode(currnetmode.message))*/
          this.showMode(currnetmode.message);
          this.showError(errorData.message);
          return;
        }
        if (response.status === 500) {
          const errorData = await response.json();
          currnetmode =errorData;
          /*  console.error('Error from server:',errorData);
            console.error('deneme',this.showMode(currnetmode.message))*/
         /* this.showMode(currnetmode.message);*/
          this.showError(errorData.message);
          return;
        }
        const data = await response.json();

        this.analyzeFaces(data);
      } catch (error) {
        /*console.error(error);*/
      }
    },
    async analyzeFaces() {
      const overlayCanvas = this.$refs.overlayCanvas;
      const video = this.$refs.videoElement;
      overlayCanvas.width = video.videoWidth;
      overlayCanvas.height = video.videoHeight;
      const context = overlayCanvas.getContext('2d');

      const options = new faceapi.TinyFaceDetectorOptions({
        inputSize: 608, // Bu değeri artırarak hassasiyeti artırın (128, 160, 224, 320, 416, 512, 608)
        scoreThreshold: 0.1, // Bu değeri düşürerek hassasiyeti artırın (0 ile 1 arasında)
      });
      while (video.paused !== true) {
        const detections = await faceapi.detectAllFaces(video, options).withFaceLandmarks();

        // Canvas üzerinde yüzü çevreleyen kareyi çiz
        context.clearRect(0, 0, overlayCanvas.width, overlayCanvas.height);
        detections.forEach((detection) => {
          const box = detection.detection.box;
      /*    const modeColors = {
            '"angry"': 'red',
            '"disgusted"': 'green',
            '"fearful"' : 'purple',
            '"happy"': 'yellow',
            '"neutral"': 'gray',
            '"sad"': 'blue',
            '"surprised"': 'orange'
          };
          context.strokeStyle = modeColors[xmode]; // Moda göre rengi ayarla
          */
          const xmode = this.showMode(currnetmode.message)
          if(xmode =='"angry"'){
            context.strokeStyle ='red';
          }else if(xmode =='"disgusted"'){
            context.strokeStyle ='yellow';
          } else if(xmode =='"fearful"'){
            context.strokeStyle ='purple';
          }else if(xmode =='"happy"'){
            context.strokeStyle ='green';
          }else if(xmode =='"sad"'){
            context.strokeStyle ='blue';
          }else if(xmode =='"neutral"'){
            context.strokeStyle ='grey';
          }else if(xmode =='"surprised"'){
            context.strokeStyle ='orange';
          }
          context.lineWidth = 2;
          context.strokeRect(box.x, box.y, box.width, box.height);
        });

        // 100ms bekle ve tekrar analiz yap
        await new Promise((resolve) => setTimeout(resolve, 100));
      }
    },
    showInfo() {
      this.$toast.add({ severity: 'info', summary: 'Info Message', detail: this.message , life: 3000 });
    },

    setChartData() {
      const documentStyle = getComputedStyle(document.body);
      const colors = [
        "red",
        "yellow",
        "purple",
        "green",
        "grey",
        "blue",
        "orange"
      ];

      return {
        labels: ["Angry", "Disgusted", "Fearful", "Happy", "Neutral", "Sad", "Surprised"],
        datasets: [
          {
            data: [100, 100, 100, 100, 100, 100, 100],
            backgroundColor: colors,
            hoverBackgroundColor: colors,
          },
        ],
      };
    },

  },
};
</script>
<style>
button {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 10px;
  cursor: pointer;
}

.countdown {
  font-size: 36px;
  margin-top: 20px;
}

.message {
  font-size: 24px;
  margin-top: 20px;
}
.video-container {
  position: relative;
}

video,
.overlay-canvas {
  position: absolute;
  top: 0;
  left: 0;
}
.error {
  margin-top: 350px;
  background-color: white;


}
.error-box {
  position: absolute;
  textAlign: 'right';
  top: 0;
  right: 0;
  background-color: red;
  color: white;
  padding: 10px;
}
.color{
  right: 50px;

}
.error-container {
  position: fixed;
  top: 10px;
  left: 10px;
  width: 300px;
  background-color: red;
  padding: 10px;
  border: 1px solid white;
  overflow: scroll;
  max-height: 200px;
}

</style>
