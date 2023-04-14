import { useState } from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import "./index.css";
import AppHeader from "./components/AppHeader";
import Home from "./pages/Home";
import ContactUs from "./pages/ContactUs";
import PhotoPage from "./pages/PhotoPage";
import Photos from "./pages/Photos";

function App() {
  return (
    <>
      <BrowserRouter>
        <AppHeader />
        <main>
          <Routes>
            <Route path="/" element={<Home></Home>}></Route>
            <Route path="/photos" element={<Photos></Photos>}></Route>
            <Route path="/photo/:id" element={<PhotoPage></PhotoPage>}></Route>
            <Route path="/contact" element={<ContactUs></ContactUs>}></Route>
          </Routes>
        </main>
      </BrowserRouter>
    </>
  );
}

export default App;
