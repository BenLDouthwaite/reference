import axios from "axios";

export default axios.create({
  baseURL: "https://api.unsplash.com",
  headers: {
    Authorization: "Client-ID Z5x-tCoWhvy4cf8ldvaOpn3ji-8M5XufcrXHYGxoCOw",
  },
});
