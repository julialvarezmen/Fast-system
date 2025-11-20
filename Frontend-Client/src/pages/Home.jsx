import React from 'react'
import products from '../data/products.js'
import ProductCard from '../components/ProductCard.jsx'
import './Home.css'

export default function Home(){
  return (
    <section className="home-section">
      <div className="hero">
        <div className="hero-inner">
          <h1>¡Bienvenido a <span>SalchiFast</span>!</h1>
          <p>El sabor que te llega rápido a casa. Pide ya tu salchipapa favorita.</p>
        </div>
      </div>

      <div className="products-grid">
        {products.map(p => <ProductCard key={p.id} product={p} />)}
      </div>
    </section>
  )
}
