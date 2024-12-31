<template>
  <div class="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
    <div class="bg-white rounded-lg shadow-lg w-1/2">
      <div class="flex justify-between items-center border-b p-4">
        <h2 class="text-xl font-semibold text-center w-full">Novo Usuário</h2>
        <button @click="$emit('close')" class="text-gray-500 hover:text-gray-700">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
          </svg>
        </button>
      </div>
      <div class="p-4">
        <form @submit.prevent="submitForm">
          <div class="mb-4">
            <label class="block text-gray-700">Nome</label>
            <input v-model="name" type="text" class="w-full px-3 py-2 border rounded" placeholder="Digite o nome" required />
          </div>
          <div class="mb-4">
            <label class="block text-gray-700">Email</label>
            <input v-model="email" type="email" class="w-full px-3 py-2 border rounded" placeholder="Digite o email" required />
          </div>
          <div class="mb-4">
            <label class="block text-gray-700">Senha</label>
            <input v-model="password" type="password" class="w-full px-3 py-2 border rounded" placeholder="Digite a senha" required />
          </div>
          <div class="mb-4">
            <label class="block text-gray-700">Tipo de Usuário</label>
            <div>
              <label class="inline-flex items-center">
                <input type="radio" v-model="userType" value="1" class="form-radio" />
                <span class="ml-2">Administrador</span>
              </label>
              <label class="inline-flex items-center ml-4">
                <input type="radio" v-model="userType" value="2" class="form-radio" />
                <span class="ml-2">Garçom</span>
              </label>
            </div>
          </div>
          <div class="flex justify-end">
            <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">Cadastrar</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import api from '../../../services/api'

export default {
  name: 'NovoCadastroModal',
  data() {
    return {
      name: '',
      email: '',
      password: '',
      userType: '2', // Default para 'Usuário'
      loading: false,
      error: null
    }
  },
  methods: {
    submitForm() {
      // Validação dos campos
      if (!this.name || !this.email || !this.password || !this.userType) {
        alert('Por favor, preencha todos os campos corretamente.');
        return;
      }

      this.loading = true
      this.error = null

      api.post('/auth/register', {
        name: this.name,
        email: this.email,
        password: this.password,
        tipo: this.userType
      })
      .then(response => {
        alert('Usuário cadastrado com sucesso!')
        this.$emit('close') // Fechar o modal após o cadastro
      })
      .catch(error => {
        this.error = error.response?.data || 'Erro ao cadastrar usuário'
        alert(this.error)
      })
      .finally(() => {
        this.loading = false
      })
    }
  }
}
</script>

<style scoped>
/* Adicione estilos específicos do modal aqui, se necessário */
</style>
