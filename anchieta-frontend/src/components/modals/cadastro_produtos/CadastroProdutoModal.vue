<template>
  <div class="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
    <div class="bg-white p-6 rounded-lg shadow-lg w-1/3">
      <h2 class="text-xl font-semibold mb-4">Cadastro de Produto</h2>
      <form @submit.prevent="submitForm">
        <div class="mb-4">
          <label class="block text-gray-700">Nome do Produto</label>
          <input v-model="productName" type="text" class="w-full p-2 border border-gray-300 rounded" required />
        </div>
        <div class="mb-4">
          <label class="block text-gray-700">Preço</label>
          <input v-model="price" type="number" step="0.01" class="w-full p-2 border border-gray-300 rounded" required />
        </div>
        <div class="mb-4">
          <label class="block text-gray-700">Marca</label>
          <input v-model="mark" type="text" class="w-full p-2 border border-gray-300 rounded" required />
        </div>
        <div class="mb-4">
          <label class="block text-gray-700">Validade</label>
          <input v-model="validity" type="date" class="w-full p-2 border border-gray-300 rounded" />
        </div>
        <div class="flex justify-end">
          <button type="button" @click="$emit('close')" class="mr-2 px-4 py-2 bg-gray-300 rounded">Cancelar</button>
          <button type="submit" class="px-4 py-2 bg-blue-500 text-white rounded">Salvar</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import api from '../../../services/api';

export default {
  data() {
    return {
      productName: '',
      price: null,
      mark: '',
      validity: ''
    };
  },
  methods: {
    submitForm() {
      // Validação dos campos
      if (!this.productName || this.price === null || !this.mark) {
        alert('Por favor, preencha todos os campos obrigatórios.');
        return;
      }

      
      api.post('/auth/stocks', {
        productName: this.productName,
        price: this.price,
        mark: this.mark,
        validity: this.validity
      })
        .then(response => {
          alert('Produto cadastrado com sucesso!');
          this.$emit('close'); // Fechar o modal após o cadastro
        })
        .catch(error => {
          const errorMessage = error.response?.data || 'Erro ao cadastrar produto';
          alert(errorMessage);
        });
    }
  }
}
</script>

