<template>
  <aside class="bg-[#A52A2A] text-white w-64 min-h-screen p-4">
    <div class="mb-8">
      <h1 class="text-2xl font-bold">Resenha's Anchieta</h1>
    </div>

    <nav>
      <ul class="space-y-2">
        <li>
          <router-link 
            to="/dashboard" 
            class="flex items-center p-2 rounded-lg hover:bg-[#8B0000] transition-colors"
          >
            <span class="ml-3">Dashboard</span>
          </router-link>
        </li>
        <li>
          <router-link 
            to="/estoque" 
            class="flex items-center p-2 rounded-lg hover:bg-[#8B0000] transition-colors"
          >
            <span class="ml-3">Estoque</span>
          </router-link>
        </li>
        <li>
          <button 
            @click="handleLogout" 
            class="flex items-center p-2 rounded-lg hover:bg-[#8B0000] transition-colors w-full text-left"
          >
            <span class="ml-3">Sair</span>
          </button>
        </li>
      </ul>
    </nav>
  </aside>
</template>

<script>
import auth from '../services/auth'
import { useRouter } from 'vue-router'

export default {
  name: 'Sidebar',
  setup() {
    const router = useRouter()

    const handleLogout = () => {
      auth.logout()
      router.push('/login')
    }

    return {
      handleLogout
    }
  }
}
</script>