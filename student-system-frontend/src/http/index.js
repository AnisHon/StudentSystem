import axios from 'axios'
import { ElMessage } from 'element-plus'
import { useUserStore } from '@/stores/user.js'

const userStore = useUserStore()
const baseUrl = "http://localhost:8080"
axios.defaults.baseURL = baseUrl

const defaultError = () => {ElMessage.error("发生未知错误")}
const defaultFailure = (message) => {
    ElMessage.warning(message)
}

function post(url, data, success, failure = defaultFailure, error = defaultError) {
    axios.post(url, data,
        {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            withCredentials: true
        }).then(({data}) => {
            if (data.code === 200) {
                success(data)
            }  else {
                failure(data.code, data.message);
            }
    }).catch(error)
}


function get(url, success, failure = defaultFailure, error = defaultError) {
    axios.get(url,
        {
            withCredentials: true
        }).then(({data}) => {
        if (data.code === 200) {
            success(data.data)
        }  else {
            failure(data.code, data.message);
        }
    }).catch(error)
}

export {
    get,
    post
}






