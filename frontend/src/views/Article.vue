<template>
  <div class="article">
    <h1>This is an article {{ id }} page</h1>
  </div>
</template>

<script>
export default {
  name: "Article",
  props: ['id']
}
</script>

<style scoped>

</style>