import { createStore } from 'vuex'

export default createStore({
  state() {
    return {
      articleItems: new Map()
    }
  },
  getters: {
    ARTICLE_ITEMS: state => key => {
      if (state.articleItems.has(key)) {
        return state.articleItems.get(key)
      }
      return []
    },
  },
  mutations: {
    SET_ARTICLE_ITEMS(state, { key, value }) {
      state.articleItems.set(key, value)
      setTimeout(() => state.articleItems.delete(key), 180000)
    }
  },
  actions: {
    FETCH_ARTICLE_ITEMS: ({ commit, state }, key) => {
      if (state.articleItems.has(key)) {
        return Promise.resolve(state.articleItems.get(key))
      }
      return fetch("http://localhost:8000/api/articles/" + key)
          .then(response => {
            return response.json()
          })
          .then(value => {
            console.log({ key, value })
            commit("SET_ARTICLE_ITEMS", { key, value });
            return value
          })
          .catch(error => {
            console.log(error);
          })
    }
  }
})
