import React from "react";

import "./App.css";

import { BrowserRouter } from "react-router-dom";
import { Route, Routes } from "react-router";

import Seasons from "./pages/seasons";
import Home from "./pages/home";
import Components from "./pages/components";
import ImageSearch from "./pages/image-search";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/components" element={<Components />} />
        <Route path="/seasons" element={<Seasons />} />
        <Route path="/image-search" element={<ImageSearch />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
