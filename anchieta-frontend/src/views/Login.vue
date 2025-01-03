<template>
  <div class="min-h-screen flex items-center justify-center bg-gray-50">
    <div class="bg-white p-8 rounded-xl shadow-sm w-full max-w-[400px] mx-4">
      <!-- Logo/Título -->
      <div class="text-center mb-8">
        <h1 class="text-3xl font-semibold text-gray-800">Resenha Bar Anchieta</h1>
      </div>

      <!-- Formulário -->
      <form @submit.prevent="fazerLogin" class="space-y-6">
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-1">Email</label>
          <input 
            type="email" 
            v-model="login"
            class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#A52A2A]"
            placeholder="Digite seu email"
          >
        </div>

        <div>
          <label class="block text-sm font-medium text-gray-700 mb-1">Senha</label>
          <input 
            type="password" 
            v-model="senha"
            class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#A52A2A]"
            placeholder="Digite sua senha"
          >
        </div>

        <button 
          type="submit"
          class="w-full py-3 bg-[#A52A2A] text-white rounded-lg hover:opacity-90 font-medium"
          :disabled="loading"
        >
          {{ loading ? 'Entrando...' : 'Entrar' }}
        </button>

        <p v-if="error" class="text-red-500 text-sm text-center">
          {{ error }}
        </p>
      </form>
    </div>
  </div>
</template>

<script>
import api from '../services/api'
import Cookies from 'js-cookie'

export default {
  name: 'Login',
  data() {
    return {
      login: '',
      senha: '',
      loading: false,
      error: null
    }
  },
  methods: {
    fazerLogin() {
      this.loading = true
      this.error = null
      
      api.post('/auth/login', {
        email: this.login,
        password: this.senha
      })
      .then(response => {
        // Salva no cookie por 7 dias
        Cookies.set('token', response.data.token, { expires: 7 })
        Cookies.set('user', response.data.email, { expires: 7 })
        
        window.isAuthenticated = true
        this.$router.push('/dashboard')
      })
      .catch(error => {
        this.error = error.response?.data || 'Erro ao fazer login'
        alert(this.error)
      })
      .finally(() => {
        this.loading = false
      })
    }
  }
}
</script> 