import axios from "axios";

const {VITE_PLAN_API_URL, VITE_TRIP_API_URL, VITE_AUTH_API_URL, VITE_POST_API_URL } = import.meta.env;

// local vue api axios instance
function authAxios() {
  const instance = axios.create({
    baseURL: VITE_AUTH_API_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
      'Access-Control-Allow-Origin': '*',
    },
    proxy: {
      host: '104.236.174.88',
      port: 3128
    }
  });
  return instance;
}

function tripAxios() {
  const instance = axios.create({
    baseURL: VITE_TRIP_API_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
      'Access-Control-Allow-Origin': '*'
    },
  });
  return instance;
}
function planAxios() {
  const instance = axios.create({
    baseURL: VITE_PLAN_API_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
      'Access-Control-Allow-Origin': '*'
    },
  });
  return instance;
}

function postAxios() {
  const instance = axios.create({
    baseURL: VITE_POST_API_URL,
    headers: {
    "Content-Type": "application/json;charset=utf-8",
    'Access-Control-Allow-Origin': '*'
    },
  })
  return instance;
}

export { planAxios,authAxios, tripAxios, postAxios };
