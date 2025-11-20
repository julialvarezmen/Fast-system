import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import { useCart } from '../context/CartContext.jsx'
import './CartSidebar.css'

export default function CartSidebar(){
  const [open, setOpen] = useState(false)
  const { items, total, quantity, addToCart, removeFromCart, deleteFromCart, clearCart } = useCart()

  return (
    <>
      <button className="cart-toggle-mobile" onClick={() => setOpen(v=>!v)}>
        🛒 {quantity}
      </button>

      <aside className={`cart-sidebar ${open ? 'open' : ''}`} aria-hidden={!open}>
        <div className="cart-header">
          <h3>Tu Carrito</h3>
          <button className="close-cart" onClick={()=>setOpen(false)}>✕</button>
        </div>

        <div className="cart-content">
          {items.length === 0 ? (
            <p className="empty">Tu carrito está vacío</p>
          ) : (
            items.map(item => (
              <div className="cart-item" key={item.id}>
                <img src={item.image} alt={item.name} />
                <div className="ci-info">
                  <div className="ci-name">{item.name}</div>
                  <div className="ci-meta">
                    <span className="ci-qty">x{item.quantity}</span>
                    <span className="ci-price">${(item.price*item.quantity).toLocaleString()}</span>
                  </div>
                </div>
                <div className="ci-actions">
                  <button onClick={()=>addToCart(item)}>＋</button>
                  <button onClick={()=>removeFromCart(item.id)}>−</button>
                  <button onClick={()=>deleteFromCart(item.id)}>🗑️</button>
                </div>
              </div>
            ))
          )}
        </div>

        <div className="cart-footer">
          <div className="cart-summary">
            <div>Total</div>
            <div className="cart-total-amount">${total.toLocaleString()}</div>
          </div>
          <Link to="/checkout"><button className="checkout-btn" disabled={items.length===0}>Ir a Checkout</button></Link>
          <button className="clear-btn" onClick={clearCart} disabled={items.length===0}>Vaciar</button>
        </div>
      </aside>
    </>
  )
}
