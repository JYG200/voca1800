import api from './axios.js'

export const getRanking = () =>
  api.get('/ranking')
