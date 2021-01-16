import { createStore } from 'vuex'

export default createStore({
  state() {
    return {
      articles: []
    }
  },
  getters: {
    ARTICLES: state => {
      return state.articles;
    },
  },
  mutations: {
    SET_ARTICLES(state, payload) {
      state.articles = payload
    }
  },
  actions: {
    getArticles: ({ commit }) => {
      return fetch("http://localhost:8000/api/articles")
          .then(response => {
            console.log(response)
            return response.json();
          })
          .then(json => {
            console.log(json)
            commit("SET_ARTICLES", json);
          })
          .catch(error => {
            console.log(error);
          })
    }
  }
})
