import api from './axios.js'

// 회원가입
export const signup = (data) =>
  api.post('/auth/signup', data)

export const login = (data) =>
  api.post('/auth/login', data)

export const logout = () =>
  api.post('/auth/logout')
