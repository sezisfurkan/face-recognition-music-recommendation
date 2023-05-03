<template>
  <div class="menubar">
    <Toast />

    <div style="float: left">
    <h1 style="color: #fff;" class="menubar">Kamera Aç/Kapat</h1>
    <button @click="openCamera">Kamera Aç</button>
    <button @click="closeCamera">Kamera Kapat</button>
    <button @click="start">Start</button>
    <button @click="stop">Stop</button>
     <div v-if="showPlayListButton">
       <Button @click="goToPlayList" >Sarkiya git </Button>

     </div>

    <div v-if="running" class="countdown">{{ count }}</div>
    <div v-if="!running" class="message">{{ message }}</div>
    <div v-if="timerStarted">{{ remainingTime }}</div>
    <div v-if="timerEnded">{{ errorData }}</div>
    <canvas ref="canvasElement" style="display:none;"></canvas>
    <div class="video-container">
      <video ref="videoElement" autoplay></video>
      <canvas ref="overlayCanvas" class="overlay-canvas"></canvas>
    </div>


    </div>

  <div style="float: right">

    <div id="error-box" class="error">
      <div v-if="errorData">{{ errorData }}</div>
    </div>
    <div class="card flex justify-content-center">
      <Chart type="pie" :data="chartData" :options="chartOptions" class="w-full md:w-30rem" />
    </div>


  </div>




  </div>
</template>
<script>

import axios from 'axios';


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
      emotionId:'',
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
          this.showInfo();
          const emotionId= this.selectMode(currnetmode.message);
          emotionId.then(result => {
            this.message =result +"  "+ currnetmode.message+" playlist will come";
            console.log(result); // Promise'in sonucunu yazdırır
          });


          this.showPlayListButton = true;
        }

      }, 1000);
    },
    goToPlayList(y){


      this.$router.push({ name: "home2" });
    },
    selectMode(x){
      if(x=='angry'){
        return this.getAngryId();
      }else if(x =='disgusted'){
        return this.getDisgustedId();
      } else if(x=='fearful'){
        return this.getFearfulId();
      }else if(x =='happy'){
        return this.getHappyId();
      }else if(x=='sad'){
        return this.getSadId();
      }else if(x =='neutral'){
        return this.getNeutralId();
      }else if(x =='surprised'){
        return this.getSurprisedId();
      }else{
        console.log(x);
      }

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
      this.showPlayListButton = false;
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


        if (response.status === 200) {
          const errorData = await response.json();
          currnetmode =errorData;
          this.showMode(currnetmode.message);
          this.showError(errorData.message);
          return;
        }
        if (response.status === 500) {
          const errorData = await response.json();
          currnetmode =errorData;
          this.showError(errorData.message);
          return;
        }
        const data = await response.json();

        this.analyzeFaces(data);
      } catch (error) {
      }
    },
/*     saveEmotionId(){
      const emotion =currnetmode.message;
         if(emotion =='"angry"'){
        emotionId =this.getAngryId();
      }else if(emotion =='"disgusted"'){
        emotionId = this.getDisgustedId();
           console.log(emotionId.data);
      } else if(emotion =='"fearful"'){
        emotionId = this.getFearfulId();
           console.log(emotionId.data);
      }else if(emotion =='"happy"'){
        emotionId = this.getHappyId();
           console.log(emotionId.data);
      }else if(emotion =='"sad"'){
        emotionId = this.getSadId();
           console.log(emotionId.data);
      }else if(emotion =='"neutral"'){
        emotionId = this.getNeutralId();
           console.log(emotionId.data);
      }else if(emotion =='"surprised"'){
        emotionId =  this.getSurprisedId();
           console.log(emotionId.data);
      }
    },*/

    async getAngryId(){
      const y= await axios.get('http://127.0.0.1:8090/api/v1/emotion/emo/"angry"');
      return y.data;
    },
    async getDisgustedId(){
      const y= await axios.get('http://127.0.0.1:8090/api/v1/emotion/emo/"disgusted"');
      return y.data;
    },
    async getFearfulId(){
      const y= await axios.get('http://127.0.0.1:8090/api/v1/emotion/emo/"fearful"');
      return y.data;
    },
    async getHappyId(){
      const y= await axios.get('http://127.0.0.1:8090/api/v1/emotion/emo/"happy"');
      return y.data;

    },
    async getSadId(){
      const y= await axios.get('http://127.0.0.1:8090/api/v1/emotion/emo/"sad"');
      return y.data;
    },
    async getNeutralId(){
      const y= await axios.get('http://127.0.0.1:8090/api/v1/emotion/emo/"neutral"');
      return y.data;
    },
    async getSurprisedId(){
      const y= await axios.get('http://127.0.0.1:8090/api/v1/emotion/emo/"surprised"');
      return y.data;
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
<style scoped>


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


</style>
