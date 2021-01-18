<template>
  <div class="content">
    <h3 class="subhead">{{ article.title }}</h3>
    <p>{{ article.content }}</p>
    <router-link :to="{ name: 'Article', params: { id: article.id }}">link</router-link>
  </div>
</template>

<script>
export default {
  name: "ArticleItem",
  props: ["article"],
}
</script>

<style scoped>
.content {
  padding-left: 2em;
  padding-right: 2em;
  margin-left: auto;
  margin-right: auto;
  max-width: 768px;
}

.subhead {
  margin: 2em 0 1em;
}
</style>