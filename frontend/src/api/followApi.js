import api from './axios.js'

export const getFollowing = () =>
  api.get('/follows/following')

export const getFollowers = () =>
  api.get('/follows/followers')

export const follow = (userId) =>
  api.post(`/follows/${userId}`)

export const unfollow = (userId) =>
  api.delete(`/follows/${userId}`)

export const searchUsers = (query) =>
  api.get('/follows/search', { params: { q: query } })
