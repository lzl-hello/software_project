// src/store/store.js
import { createStore } from 'vuex';

const store = createStore({
  state: {
    isAuthenticated: false,
    user: null
  },
  mutations: {
    setAuth(state, isAuthenticated) {
      state.isAuthenticated = isAuthenticated;
    },
    setUser(state, user) {
      state.user = user;
    }
  },
  actions: {
    login({ commit }, user) {
      commit('setAuth', true);
      commit('setUser', user);
    },
    logout({ commit }) {
      commit('setAuth', false);
      commit('setUser', null);
    }
  }
});

export default store;