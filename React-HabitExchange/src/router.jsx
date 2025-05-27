// src/router.jsx
import { createBrowserRouter } from "react-router-dom";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";
import Hello from "./pages/Hello";
import './App.css'

export const router = createBrowserRouter([
  { path: "/hello",          element: <Hello /> }, 
  { path: "/",          element: <Login /> },     // 預設導到登入
  { path: "/login",     element: <Login /> },
  { path: "/register",  element: <Register /> },
  { path: "/dashboard", element: <Dashboard /> },
  { path: "*",          element: <Login /> },     // 404 fallback
]);
