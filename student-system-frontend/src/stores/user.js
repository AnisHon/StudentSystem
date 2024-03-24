import {ref, computed, reactive} from 'vue'
import { defineStore } from 'pinia'

export const useUserStore = defineStore(
    'user',
    () => {
        const user = reactive({})
        const getUser = computed(() => user.value)
        const setUser = (u) => user.value = u;
        return {
            user,
            getUser,
            setUser
        }
    })