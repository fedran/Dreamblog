<template>
  <div class="home">
    <ArticleItem
        v-for="article in articles"
        :key="article.id"
        :article="article"
    />
  </div>
</template>

<script>
import ArticleItem from "@/components/ArticleItem";
import {mapActions} from "vuex";

export default {
  name: 'Home',
  components: {ArticleItem},
  created() {
    this.getArticles()
  },
  computed: {
    articles() {
      return this.$store.getters.ARTICLES
    }
  },
  methods: {
    ...mapActions(['getArticles'])
  }
}
</script>
