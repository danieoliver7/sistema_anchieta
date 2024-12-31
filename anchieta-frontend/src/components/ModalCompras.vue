<template>
  <div v-if="isOpen" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
    <div class="bg-white rounded-xl shadow-lg w-full max-w-[500px] mx-4">
      <!-- Header -->
      <div class="flex items-center justify-between p-4 border-b">
        <h2 class="text-xl font-semibold text-gray-800">Cadastrar Compras do Dia</h2>
        <button @click="fecharModal" class="text-gray-500 hover:text-gray-700">
          <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
          </svg>
        </button>
      </div>

      <!-- Conteúdo -->
      <div class="p-6">
        <form @submit.prevent="salvarCompra">
          <div class="space-y-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">Descrição</label>
              <input 
                type="text" 
                v-model="compra.descricao"
                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#A52A2A]"
                placeholder="Ex: Compra de materiais"
              >
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">Valor</label>
              <input 
                type="number" 
                v-model="compra.valor"
                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#A52A2A]"
                placeholder="R$ 0,00"
                step="0.01"
              >
            </div>
            <div>
             
            </div>
          </div>

          <!-- Botões -->
          <div class="flex justify-end gap-3 mt-6">
            <button 
              type="button"
              @click="fecharModal"
              class="px-4 py-2 text-gray-700 bg-gray-100 rounded-lg hover:bg-gray-200"
            >
              Cancelar
            </button>
            <button 
              type="submit"
              class="px-4 py-2 text-white bg-[#A52A2A] rounded-lg hover:opacity-90"
            >
              Cadastrar
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import api from '../services/api'

export default {
  name: 'ModalCompras',
  props: {
    isOpen: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      compra: {
        descricao: '',
        valor: '',
        data: new Date().toISOString().substr(0, 10)
      }
    }
  },
  methods: {
    fecharModal() {
      this.$emit('fechar')
      this.limparFormulario()
    },
    async salvarCompra() {
      try {
        const response = await api.post('/compras', {
          descricao: this.compra.descricao,
          valor: this.compra.valor,
          data: new Date().toISOString()
        })
        
        console.log('Compra salva:', response.data)
        this.$emit('compra-salva', response.data)
        this.fecharModal()
      } catch (error) {
        alert('Erro ao salvar compra: ' + (error.response?.data?.message || 'Erro desconhecido'))
      }
    },
    limparFormulario() {
      this.compra = {
        descricao: '',
        valor: '',
        data: new Date().toISOString().substr(0, 10)
      }
    }
  }
}
</script> 