import api from './axios.js'

export const getMyProfile = () =>
  api.get('/users/me')

export const updateProfile = (data) =>
  api.put('/users/me', data)

export const deleteAccount = () =>
  api.delete('/users/me')
