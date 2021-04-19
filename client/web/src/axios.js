import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8080/api/v1/';
// axios.defaults.headers.common['csrf-token'] = localStorage.getItem('csrfToken');//this.$store.getters.TokenUser.csrfToken
// axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('csrfToken');
//csrf-token
