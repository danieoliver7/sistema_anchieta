<template>
  <div class="min-h-screen flex">
    <!-- Menu Lateral - Mostrar apenas quando não estiver na página de login -->
    <aside v-if="!isLoginPage" class="w-64 bg-gray-50 border-r border-gray-200">
      <div class="p-4">
        <h1 class="text-xl font-semibold mb-6">Dashboard</h1>
        
        <nav class="space-y-4">
          <!-- Tópicos do Menu -->
          <div v-for="(topic, index) in topics" :key="index" class="space-y-2">
            <div 
              v-if="topic.name !== 'Logout'"
              @click="toggleSubmenu(index)"
              class="flex items-center justify-between px-4 py-2 text-gray-700 hover:bg-gray-200 rounded-lg cursor-pointer"
            >
              <span>{{ topic.name }}</span>
              <svg 
                class="w-4 h-4 transition-transform duration-200"
                :class="{ 'transform rotate-180': topic.isOpen }"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
              >
                <path d="M19 9l-7 7-7-7" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
              </svg>
            </div>
            <div v-show="topic.isOpen" class="ml-4 space-y-1">
              <div 
                v-for="(subtopic, subIndex) in topic.subtopics" 
                :key="subIndex"
                @click="openModal(topic.name, subtopic)"
                class="px-4 py-2 text-sm text-gray-600 hover:bg-gray-200 rounded-lg cursor-pointer"
              >
                {{ subtopic }}
              </div>
            </div>
          </div>
          <!-- Botão de Logout -->
          <button 
            @click="logout"
            class="w-full px-4 py-2 text-gray-700 hover:bg-gray-200 rounded-lg cursor-pointer"
          >
            Sair
          </button>
        </nav>
      </div>
    </aside>

    <!-- Conteúdo Principal -->
    <main class="flex-1">
      <router-view></router-view>
    </main>

    <!-- Modais -->
    <NovoCadastroModal v-if="showNovoCadastroModal" @close="showNovoCadastroModal = false" />
    <ListarUsuariosModal v-if="showListarUsuariosModal" @close="showListarUsuariosModal = false" />
  </div>
</template>

<script>
import NovoCadastroModal from './components/modals/cadastro/NovoCadastroModal.vue';
import ListarUsuariosModal from './components/modals/cadastro/ListarUsuariosModal.vue';

export default {
  name: 'App',
  components: {
    NovoCadastroModal,
    ListarUsuariosModal
  },
  data() {
    return {
      topics: [
        {
          name: 'Cadastro',
          isOpen: false,
          subtopics: ['Novo Cadastro', 'Usuarios']
        },
        {
          name: 'Cadastro de Produtos',
          isOpen: false,
          subtopics: ['Novos produtos', 'Cadastro Fornecedores']
        },
        {
          name: 'Mesas',
          isOpen: false,
          subtopics: ['Abertos', 'Todos', 'Outros']
        },
        {
          name: 'Logout',
          isOpen: false
        },
      ],
      showNovoCadastroModal: false,
      showListarUsuariosModal: false
    }
  },
  computed: {
    isLoginPage() {
      return this.$route.name === 'Login'
    }
  },
  methods: {
    toggleSubmenu(index) {
      this.topics[index].isOpen = !this.topics[index].isOpen
    },
    openModal(topicName, subtopicName) {
      if (topicName === 'Cadastro') {
        if (subtopicName === 'Novo Cadastro') {
          this.showNovoCadastroModal = true;
        } else if (subtopicName === 'Usuarios') {
          this.showListarUsuariosModal = true;
        }
      }
      // Adicione lógica para outros tópicos e sub-tópicos conforme necessário
    },
    logout() {
      localStorage.removeItem('authToken');
      this.$router.push({ name: 'Login' });
      console.log('Sessão encerrada e usuário redirecionado para a página de login');
    }
  }
}
</script>
