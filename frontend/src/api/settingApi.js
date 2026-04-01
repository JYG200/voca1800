import api from './axios.js'

export const getSettings = () =>
  api.get('/settings')

export const updateSettings = (data) =>
  api.put('/settings', data)

export const resetProgress = () =>
  api.post('/settings/reset-progress')
