import axios from 'axios';

const instance = axios.create({
  baseURL: '/api', 
  timeout: 3000 
});

export default instance;