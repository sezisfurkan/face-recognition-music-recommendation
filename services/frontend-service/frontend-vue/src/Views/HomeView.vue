<template>
  <div class="menubar">
    <Toast />

    <div style="float: left">
      <h1 style="color: #fff;" class="menubar">Kamera Aç/Kapat</h1>
      <button @click="openCamera">Kamera Aç</button>
      <button @click="closeCamera">Kamera Kapat</button>
      <button @click="start">Sayaci baslat</button>
      <button @click="stop">Durdur her seyi</button>
      <button @click="clearChart">Sayaci sifirla </button>
      <!--      <button @click="closeVideoPlayer">Video penceresini kapat</button>-->

      <div v-if="showPlayListButton">
        <!--       <Button @click="goToPlayList" >Sarkiyi baslat</Button>-->
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
      <div id="player" ></div>  <!--    this.videoPlayerOpen =false;-->
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
      emotionCounters: {
        'angry': 0,
        'disgusted': 0,
        'fearful': 0,
        'happy': 0,
        'neutral': 0,
        'sad': 0,
        'surprised': 0
      },
      emotions: {
        'angry': 'red',
        'disgusted': 'yellow',
        'fearful': 'purple',
        'happy': 'green',
        'neutral': 'grey',
        'sad': 'blue',
        'surprised': 'orange'
      },
      videoId: null,
      videoPlayerOpen: false,
      player: null,
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
    // Youtube API'i yukle
    window.onYouTubeIframeAPIReady = () => {
      this.player = new window.YT.Player("player", {
        videoId: this.videoId,
        playerVars: {
          autoplay: 1,
          controls: 1
        },
        events: {
          onReady: this.onPlayerReady
        }
      });
    };

    const tag = document.createElement("script");
    tag.src = "https://www.youtube.com/iframe_api";
    const firstScriptTag = document.getElementsByTagName("script")[0];
    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

  },



  methods: {
    updateChartData() {
      const newData = {
        labels: Object.keys(this.emotionCounters),
        datasets: [
          {
            data: Object.values(this.emotionCounters),
            backgroundColor: Object.values(this.emotions),
          },
        ],
      };

      this.chartData = newData;
    },

    start() {
      this.running = true;
      this.count = 0;
      this.intervalId = setInterval(() => {
        this.count++;
        if (currnetmode.message in this.emotionCounters) {
          this.emotionCounters[currnetmode.message]++;
        }
        if (this.count >= 10) {

          this.stop();
          this.closeCamera();
          console.log(this.emotionCounters);
          this.updateChartData();
          const highestEmotion = Object.entries(this.emotionCounters).reduce((prev, current) => {
            return (prev[1] > current[1]) ? prev : current;
          });


          const emotionId= this.selectMode(highestEmotion[0]);
          emotionId.then(result => {
            const api =this.getApiKey(result)
            api.then(result=> {
              this.goToPlayList(result);
              console.log(result);
            });
            this.message = highestEmotion[0]+" mode songs playing";
            this.showInfo();
            console.log(result); // Promise'in sonucunu yazdırır
          });
          this.showPlayListButton = true;
        }

      }, 1000);
    },


    emotionChart(x){
      return x;

    },
    goToPlayList(apiKeyList){
      /*   this.$router.push({ name: "home2" ,params: { yData: y } });*/
      if (apiKeyList.length === 0) {
        console.log("ERROR: apiKeyList is empty");
        return null; // Boş dizi için null döndür
      }
      const randomIndex = Math.floor(Math.random() * apiKeyList.length);
      const randomApiKey = apiKeyList[randomIndex];

      this.playVideo(randomApiKey);
    },
    async getApiKey(emotionId){
      const y= await axios.get('http://127.0.0.1:8090/api/v1/song/emotion/'+emotionId);
      return y.data;
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

    stop() {
      clearInterval(this.intervalId);

      this.showPlayListButton= false;
      this.running = false;
      this.count = 0;
      this.message = '';
      this.closeCamera();
      this.stopVideo();
    },
    async init() {
      this.videoPlayerOpen=false;
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
    clearChart() {
      Object.keys(this.emotionCounters).forEach(emotion => {
        this.emotionCounters[emotion] = 0;
      });
      this.updateChartData();
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
    onPlayerReady(event) {
      event.target.playVideo();
    },
    playVideo(videoId) {
      this.videoPlayerOpen= true;
      // Video ID'si değiştirilirse, oynatıcıyı güncelleyin
      if (this.player && videoId !== this.videoId) {
        this.videoId = videoId;
        this.player.loadVideoById(videoId);
      } else if (!this.player) {
        // İlk kez bir video seçiliyorsa, oynatıcıyı oluşturun ve videoyu oynatın
        this.videoId = videoId;
        this.player = new window.YT.Player("player", {
          videoId: videoId,
          playerVars: {
            autoplay: 1,
            controls: 1
          },
          events: {
            onReady: this.onPlayerReady
          }
        });
      }
      // Videoyu oynat
      this.player.playVideo();
    },
    stopVideo() {

      if (this.player) {
        this.videoPlayerOpen =false;
        /* this.player.destroy(); // player nesnesini sayfadan kaldırır*/
        this.player.stopVideo();

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
    resetPage() {
      // Reset all necessary data properties to their default values
      this.running = false;
      this.count = 0;
      this.message = '';
      this.showPlayListButton = false;
      this.chartData = this.setChartData();
      this.timerStarted = false;
      this.timerEnded = false;
      this.remainingTime = 10;
      this.errorData = null;
      // Call any other methods you need to reset the page to its initial state
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
      this.$toast.add({ severity: 'info', summary: 'Info Message', detail:this.message , life: 3000 });
    },
    closeVideoPlayer() {
      if (this.player) {
        this.player.destroy(); // player nesnesini sayfadan kaldırır
        this.videoPlayerOpen = false; // videoPlayerOpen değişkenini false yaparak video penceresinin kapandığını işaretler
      }
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
        labels: Object.keys(this.emotionCounters),
        datasets: [
          {
            data: Object.values(this.emotionCounters),
            backgroundColor: Object.values(this.emotions),
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
  margin-top: 30px;
  background-color: white;


}


</style>