<template>
  <div class="articleList">
    <ArticleItem
        v-for="article in articles"
        :key="article.id"
        :article="article"
    />
  </div>
</template>

<script>
import ArticleItem from "@/components/ArticleItem";
import {mapActions, mapGetters} from "vuex";

export default {
  name: 'ArticleList',
  components: {ArticleItem},
  props: {
    queryString: {
      type: String,
      validator: function (value) {
        return ["", "new"].indexOf(value) !== -1
      }
    }
  },
  data() {
    return {
      articles: this.articleItems(this.query)
    }
  },
  created() {
    this.loadData(this.query)
  },
  watch: {
    query(newQuery) {
      this.loadData(newQuery)
    }
  },
  computed: {
    query() {
      return this.queryString === "" ? "top" : this.queryString
    }
  },
  methods: {
    ...mapGetters({ articleItems:'ARTICLE_ITEMS'}),
    ...mapActions({ fetchArticleItems:'FETCH_ARTICLE_ITEMS'}),
    loadData(query) {
      this.fetchArticleItems(query)
          .then(articles => this.articles = articles)
    }
  }
}
</script>

<style scoped>

</style>
