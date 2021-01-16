<template>
  <h3>{{ article.title }}</h3>
  <p>{{ article.content }}</p>
  <router-link :to="{ name: 'Article', params: { id: article.id }}">link</router-link>
</template>

<script>
export default {
  name: "ArticleItem",
  props: ["article"],
}
</script>

<style scoped>

</style>