<template>
  <div class="p-6">
    <!-- Cabeçalho -->


    <!-- Cards de Métricas -->
    <div class="grid grid-cols-4 gap-6 mb-8">
      <div class="bg-white p-6 rounded-xl shadow-sm">
        <div class="flex items-center justify-between mb-2">
          <p class="text-gray-500">Gastos</p>
          <div class="w-8 h-8 bg-blue-100 rounded-lg flex items-center justify-center">
            <svg class="w-5 h-5 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2"/>
            </svg>
          </div>
        </div>
        <h2 class="text-2xl font-bold">R${{ totalGastos30Dias.toLocaleString('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 }) }}</h2>
      </div>
      <div class="bg-white p-6 rounded-xl shadow-sm">
        <div class="flex items-center justify-between mb-2">
          <p class="text-gray-500">Lucros</p>
          <div class="w-8 h-8 bg-purple-100 rounded-lg flex items-center justify-center">
            <svg class="w-5 h-5 text-purple-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path d="M12 8v4l3 3"/>
            </svg>
          </div>
        </div>
        <h2 class="text-2xl font-bold">R${{ totalVendas.toLocaleString('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 }) }}</h2>
      </div>
      <div class="bg-white p-6 rounded-xl shadow-sm">
        <div class="flex items-center justify-between mb-2">
          <p class="text-gray-500">Saldo em conta</p>
          <div class="w-8 h-8 bg-green-100 rounded-lg flex items-center justify-center">
            <svg class="w-5 h-5 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path d="M13 10V3L4 14h7v7l9-11h-7z"/>
            </svg>
          </div>
        </div>
        <h2 class="text-2xl font-bold">{{ formattedBalance }}</h2>
      </div>
      <div class="bg-white p-6 rounded-xl shadow-sm">
        <div class="flex items-center justify-between mb-2">
          <p class="text-gray-500">Meta do mês</p>
          <div class="w-8 h-8 bg-yellow-100 rounded-lg flex items-center justify-center">
            <svg class="w-5 h-5 text-yellow-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6"/>
            </svg>
          </div>
        </div>
        <h2 class="text-2xl font-bold">14.5%</h2>
      </div>
    </div>

    <!-- Gráfico e Metas -->
    <div class="grid grid-cols-3 gap-6">
      <!-- Coluna da esquerda com gráfico e botões -->
      <div class="col-span-2 space-y-8">
        <!-- Gráfico -->
        <div class="bg-white p-6 rounded-xl shadow-sm">
          <div class="flex justify-between items-center mb-6">
            <div>
              <h3 class="text-xl font-semibold">Performance</h3>
              <p class="text-gray-500"></p>
            </div>
            
            <div class="flex items-center gap-4">
              <button @click="toggleCalendar" class="flex items-center px-4 py-2 bg-gray-50 rounded-lg hover:bg-gray-100 relative">
                <span class="mr-2">{{ selectedDate }}</span>
                <svg class="w-5 h-5 text-gray-500" viewBox="0 0 20 20" fill="currentColor">
                  <path d="M6 8l4 4 4-4"/>
                </svg>

                <div 
                  v-if="isCalendarOpen"
                  class="absolute top-full right-0 mt-2 w-48 bg-white rounded-lg shadow-lg py-1 z-50"
                >
                  <div 
                    v-for="option in dateOptions" 
                    :key="option.value"
                    @click="selectDate(option)"
                    class="px-4 py-2 hover:bg-gray-100 cursor-pointer text-gray-700"
                  >
                    {{ option.value }}
                  </div>
                </div>
              </button>
            </div>
          </div>

          <div class="h-[300px]">
            <apexchart
              type="bar"
              height="300"
              :options="chartOptions"
              :series="series"
            ></apexchart>
          </div>
        </div>

        <!-- Botões -->
        <div class="flex gap-4 justify-end">
          <button 
            @click="abrirModalVendas"
            class="px-6 py-3 bg-[#28a745] text-white rounded-lg hover:opacity-90 flex items-center gap-2 shadow-sm"
          >
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6"/>
            </svg>
            Cadastrar Lucro
          </button>
          
          <button 
            @click="abrirModalCompras"
            class="px-6 py-3 bg-[#A52A2A] text-white rounded-lg hover:opacity-90 flex items-center gap-2 shadow-sm"
          >
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6"/>
            </svg>
            Cadastrar Gasto
          </button>
        </div>
      </div>

      <!-- Coluna da direita com últimos lançamentos -->
      <div class="bg-white p-6 rounded-xl shadow-sm h-full">
        <h3 class="text-xl font-semibold mb-6">Últimos lançamentos</h3>
        <div class="space-y-6">
          <div class="flex items-center gap-4">
            <div class="w-2 h-2 bg-green-500 rounded-full"></div>
            <div class="flex-1">
              <p class="font-medium">Dia de pagode</p>
              <p class="text-gray-500">R$10,000</p>
            </div>
            <span class="text-sm text-gray-500">Mar 15</span>
          </div>
          <div class="flex items-center gap-4">
            <div class="w-2 h-2 bg-red-500 rounded-full"></div>
            <div class="flex-1">
              <p class="font-medium">Cerveja lukinhas</p>
              <p class="text-gray-500">R$1,000</p>
            </div>
            <span class="text-sm text-gray-500">Oct 01</span>
          </div>
          <div class="flex items-center gap-4">
            <div class="w-2 h-2 bg-red-500 rounded-full"></div>
            <div class="flex-1">
              <p class="font-medium">Mercado</p>
              <p class="text-gray-500">R$1,500</p>
            </div>
            <span class="text-sm text-gray-500">Dec 12</span>
          </div>
        </div>
      </div>
    </div>

    <!-- Modais -->
    <ModalVendas 
      :isOpen="modalVendasAberto"
      @fechar="fecharModalVendas"
    />
    <ModalCompras 
      :isOpen="modalComprasAberto"
      @fechar="fecharModalCompras"
    />
  </div>
</template>

<script>
import ModalVendas from '../components/modals/atualizacaoSaldo/ModalVendas.vue'
import ModalCompras from '../components/modals/atualizacaoSaldo/ModalCompras.vue'
import api from '../services/api'
import Cookies from 'js-cookie'

export default {
  name: 'Dashboard',
  components: {
    ModalVendas,
    ModalCompras
  },
  data() {
    return {
      totalGastos30Dias: 0,
      isCalendarOpen: false,
      selectedDate: '30 Dec 2024',
      dateOptions: [
        { value: 'Today' },
        { value: 'Yesterday' },
        { value: 'Last 7 Days' },
        { value: 'Last 30 Days' },
        { value: 'This Month' },
        { value: 'Last Month' },
        { value: 'Custom Range' }
      ],
      chartOptions: {
        chart: {
          type: 'bar',
          toolbar: {
            show: false
          },
          stacked: false
        },
        plotOptions: {
          bar: {
            borderRadius: 4,
            columnWidth: '20%',
            dataLabels: {
              position: 'top'
            }
          }
        },
        dataLabels: {
          enabled: false
        },
        colors: ['#A52A2A', '#28a745'],
        xaxis: {
          categories: ['Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado', 'Domingo'],
          axisBorder: {
            show: false
          },
          axisTicks: {
            show: false
          }
        },
        yaxis: {
          labels: {
            formatter: function (val) {
              return '$' + val.toFixed(2)
            }
          }
        },
        grid: {
          borderColor: '#f1f1f1',
          padding: {
            top: 0,
            right: 0,
            bottom: 0,
            left: 0
          }
        },
        legend: {
          show: true,
          position: 'top',
          horizontalAlign: 'right'
        }
      },
      series: [
        {
          name: 'Gastos',
          data: [1200, 800, 1500, 1000, 1300, 950, 1100]
        },
        {
          name: 'Lucro',
          data: [1500, 1300, 1800, 1400, 1600, 1200, 1400]
        }
      ],
      modalVendasAberto: false,
      modalComprasAberto: false,
      balance: null,
      user: null,
      userName: 'Visitante',
      totalGastos30Dias: 0,
      totalVendas: 0,
    }
  },
  computed: {
    formattedBalance() {
      if (!this.balance) return 'R$ 0,00'
      return `R$ ${Number(this.balance.amount).toLocaleString('pt-BR', {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
      })}`
    },
    formattedName() {
      return this.user?.email || 'Visitante'
    }
  },
  methods: {
    toggleCalendar() {
      this.isCalendarOpen = !this.isCalendarOpen
    },
    selectDate(option) {
      this.selectedDate = option.value
      this.isCalendarOpen = false
    },
    abrirModalVendas() {
      this.modalVendasAberto = true
    },
    fecharModalVendas() {
      this.modalVendasAberto = false
    },
    abrirModalCompras() {
      this.modalComprasAberto = true
    },
    fecharModalCompras() {
      this.modalComprasAberto = false
    },
    async fetchBalance() {
      try {
        const response = await api.get('/api/balance')
        this.balance = response.data
      } catch (error) {
        console.error('Erro ao buscar saldo:', error)
      }
    },
    async fetchUser() {
      try {
        const userEmail = Cookies.get('user');
        const response = await api.get('/auth/users/profile', {
          headers: { user: userEmail }
        });
        this.user = response.data;
      } catch (error) {
        console.error('Erro ao buscar usuário:', error);
      }
    },
    fetchUserName() {
      const userId = this.user?.id;
      if (userId) {
        api.get(`/auth/users/${userId}/name`)
          .then(response => {
            this.userName = response.data;
          })
          .catch(error => {
            console.error('Erro ao buscar nome do usuário:', error);
            alert('Erro ao buscar nome do usuário');
          });
      }
    },
    async fetchGastos30Dias() {
        try {
            const response = await api.get('/api/expenses/last30days');
            this.totalGastos30Dias = response.data;
        } catch (error) {
            console.error('Erro ao buscar gastos:', error);
        }
    },
    async fetchTotalVendas() {
        try {
            const response = await api.get('/api/sales/total');
            this.totalVendas = response.data;
        } catch (error) {
            console.error('Erro ao buscar total de vendas:', error);
        }
    }
  },
  mounted() {
    this.fetchGastos30Dias();
    document.addEventListener('click', (e) => {
      if (!e.target.closest('.relative')) {
        this.isCalendarOpen = false
      }
    })
    this.fetchUser()
    this.fetchBalance()
    this.fetchUserName(); 
    const userId = this.user?.id;
    if (userId) {
      this.fetchUserName(userId);
    }
    this.fetchGastos30Dias();
    this.fetchTotalVendas();
  }
}
</script> 