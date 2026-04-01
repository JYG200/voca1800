import api from './axios.js'

export const getDayStatuses = () =>
  api.get('/progress/days')

export const completeDay = (dayNumber) =>
  api.post(`/progress/days/${dayNumber}/complete`)

export const updateWordStatus = (dayNumber, wordId, data) =>
  api.put(`/progress/days/${dayNumber}/words/${wordId}`, data)

export const getStats = () =>
  api.get('/progress/stats')
