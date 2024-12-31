import Cookies from 'js-cookie'

export const auth = {
  isAuthenticated() {
    return !!Cookies.get('token')
  },

  getToken() {
    return Cookies.get('token')
  },

  getUser() {
    return Cookies.get('user')
  },

  login(token, user) {
    Cookies.set('token', token, { expires: 7 })
    Cookies.set('user', user, { expires: 7 })
  },

  logout() {
    Cookies.remove('token')
    Cookies.remove('user')
  }
}

export default auth 