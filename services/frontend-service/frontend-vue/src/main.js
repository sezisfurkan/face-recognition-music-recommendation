import { createApp } from "vue";
import router from "./routers/router";
import { createPinia } from 'pinia';
import App from "./App.vue";
import PrimeVue from "primevue/config";

import "primevue/resources/themes/saga-blue/theme.css";
import "primevue/resources/primevue.min.css";
import "primeicons/primeicons.css";
import "primeflex/primeflex.css";

import Button from "primevue/button";
import MenuBar from "primevue/menubar";
import InputText from "primevue/inputtext";
import Password from "primevue/password";
import Toast from 'primevue/toast';
import ToastService from 'primevue/toastservice';
import Chart from 'primevue/chart';




const app = createApp(App);

app.use(PrimeVue);
app.use(router);
app.use(ToastService);

app.use(createPinia());
app.component("Button", Button);
app.component("Menubar", MenuBar);
app.component("InputText", InputText);
app.component("Password", Password);
app.component('Toast', Toast);
app.component('Chart', Chart);


app.mount("#app");

