import Vue from 'vue'
import App from './App.vue'
// add routes
import router from "./routes";

Vue.config.productionTip = false

new Vue({
  router,    // for routing
  render: h => h(App),
}).$mount('#app')
