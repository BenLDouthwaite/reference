import React from "react";

import './App.css';

import { BrowserRouter, Link } from "react-router-dom";
import { Route, Routes } from "react-router";

function App() {
  return (
    <BrowserRouter>
      <Link to="/" >
        Home
      </Link>
      <Link to="/about" >
        About
      </Link>
      <Link
        to="/dashboard">
        Dashboard
      </Link>
      <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/about" element={<About />} />
      <Route path="/dashboard" element={<Dashboard />}>
      </Route>
    </Routes>  </BrowserRouter>
  );
}

function Home() {
  return (
    <div>
      <h2>Home</h2>
    </div>
  );
}

function About() {
  return (
    <div>
      <h2>About</h2>
    </div>
  );
}

function Dashboard() {
  return (
    <div>
      <h2>Dashboard</h2>
    </div>
  );
}

export default App;
