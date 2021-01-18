<template>
  <div>
    <router-link to="/">Blog</router-link> |
    <router-link to="/">Top</router-link> |
    <router-link to="/new">New</router-link> |
    Login
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>

</style>