<template>
  <div id="nav">
    <router-link to="/">Home</router-link> |
    <router-link to="/a/1">About</router-link>
  </div>
  <router-view/>
</template>

<script>
export default {
  name: 'App'
}
</script>
