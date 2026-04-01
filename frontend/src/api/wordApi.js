import api from './axios.js'

// Day별 단어 목록 조회
export const getWordsByDay = (dayNumber) =>
  api.get(`/words/days/${dayNumber}`)
