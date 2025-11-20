import React from 'react'
import { useCart } from '../context/CartContext.jsx'
import './ProductCard.css'

export default function ProductCard({ product }){
  const { addToCart } = useCart()
  return (
    <article className="product-card">
      <div className="media">
        <img src={product.image} alt={product.name} />
      </div>
      <div className="info">
        <h3 className="title">{product.name}</h3>
        <p className="desc">{product.description}</p>
        <div className="footer">
          <div className="price"> ${product.price.toLocaleString()} </div>
          <button className="add-btn" onClick={() => addToCart(product)}>Agregar</button>
        </div>
      </div>
    </article>
  )
}
