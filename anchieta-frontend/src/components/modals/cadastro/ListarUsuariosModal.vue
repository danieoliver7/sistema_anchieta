<template>
  <div class="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
    <div class="bg-white rounded-lg shadow-lg w-3/4">
      <div class="flex justify-between items-center border-b p-4">
        <h2 class="text-xl font-semibold text-center w-full">Todos Usuários</h2>
        <button @click="$emit('close')" class="text-gray-500 hover:text-gray-700">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
          </svg>
        </button>
      </div>
      <div class="p-4">
        <table class="min-w-full bg-white">
          <thead>
            <tr>
              <th class="py-2">Nome</th>
              <th class="py-2">Email</th>
              <th class="py-2">Tipo</th>
              <th class="py-2">Ações</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="user in users" :key="user.id">
              <td class="border px-4 py-2">{{ user.name }}</td>
              <td class="border px-4 py-2">{{ user.email }}</td>
              <td class="border px-4 py-2">{{ user.tipo === 1 ? 'Admin' : 'Usuário' }}</td>
              <td class="border px-4 py-2 flex justify-center">
                <button @click="editUser(user)" class="text-blue-500 hover:text-blue-700 mx-2">
                  <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 20h9" />
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16.5 3.5a2.121 2.121 0 113 3L7 19l-4 1 1-4L16.5 3.5z" />
                  </svg>
                </button>
                <button @click="deleteUser(user.id)" class="text-red-500 hover:text-red-700 mx-2">
                  <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                  </svg>
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import api from '../../../services/api'

export default {
  name: 'ListarUsuariosModal',
  data() {
    return {
      users: []
    }
  },
  created() {
    this.fetchUsers()
  },
  methods: {
    fetchUsers() {
      api.get('/auth/users')
        .then(response => {
          this.users = response.data
        })
        .catch(error => {
          console.error('Erro ao buscar usuários:', error)
          alert('Erro ao buscar usuários')
        })
    },
    editUser(user) {
      const newName = prompt('Editar nome do usuário:', user.name)
      const newEmail = prompt('Editar email do usuário:', user.email)
      const newTipo = prompt('Editar tipo do usuário (1 para Admin, 2 para Usuário):', user.tipo)

      if (newName && newEmail && (newTipo === '1' || newTipo === '2')) {
        api.put(`/auth/users/${user.id}`, {
          name: newName,
          email: newEmail,
          tipo: parseInt(newTipo)
        })
        .then(() => {
          alert('Usuário atualizado com sucesso')
          this.fetchUsers() // Atualiza a lista de usuários
        })
        .catch(error => {
          console.error('Erro ao atualizar usuário:', error)
          alert('Erro ao atualizar usuário')
        })
      } else {
        alert('Informações inválidas para atualização')
      }
    },
    deleteUser(userId) {
      if (confirm('Tem certeza que deseja excluir este usuário?')) {
        api.delete(`/auth/users/${userId}`)
          .then(() => {
            this.users = this.users.filter(user => user.id !== userId)
            alert('Usuário excluído com sucesso')
          })
          .catch(error => {
            console.error('Erro ao excluir usuário:', error)
            alert('Erro ao excluir usuário')
          })
      }
    }
  }
}
</script>

<style scoped>
/* Adicione estilos específicos do modal aqui, se necessário */
</style>
