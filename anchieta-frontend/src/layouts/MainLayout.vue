<template>
  <div class="flex">
    <!-- Menu Lateral -->
    <Sidebar />
    
    <!-- Conteúdo Principal -->
    <div class="flex-1">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import Sidebar from '../components/Sidebar.vue'

export default {
  name: 'MainLayout',
  components: {
    Sidebar
  }
}
</script> 