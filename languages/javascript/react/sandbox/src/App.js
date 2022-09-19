import React from "react";

import './App.css';

import { BrowserRouter } from "react-router-dom";
import { Route, Routes } from "react-router";

import Seasons from "./pages/seasons";
import Home from "./pages/home";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/seasons" element={<Seasons />} />
      </Routes>  
    </BrowserRouter>
  );
}

export default App;
