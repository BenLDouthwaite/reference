import axios from "axios";

export default axios.create({
  baseURL: "https://api.unsplash.com",
  headers: {
    // TODO Update to use env var
    Authorization: "{my-client-id}",
  },
});
