
<template>
  <div class="music-player">
    <div id="player"></div>
    <div>
      <h2 class="playlist-title">Müzik Çalma Listesi</h2>
      <ul class="song-list">
        <li v-for="song in songs" :key="song.apiKey" @click="playSong(song.apiKey)" class="song-item">
          {{ song.title }}
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      songs: [],
      player: null
    };
  },
  mounted() {

    window.onYouTubeIframeAPIReady = () => {
      this.player = new window.YT.Player("player", {
        playerVars: {
          autoplay: 0,
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

    this.getUserPlaylist();
  },
  methods: {
    async getUserPlaylist() {
      const localData = localStorage.getItem("user");
      const username = JSON.parse(localData);
      const userId = username.id;
      const response = await axios.get(
          "http://127.0.0.1:8090/api/v1/playlist/getplaylist/" + userId
      );
      const data = response.data;

      this.songs = data.map(item => {
        const [apiKey, title] = item.split(",");
        return { apiKey, title };
      });
    },

    onPlayerReady(event) {
      event.target.playVideo();
    },

    playSong(apiKey) {
      const selectedSong = this.songs.find(song => song.apiKey === apiKey);
      const videoId = selectedSong.apiKey;

      if (this.player) {
        this.player.loadVideoById(videoId);
      } else {
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
    },

  },
};
</script>
<style scoped>
.music-player {
  text-align: center;
  margin-top: 20px;
}

.playlist-title {
  font-size: 24px;
  margin-bottom: 10px;
}

.song-list {
  list-style: none;
  padding: 0;
}

.song-item {
  cursor: pointer;
  margin-bottom: 5px;
  padding: 5px;
  background-color: #f1f1f1;
  border-radius: 5px;
  transition: background-color 0.3s ease;
}

.song-item:hover {
  background-color: #e0e0e0;
}
</style>