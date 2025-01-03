<template>
  <div v-if="isOpen" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
    <div class="bg-white rounded-xl shadow-lg w-full max-w-[500px] mx-4">
      <!-- Header -->
      <div class="flex items-center justify-between p-4 border-b">
        <h2 class="text-xl font-semibold text-gray-800">Cadastrar Vendas do Dia</h2>
        <button @click="fecharModal" class="text-gray-500 hover:text-gray-700">
          <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
          </svg>
        </button>
      </div>

      <!-- Conteúdo -->
      <div class="p-6">
        <form @submit.prevent="salvarVenda">
          <div class="space-y-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">Descrição</label>
              <input 
                type="text" 
                v-model="venda.descricao"
                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#28a745]"
                placeholder="Ex: Venda de produtos"
              >
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">Valor</label>
              <input 
                type="number" 
                v-model="venda.valor"
                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#28a745]"
                placeholder="R$ 0,00"
                step="0.01"
              >
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
              class="px-4 py-2 text-white bg-[#28a745] rounded-lg hover:opacity-90"
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
import api from '../../../services/api'
import Cookies from 'js-cookie'

export default {
  name: 'ModalVendas',
  props: {
    isOpen: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      venda: {
        descricao: '',
        valor: '',
        data: new Date().toISOString().substr(0, 10)
      },
      loading: false,
      error: null
    }
  },
  methods: {
    fecharModal() {
      this.$emit('fechar')
      this.limparFormulario()
    },
    async salvarVenda() {
      this.loading = true
      this.error = null
      
      try {
        const userEmail = Cookies.get('user')
        const dataHoraAtual = new Date();
        
        const response = await api.post('/api/sales', {
          descricao: this.venda.descricao,
          amount: this.venda.valor,
          data: dataHoraAtual.toISOString()
        }, {
          headers: {
            'user': userEmail
          }
        })
        
        console.log('Venda registrada:', response.data)
        this.$emit('venda-salva', response.data)
        this.fecharModal()
      } catch (error) {
        this.error = error.response?.data || 'Erro ao registrar venda'
        alert(this.error)
      } finally {
        this.loading = false
      }
    },
    limparFormulario() {
      this.venda = {
        descricao: '',
        valor: '',
        data: new Date().toISOString().substr(0, 10)
      }
      this.error = null
    }
  }
}
</script> 