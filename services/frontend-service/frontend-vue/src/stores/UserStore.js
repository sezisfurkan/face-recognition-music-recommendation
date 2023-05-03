import { defineStore } from 'pinia';

export const useUserStore = defineStore('userStore', {
    state: () => {
        return { userId: '', userName:"", currentMode:"", };
    },

});
