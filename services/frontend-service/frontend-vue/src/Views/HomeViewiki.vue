
<!--<template>
  <div>
    <div id="player"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      videoId: "PQK5Tnxi1LU",
      player: null
    };
  },
  mounted() {
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
    onPlayerReady(event) {
      event.target.playVideo();
    }
  }
};
</script>-->
<template>
  <div>
    <div id="player"></div>
    <select v-model="selectedOption" @change="onOptionSelected">
      <option value="">Select your mode</option>
      <option value="option1">Anger</option>
      <option value="option2">Disgust</option>
      <option value="option3">Fear</option>
      <option value="option4">Happiness</option>
      <option value="option5">Sadness</option>
      <option value="option6">Surprise</option>
    </select>
  </div>
</template>

<script>
export default {
  data() {
    return {
      videoId: "RrxXfB5NzDE",
      player: null,
      selectedOption: ""
    };
  },
  mounted() {
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
    onPlayerReady(event) {
      event.target.playVideo();
    },
    onOptionSelected() {
      const videoIdMap = {
        option1: "Rv7WZ92eXqQ",
        option2: "boUKG5R5ED0",
        option3: "CHelYRZaieA",
        option4: "qzk-u7GrBKg",
        option5: "8-N3G0pPU64",
        option6: "FfqJHU-1Jrc"
      };

      // Seçilen seçenek için uygun video ID'sini alın
      const selectedVideoId = videoIdMap[this.selectedOption];

      // Video ID'si değiştirilirse, oynatıcıyı güncelleyin
      if (this.player && selectedVideoId !== this.videoId) {
        this.videoId = selectedVideoId;
        this.player.loadVideoById(selectedVideoId);
      }
    }
  }
};
</script>


