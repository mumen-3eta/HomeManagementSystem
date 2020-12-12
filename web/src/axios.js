import axios from 'axios'

// axios.defaults.baseURL = 'http://apiuser.test/api/';
axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token');
