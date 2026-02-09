import api from './axios.js';


export const fetchTestWords = async () => {
  try {
    const response = await api.get('/test/words');
    return response.data; 
  } catch (error) {
    console.error('실패:', error);
    return '백엔드 연결 실패';
  }
};