import React from 'react'
import { Routes, Route, Link } from 'react-router-dom'
import Home from './pages/Home.jsx'
import Checkout from './pages/Checkout.jsx'
import MisPedidos from './pages/MisPedidos.jsx'
import CartSidebar from './components/CartSidebar.jsx'
import './App.css'

export default function App(){
  return (
    <div className="app-root">
      <header className="site-header">
        <div className="container header-inner">
          <Link to="/" className="brand">
            <img src="/assets/Logo.jpg" alt="SalchiMonster" className="brand-logo" />
            <span className="brand-text">SalchiFast</span>
          </Link>

          <nav className="nav">
            <Link to="/" className="nav-link">Inicio</Link>
            <Link to="/checkout" className="nav-link">Checkout</Link>
            <Link to="/mis-pedidos" className="nav-link">Mis Pedidos</Link>
          </nav>
        </div>
      </header>

      <main className="container main">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/checkout" element={<Checkout />} />
          <Route path="/mis-pedidos" element={<MisPedidos />} />
        </Routes>
      </main>

      <CartSidebar />

      <footer className="site-footer">
        <div className="container">© {new Date().getFullYear()} SalchiFast</div>
      </footer>
    </div>
  )
}
