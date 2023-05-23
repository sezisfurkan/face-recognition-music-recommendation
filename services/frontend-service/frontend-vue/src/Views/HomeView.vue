<!--<template>
  <div class="menubar">
    <Toast/>

    <div style="float: left">
      <h1 style="color: #fff;" class="menubar">Kamera Aç/Kapat</h1>
      <Button @click="openCamera">Kamera Aç</Button>
      <Button @click="closeCamera">Kamera Kapat</Button>
      <Button @click="start">Sayaci baslat</Button>
      <Button @click="stop">Durdur her seyi</Button>
      <Button @click="clearChart">Sayaci sifirla</Button>

      &lt;!&ndash;      <button @click="closeVideoPlayer">Video penceresini kapat</button>&ndash;&gt;

      <div v-if="showPlayListButton">
        &lt;!&ndash;       <Button @click="goToPlayList" >Sarkiyi baslat</Button>&ndash;&gt;
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


      <div v-if="show" class="my-div"></div>
      <div>


        <div id="player"></div>  &lt;!&ndash;    this.videoPlayerOpen =false;&ndash;&gt;

        <div>
          <Button icon="pi pi-chevron-left" @click="previousOption"/>
          <span>{{ selectedOption.key}}</span>
          <Button icon="pi pi-chevron-right" @click="nextOption"/>
        </div>
        <Button @click="addPlayList">Add to Playlist</Button>




      </div>




      <div id="error-box" class="error">
        <div v-if="errorData">{{ errorData }}</div>
      </div>
      <div class="card flex justify-content-center">
        <Chart type="pie" :data="chartData" :options="chartOptions" class="w-full md:w-30rem"/>
      </div>


    </div>
  </div>
</template>-->
<template>
  <div class="surface-0 flex justify-content-center">
    <Toast/>
    <div id="home" class="landing-wrapper overflow-hidden">
      <div class="py-4 px-4 mx-0 md:mx-6 lg:mx-8 lg:px-8 flex align-items-center justify-content-between relative lg:static mb-3">
        <a class="flex align-items-center" href="#"> <img src="demo/images/login/avatar.png" alt="Sakai Logo" height="50" class="mr-0 lg:mr-2" /><span class="text-900 font-medium text-2xl line-height-3 mr-8">SAKAI</span> </a>
        <a
            v-ripple
            v-styleclass="{
                        selector: '@next',
                        enterClass: 'hidden',
                        leaveToClass: 'hidden',
                        hideOnOutsideClick: true
                    }"
            class="cursor-pointer block lg:hidden text-700 p-ripple"
        >
          <i class="pi pi-bars text-4xl"></i>
        </a>
        <div class="align-items-center surface-0 flex-grow-1 justify-content-between hidden lg:flex absolute lg:static w-full left-0 px-6 lg:px-0 z-2" style="top: 120px">
          <ul class="list-none p-0 m-0 flex lg:align-items-center select-none flex-column lg:flex-row cursor-pointer">
            <li>
              <a v-ripple @click="smoothScroll('#hero')" class="flex m-0 md:ml-5 px-0 py-3 text-900 font-medium line-height-3 p-ripple">
                <span>Home</span>
              </a>
            </li>
            <li>
              <a v-ripple @click="smoothScroll('#suggestedsongs')" class="flex m-0 md:ml-5 px-0 py-3 text-900 font-medium line-height-3 p-ripple">
                <span>Songs</span>
              </a>
            </li>
            <li>
              <a v-ripple @click="smoothScroll('#highlights')" class="flex m-0 md:ml-5 px-0 py-3 text-900 font-medium line-height-3 p-ripple">
                <span>Highlights</span>
              </a>
            </li>
            <li>
              <a v-ripple @click="smoothScroll('#pricing')" class="flex m-0 md:ml-5 px-0 py-3 text-900 font-medium line-height-3 p-ripple">
                <span>Pricing</span>
              </a>
            </li>
          </ul>
          <div class="flex justify-content-between lg:block border-top-1 lg:border-top-none surface-border py-3 lg:py-0 mt-3 lg:mt-0">
            <Button label="Login" class="p-button-text p-button-rounded border-none font-light line-height-2 text-blue-500"></Button>
            <Button label="Register" class="p-button-rounded border-none ml-5 font-light text-white line-height-2 bg-blue-500"></Button>
          </div>
        </div>
      </div>

      <div
          id="hero"
          class="flex flex-column pt-4 px-4 lg:px-8 overflow-hidden"
          style="background: linear-gradient(0deg, rgba(255, 255, 255, 0.2), rgba(255, 255, 255, 0.2)), radial-gradient(77.36% 256.97% at 77.36% 57.52%, rgb(238, 239, 175) 0%, rgb(195, 227, 250) 100%);"
      >
        <div class="mx-4 md:mx-8 mt-0 md:mt-4 mb-3">
          <Button @click="openCamera">Kamera Aç</Button>
          <Button @click="closeCamera">Kamera Kapat</Button>
          <Button @click="start">Sayaci baslat</Button>
          <Button @click="stop">Durdur her seyi</Button>
          <Button @click="clearChart">Sayaci sifirla</Button>

        </div>
        <div class="flex justify-content-center md:justify-content-end">

          <div v-if="running" class="md:justify-content-end -mt-3">{{ count }}</div>
          <div v-if="!running" class="message">{{ message }}</div>
          <div v-if="timerStarted">{{ remainingTime }}</div>
          <div v-if="timerEnded">{{ errorData }}</div>
        </div>
        <div class="mx-4 md:mx-8 mt-0 md:mt-4 mb-3">
          <canvas ref="canvasElement" style="display:none;"></canvas>
          <div class="video-container">
            <video ref="videoElement" autoplay></video>
            <canvas ref="overlayCanvas" class="overlay-canvas"></canvas>
          </div>

        </div>
      </div>

      <div id="suggestedsongs" class="py-4 px-4 lg:px-8 mt-5 mx-0 lg:mx-8">
        <div class="grid justify-content-center">
          <div class="col-12 text-center mt-8 mb-4">
            <h2 class="text-900 font-normal mb-2">Suggested Songs</h2>
          </div>

          <div
              class="col-12 mt-8 mb-8 p-2 md:p-8"
              style="border-radius: 20px; background: linear-gradient(0deg, rgba(255, 255, 255, 0.6), rgba(255, 255, 255, 0.6)), radial-gradient(77.36% 256.97% at 77.36% 57.52%, #efe1af 0%, #c3dcfa 100%)"
          >
            <div id="player"></div>
            <Button icon="pi pi-chevron-left" @click="previousOption"/>
            <span>{{ selectedOption.key}}</span>
            <Button icon="pi pi-chevron-right" @click="nextOption"/>
          </div>



          <div
              class="col-12 mt-8 mb-8 p-2 md:p-8"
              style="border-radius: 20px; background: linear-gradient(0deg, rgba(255, 255, 255, 0.6), rgba(255, 255, 255, 0.6)), radial-gradient(77.36% 256.97% at 77.36% 57.52%, #efe1af 0%, #c3dcfa 100%)"
          >
            <div class="flex flex-column justify-content-center align-items-center text-center px-3 py-3 md:py-0">
              <h3 class="text-gray-900 mb-2">Joséphine Miller</h3>
              <span class="text-gray-600 text-2xl">Peak Interactive</span>
              <p class="text-gray-900 sm:line-height-2 md:line-height-4 text-2xl mt-4" style="max-width: 800px">
                “Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.”
              </p>
              <img src="/demo/images/landing/peak-logo.svg" class="mt-4" alt="Company logo" />
            </div>
          </div>
        </div>
      </div>

      <div id="highlights" class="py-4 px-4 lg:px-8 mx-0 my-6 lg:mx-8">
        <div class="text-center">
          <h2 class="text-900 font-normal mb-2">Powerful Everywhere</h2>
          <span class="text-600 text-2xl">Amet consectetur adipiscing elit...</span>
        </div>

        <div class="grid mt-8 pb-2 md:pb-8">
          <div class="flex justify-content-center col-12 lg:col-6 bg-purple-100 p-0 flex-order-1 lg:flex-order-0" style="border-radius: 8px">
            <img src="/demo/images/landing/mockup.svg" class="w-11" alt="mockup mobile" />
          </div>

          <div class="col-12 lg:col-6 my-auto flex flex-column lg:align-items-end text-center lg:text-right">
            <div class="flex align-items-center justify-content-center bg-purple-200 align-self-center lg:align-self-end" style="width: 4.2rem; height: 4.2rem; border-radius: 10px">
              <i class="pi pi-fw pi-mobile text-5xl text-purple-700"></i>
            </div>
            <h2 class="line-height-1 text-900 text-4xl font-normal">Congue Quisque Egestas</h2>
            <span class="text-700 text-2xl line-height-3 ml-0 md:ml-2" style="max-width: 650px"
            >Lectus arcu bibendum at varius vel pharetra vel turpis nunc. Eget aliquet nibh praesent tristique magna sit amet purus gravida. Sit amet mattis vulputate enim nulla aliquet.</span
            >
          </div>
        </div>

        <div class="grid my-8 pt-2 md:pt-8">
          <div class="col-12 lg:col-6 my-auto flex flex-column text-center lg:text-left lg:align-items-start">
            <div class="flex align-items-center justify-content-center bg-yellow-200 align-self-center lg:align-self-start" style="width: 4.2rem; height: 4.2rem; border-radius: 10px">
              <i class="pi pi-fw pi-desktop text-5xl text-yellow-700"></i>
            </div>
            <h2 class="line-height-1 text-900 text-4xl font-normal">Celerisque Eu Ultrices</h2>
            <span class="text-700 text-2xl line-height-3 mr-0 md:mr-2" style="max-width: 650px"
            >Adipiscing commodo elit at imperdiet dui. Viverra nibh cras pulvinar mattis nunc sed blandit libero. Suspendisse in est ante in. Mauris pharetra et ultrices neque ornare aenean euismod elementum nisi.</span
            >
          </div>

          <div class="flex justify-content-end flex-order-1 sm:flex-order-2 col-12 lg:col-6 bg-yellow-100 p-0" style="border-radius: 8px">
            <img src="/demo/images/landing/mockup-desktop.svg" class="w-11" alt="mockup" />
          </div>
        </div>
      </div>

      <div id="pricing" class="py-4 px-4 lg:px-8 my-2 md:my-4">
        <div class="text-center">
          <h2 class="text-900 font-normal mb-2">Matchless Pricing</h2>
          <span class="text-600 text-2xl">Amet consectetur adipiscing elit...</span>
        </div>

        <div class="grid justify-content-between mt-8 md:mt-0">
          <div class="col-12 lg:col-4 p-0 md:p-3">
            <div class="p-3 flex flex-column border-200 pricing-card cursor-pointer border-2 hover:border-primary transition-duration-300 transition-all" style="border-radius: 10px">
              <h3 class="text-900 text-center my-5">Free</h3>
              <img src="/demo/images/landing/free.svg" class="w-10 h-10 mx-auto" alt="free" />
              <div class="my-5 text-center">
                <span class="text-5xl font-bold mr-2 text-900">$0</span>
                <span class="text-600">per month</span>
                <Button label="Get Started" class="block mx-auto mt-4 p-button-rounded border-none ml-3 font-light line-height-2 bg-blue-500 text-white"></Button>
              </div>
              <Divider class="w-full bg-surface-200"></Divider>
              <ul class="my-5 list-none p-0 flex text-900 flex-column">
                <li class="py-2">
                  <i class="pi pi-fw pi-check text-xl text-cyan-500 mr-2"></i>
                  <span class="text-xl line-height-3">Responsive Layout</span>
                </li>
                <li class="py-2">
                  <i class="pi pi-fw pi-check text-xl text-cyan-500 mr-2"></i>
                  <span class="text-xl line-height-3">Unlimited Push Messages</span>
                </li>
                <li class="py-2">
                  <i class="pi pi-fw pi-check text-xl text-cyan-500 mr-2"></i>
                  <span class="text-xl line-height-3">50 Support Ticket</span>
                </li>
                <li class="py-2">
                  <i class="pi pi-fw pi-check text-xl text-cyan-500 mr-2"></i>
                  <span class="text-xl line-height-3">Free Shipping</span>
                </li>
              </ul>
            </div>
          </div>

          <div class="col-12 lg:col-4 p-0 md:p-3 mt-4 md:mt-0">
            <div class="p-3 flex flex-column border-200 pricing-card cursor-pointer border-2 hover:border-primary transition-duration-300 transition-all" style="border-radius: 10px">
              <h3 class="text-900 text-center my-5">Startup</h3>
              <img src="/demo/images/landing/startup.svg" class="w-10 h-10 mx-auto" alt="startup" />
              <div class="my-5 text-center">
                <span class="text-5xl font-bold mr-2 text-900">$1</span>
                <span class="text-600">per month</span>
                <Button label="Try Free" class="block mx-auto mt-4 p-button-rounded border-none ml-3 font-light line-height-2 bg-blue-500 text-white"></Button>
              </div>
              <Divider class="w-full bg-surface-200"></Divider>
              <ul class="my-5 list-none p-0 flex text-900 flex-column">
                <li class="py-2">
                  <i class="pi pi-fw pi-check text-xl text-cyan-500 mr-2"></i>
                  <span class="text-xl line-height-3">Responsive Layout</span>
                </li>
                <li class="py-2">
                  <i class="pi pi-fw pi-check text-xl text-cyan-500 mr-2"></i>
                  <span class="text-xl line-height-3">Unlimited Push Messages</span>
                </li>
                <li class="py-2">
                  <i class="pi pi-fw pi-check text-xl text-cyan-500 mr-2"></i>
                  <span class="text-xl line-height-3">50 Support Ticket</span>
                </li>
                <li class="py-2">
                  <i class="pi pi-fw pi-check text-xl text-cyan-500 mr-2"></i>
                  <span class="text-xl line-height-3">Free Shipping</span>
                </li>
              </ul>
            </div>
          </div>

          <div class="col-12 lg:col-4 p-0 md:p-3 mt-4 md:mt-0">
            <div class="p-3 flex flex-column border-200 pricing-card cursor-pointer border-2 hover:border-primary transition-duration-300 transition-all" style="border-radius: 10px">
              <h3 class="text-900 text-center my-5">Enterprise</h3>
              <img src="/demo/images/landing/enterprise.svg" class="w-10 h-10 mx-auto" alt="enterprise" />
              <div class="my-5 text-center">
                <span class="text-5xl font-bold mr-2 text-900">$999</span>
                <span class="text-600">per month</span>
                <Button label="Get a Quote" class="block mx-auto mt-4 p-button-rounded border-none ml-3 font-light line-height-2 bg-blue-500 text-white"></Button>
              </div>
              <Divider class="w-full bg-surface-200"></Divider>
              <ul class="my-5 list-none p-0 flex text-900 flex-column">
                <li class="py-2">
                  <i class="pi pi-fw pi-check text-xl text-cyan-500 mr-2"></i>
                  <span class="text-xl line-height-3">Responsive Layout</span>
                </li>
                <li class="py-2">
                  <i class="pi pi-fw pi-check text-xl text-cyan-500 mr-2"></i>
                  <span class="text-xl line-height-3">Unlimited Push Messages</span>
                </li>
                <li class="py-2">
                  <i class="pi pi-fw pi-check text-xl text-cyan-500 mr-2"></i>
                  <span class="text-xl line-height-3">50 Support Ticket</span>
                </li>
                <li class="py-2">
                  <i class="pi pi-fw pi-check text-xl text-cyan-500 mr-2"></i>
                  <span class="text-xl line-height-3">Free Shipping</span>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>

      <div class="py-4 px-4 mx-0 mt-8 lg:mx-8">
        <div class="grid justify-content-between">
          <div class="col-12 md:col-2" style="margin-top: -1.5rem">
            <a @click="smoothScroll('#home')" class="flex flex-wrap align-items-center justify-content-center md:justify-content-start md:mb-0 mb-3 cursor-pointer">
              <img src="demo/images/login/avatar.png" alt="footer sections" width="50" height="50" class="mr-2" />
              <h4 class="font-medium text-3xl text-900">SAKAI</h4>
            </a>
          </div>

          <div class="col-12 md:col-10 lg:col-7">
            <div class="grid text-center md:text-left">
              <div class="col-12 md:col-3">
                <h4 class="font-medium text-2xl line-height-3 mb-3 text-900">Company</h4>
                <a class="line-height-3 text-xl block cursor-pointer mb-2 text-700">About Us</a>
                <a class="line-height-3 text-xl block cursor-pointer mb-2 text-700">News</a>
                <a class="line-height-3 text-xl block cursor-pointer mb-2 text-700">Investor Relations</a>
                <a class="line-height-3 text-xl block cursor-pointer mb-2 text-700">Careers</a>
                <a class="line-height-3 text-xl block cursor-pointer text-700">Media Kit</a>
              </div>

              <div class="col-12 md:col-3 mt-4 md:mt-0">
                <h4 class="font-medium text-2xl line-height-3 mb-3 text-900">Resources</h4>
                <a class="line-height-3 text-xl block cursor-pointer mb-2 text-700">Get Started</a>
                <a class="line-height-3 text-xl block cursor-pointer mb-2 text-700">Learn</a>
                <a class="line-height-3 text-xl block cursor-pointer text-700">Case Studies</a>
              </div>

              <div class="col-12 md:col-3 mt-4 md:mt-0">
                <h4 class="font-medium text-2xl line-height-3 mb-3 text-900">Community</h4>
                <a class="line-height-3 text-xl block cursor-pointer mb-2 text-700">Discord</a>
                <a class="line-height-3 text-xl block cursor-pointer mb-2 text-700">Events<img src="/demo/images/landing/new-badge.svg" class="ml-2" /></a>
                <a class="line-height-3 text-xl block cursor-pointer mb-2 text-700">FAQ</a>
                <a class="line-height-3 text-xl block cursor-pointer text-700">Blog</a>
              </div>

              <div class="col-12 md:col-3 mt-4 md:mt-0">
                <h4 class="font-medium text-2xl line-height-3 mb-3 text-900">Legal</h4>
                <a class="line-height-3 text-xl block cursor-pointer mb-2 text-700">Brand Policy</a>
                <a class="line-height-3 text-xl block cursor-pointer mb-2 text-700">Privacy Policy</a>
                <a class="line-height-3 text-xl block cursor-pointer text-700">Terms of Service</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>

import axios from 'axios';


let currnetmode = '';

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
      songInfoForPlaylist:{
        'userId':'',
        'titleOfSong': '',
        'apiKey': '',
    },

      selectedOptionIndex: 0,
      options: [{ key: 'Option 1', value: '' },  { key: 'Option 2', value: '' },  { key: 'Option 3', value: '' },  { key: 'Option 4', value: '' },  { key: 'Option 5', value: '' }],



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
      show: true,
      message: '',
      showPlayListButton: false,
      emotionId: '',
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

  computed: {
    selectedOption() {
      return this.options[this.selectedOptionIndex];
    }
  },

  watch: {
    'selectedOption.key': function() {
      this.selectedOptionChanged();
    },
  },

  methods: {
    smoothScroll(id) {
      document.querySelector(id).scrollIntoView({
        behavior: 'smooth'
      });
    },


    goToPlayList(apiKeyList) {
      console.log(apiKeyList);
      if (apiKeyList.length === 0) {
        console.log("ERROR: apiKeyList is empty");
        return null;
      }

      const options = [];



      for (let i = 0; i < 5; i++) {
        const randomIndex = Math.floor(Math.random() * apiKeyList.length);
        const removedApiKey = apiKeyList.splice(randomIndex, 1)[0];
        options.push({ key: `Option ${i+1}`, value: removedApiKey });
      }

      this.options = options;
      console.log(this.options);

      this.selectedOptionIndex = 0;
      this.playVideo(this.selectedOption.value);
    },
    selectedOptionChanged() {
      this.playVideo(this.selectedOption.value)
    },
    previousOption() {
      this.selectedOptionIndex--;
      if (this.selectedOptionIndex < 0) {
        this.selectedOptionIndex = this.options.length - 1;
      }
    },
    nextOption() {
      this.selectedOptionIndex++;
      if (this.selectedOptionIndex >= this.options.length) {
        this.selectedOptionIndex = 0;
      }
    },
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
      this.toggleDiv();
      this.running = true;
      this.count = 10;
      this.intervalId = setInterval(() => {
        this.count--;
        if (currnetmode.message in this.emotionCounters) {
          this.emotionCounters[currnetmode.message]++;
        }
        if (this.count <= 0) {
          this.stop();
          this.closeCamera();
          console.log(this.emotionCounters);
          this.updateChartData();
          const highestEmotion = Object.entries(this.emotionCounters).reduce((prev, current) => {
            return (prev[1] > current[1]) ? prev : current;
          });


          const emotionId = this.selectMode(highestEmotion[0]);
          emotionId.then(result => {
            const api = this.getApiKey(result)
            api.then(result => {
              this.goToPlayList(result);
              console.log(result);
            });
            this.message = highestEmotion[0] + " mode songs playing";
            this.showInfo();
            console.log(result); // Promise'in sonucunu yazdırır
          });
          this.showPlayListButton = true;
        }

      }, 1000);
    },
    async getApiKey(emotionId) {
      const y = await axios.get('http://127.0.0.1:8090/api/v1/song/emotion/' + emotionId);
      return y.data;
    },


   addPlayList(){
     // Seçili option'un bilgilerini al
     const selectedOption = this.selectedOption;
     const apiKey = selectedOption.value;
     const songInfoForPlaylist = {
       userId: 'your_user_id',
       titleOfSong: selectedOption.key,
       apiKey: apiKey
     };
     console.log(songInfoForPlaylist)
    },

    selectMode(x) {
      if (x == 'angry') {
        return this.getAngryId();
      } else if (x == 'disgusted') {
        return this.getDisgustedId();
      } else if (x == 'fearful') {
        return this.getFearfulId();
      } else if (x == 'happy') {
        return this.getHappyId();
      } else if (x == 'sad') {
        return this.getSadId();
      } else if (x == 'neutral') {
        return this.getNeutralId();
      } else if (x == 'surprised') {
        return this.getSurprisedId();
      } else {
        console.log(x);
      }
    },
    async getAngryId() {
      const y = await axios.get('http://127.0.0.1:8090/api/v1/emotion/emo/"angry"');
      return y.data;
    },
    async getDisgustedId() {
      const y = await axios.get('http://127.0.0.1:8090/api/v1/emotion/emo/"disgusted"');
      return y.data;
    },
    async getFearfulId() {
      const y = await axios.get('http://127.0.0.1:8090/api/v1/emotion/emo/"fearful"');
      return y.data;
    },
    async getHappyId() {
      const y = await axios.get('http://127.0.0.1:8090/api/v1/emotion/emo/"happy"');
      return y.data;

    },
    async getSadId() {
      const y = await axios.get('http://127.0.0.1:8090/api/v1/emotion/emo/"sad"');
      return y.data;
    },
    async getNeutralId() {
      const y = await axios.get('http://127.0.0.1:8090/api/v1/emotion/emo/"neutral"');
      return y.data;
    },
    async getSurprisedId() {
      const y = await axios.get('http://127.0.0.1:8090/api/v1/emotion/emo/"surprised"');
      return y.data;
    },

    stop() {
      clearInterval(this.intervalId);
      this.toggleDiv();
      this.showPlayListButton = false;
      this.running = false;
      this.count = 10;
      this.message = '';
      this.closeCamera();
      this.stopVideo();
    },
    toggleDiv() {
      this.show = !this.show;
    },


    async init() {
      this.videoPlayerOpen = false;
      // Yüz tanıma modelini yükle
      await faceapi.nets.tinyFaceDetector.loadFromUri('/models');
      await faceapi.nets.faceLandmark68Net.loadFromUri('/models');

      // Kamera açıldığında yüz analizini başlat
      this.$refs.videoElement.addEventListener('loadedmetadata', this.analyzeFaces);
    },
    async openCamera() {
      try {
        const response = await fetch('http://127.0.0.1:5000/start', {method: 'POST', mode: 'no-cors'});
        this.stream = await navigator.mediaDevices.getUserMedia({video: true});
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
      this.videoPlayerOpen = true;
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
        this.videoPlayerOpen = false;
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
          currnetmode = errorData;
          this.showMode(currnetmode.message);
          this.showError(errorData.message);
          return;
        }
        if (response.status === 500) {
          const errorData = await response.json();
          currnetmode = errorData;
          this.showError(errorData.message);
          return;
        }
        const data = await response.json();

        this.analyzeFaces(data);
      } catch (error) {
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
          const xmode = this.showMode(currnetmode.message)
          if (xmode == '"angry"') {
            context.strokeStyle = 'red';
          } else if (xmode == '"disgusted"') {
            context.strokeStyle = 'yellow';
          } else if (xmode == '"fearful"') {
            context.strokeStyle = 'purple';
          } else if (xmode == '"happy"') {
            context.strokeStyle = 'green';
          } else if (xmode == '"sad"') {
            context.strokeStyle = 'blue';
          } else if (xmode == '"neutral"') {
            context.strokeStyle = 'grey';
          } else if (xmode == '"surprised"') {
            context.strokeStyle = 'orange';
          }
          context.lineWidth = 2;
          context.strokeRect(box.x, box.y, box.width, box.height);
        });

        // 100ms bekle ve tekrar analiz yap
        await new Promise((resolve) => setTimeout(resolve, 100));
      }
    },

    showInfo() {
      this.$toast.add({severity: 'info', summary: 'Info Message', detail: this.message, life: 3000});
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

.video-container {
    position: relative;
}

.overlay-canvas {
    position: absolute;
    top: 0;
    left: 0;
}
.message {
    font-size: 24px;

}
</style>
