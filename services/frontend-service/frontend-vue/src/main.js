import { createApp } from "vue";
import router from "./routers/router";
import App from "./App.vue";
import PrimeVue from "primevue/config";

import "primevue/resources/themes/saga-blue/theme.css";
import "primevue/resources/primevue.min.css";
import "primeicons/primeicons.css";
import "primeflex/primeflex.css";

import Button from "primevue/button";

const app = createApp(App);

app.use(PrimeVue);
app.use(router);
app.component("Button", Button);

app.mount("#app");
