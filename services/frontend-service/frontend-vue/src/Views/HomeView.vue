

<template>
  <div class="menubar">
    <h1 style="color: #fff;" class="menubar">Kamera Aç/Kapat</h1>
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
       /*const response = await fetch('http://127.0.0.1:5000/start', { method: 'POST' });*/
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
 /*   async detectFaces() {
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
              mode: 'no-cors',
              body: formData
          });
          const data = await response.blob(); // .json() yerine .blob() kullanın
          const reader = new FileReader();
          reader.onloadend = () => {
              this.$refs.videoElement.src = reader.result;
          };
          reader.readAsDataURL(data);
      } catch (error) {
        console.error(error);
      }
    }*/
   /* async detectFaces() {
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
          mode: 'no-cors',
          body: formData
        });
        const data = await response.json(); // .blob() yerine .json() kullanın
        const content = data.content;
        const base64ImageData = 'data:image/jpeg;base64,' + content;
        const updatedImage = new Image();
        updatedImage.src = base64ImageData;
        updatedImage.onload = () => {
          ctx.clearRect(0, 0, canvas.width, canvas.height);
          ctx.drawImage(updatedImage, 0, 0, canvas.width, canvas.height);
        };
      } catch (error) {
        console.error(error);
      }
    }*/
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
          mode: 'no-cors',
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
