
<template>
  <div>
    <div id="player"></div>
    <div>
      <h2>How do you feel today?</h2>
      <button @click="playVideo('Rv7WZ92eXqQ')">Anger</button>
      <button @click="playVideo('boUKG5R5ED0')">Disgust</button>
      <button @click="playVideo('CHelYRZaieA')">Fear</button>
      <button @click="playVideo('qzk-u7GrBKg')">Happiness</button>
      <button @click="playVideo('8-N3G0pPU64')">Sadness</button>
      <button @click="playVideo('FfqJHU-1Jrc')">Surprise</button>
      <button @click="stopVideo">Stop</button>
    </div>
  </div>
</template>
<!--0kr_0RC78Ko-->
<!--<button @click="playVideo('Rv7WZ92eXqQ')">Anger</button>-->

<script>
export default {
  data() {
    return {
      emotions: [
        { name: "Anger", videoId: "Rv7WZ92eXqQ" },
        { name: "Disgust", videoId: "boUKG5R5ED0" },
        { name: "Fear", videoId: "CHelYRZaieA" },
        { name: "Happiness", videoId: "qzk-u7GrBKg" },
        { name: "Sadness", videoId: "8-N3G0pPU64" },
        { name: "Surprise", videoId: "FfqJHU-1Jrc" }
      ],
      selectedEmotion: null,
      player: null
    };
  },
  mounted() {
    // Youtube API'i yukle
    window.onYouTubeIframeAPIReady = () => {
      this.player = new window.YT.Player("player", {
        videoId: this.selectedEmotion.videoId,
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
    onPlayerReady(event) {
      event.target.playVideo();
    },
    getSongIdByMood(){},

    playVideo(videoId, audioOnly) {
      // Seçilen duyguya göre videoyu/sesi oynat
      this.selectedEmotion = this.emotions.find(e => e.videoId === videoId);

      // Oynatıcıyı güncelle
      if (this.player) {
        if (audioOnly) {
          this.player.loadVideoById({
            videoId: this.selectedEmotion.videoId,
            suggestedQuality: "small",
            playerVars: {
              autoplay: 1,
              controls: 1,
              iv_load_policy: 3,
              loop: 1,
              start: 0,
              end: 1,
              fs: 0, // tam ekran butonunu kaldır
              playsinline: 1 // iPhone ve iPad için gereklidir
            }
          });
        } else {
          this.player.loadVideoById(this.selectedEmotion.videoId);
        }
      } else {
        if (audioOnly) {
          this.player = new window.YT.Player("player", {
            videoId: this.selectedEmotion.videoId,
            suggestedQuality: "small",
            playerVars: {
              autoplay: 1,
              controls: 1,
              iv_load_policy: 3,
              loop: 1,
              start: 0,
              end: 1,
              fs: 0, // tam ekran butonunu kaldır
              playsinline: 1 // iPhone ve iPad için gereklidir
            },
            events: {
              onReady: this.onPlayerReady
            }
          });
        } else {
          this.player = new window.YT.Player("player", {
            videoId: this.selectedEmotion.videoId,
            playerVars: {
              autoplay: 1,
              controls: 1
            },
            events: {
              onReady: this.onPlayerReady
            }
          });
        }
      }
    }, stopVideo() {
      if (this.player) {
        if (confirm("Videoyu durdurmak istediğinize emin misiniz?")) {
          this.player.stopVideo();
        }
      }
    }
  }
};
</script>

<style scoped>
#player {
  margin: 0 auto;
  display: block;
  width: 560px;
  height: 315px;
}

div > h2 {
  text-align: center;
  margin-bottom: 1rem;
  color: #fff;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
}

div > button {
  display: inline-block;
  margin: 0.5rem;
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 0.25rem;
  background-color: #007bff;
  color: #fff;
  align-items: center;
  cursor: pointer;
}

div > button:hover {
  background-color: #0069d9;
}
</style>