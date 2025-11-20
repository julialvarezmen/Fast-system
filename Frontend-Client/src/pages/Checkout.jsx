import React, { useState } from 'react'
import { useCart } from '../context/CartContext.jsx'
import './Checkout.css'

export default function Checkout(){
  const { items, total, clearCart } = useCart()
  const [form, setForm] = useState({ nombre:'', celular:'', direccion:'', barrio:'', metodoPago:'Efectivo' })
  const [loading, setLoading] = useState(false)
  const [success, setSuccess] = useState(false)

  const handleChange = e => setForm({...form, [e.target.name]: e.target.value})

  const handleSubmit = async (e) => {
    e.preventDefault()
    if(items.length===0) return alert('El carrito está vacío')
    setLoading(true)
    try {
      // Ajusta la URL al backend real
      await fetch('/api/orders', { method:'POST', headers:{'Content-Type':'application/json'}, body:JSON.stringify({ cliente:form, productos:items, total }) })
      setSuccess(true); clearCart()
    } catch (err) {
      alert('Error al enviar pedido')
    }
    setLoading(false)
  }

  if(success) return (<div className="checkout-success container"><h2>Pedido enviado ✅</h2><p>Revisa Mis Pedidos para el estado.</p></div>)

  return (
    <section className="checkout-section container">
      <h1>Checkout</h1>
      <form className="checkout-form" onSubmit={handleSubmit}>
        <label>Nombre
          <input name="nombre" value={form.nombre} onChange={handleChange} required />
        </label>
        <label>Celular
          <input name="celular" value={form.celular} onChange={handleChange} required maxLength={10} />
        </label>
        <label>Dirección
          <input name="direccion" value={form.direccion} onChange={handleChange} required />
        </label>
        <label>Barrio
          <input name="barrio" value={form.barrio} onChange={handleChange} required />
        </label>
        <label>Método de pago
          <select name="metodoPago" value={form.metodoPago} onChange={handleChange}>
            <option>Efectivo</option>
            <option>Transferencia</option>
            <option>Datafono Contra-Entrega</option>
          </select>
        </label>

        <div className="checkout-summary">
          <div>Productos: {items.length}</div>
          <div>Total: ${total.toLocaleString()}</div>
        </div>

        <button className="checkout-send-btn" type="submit" disabled={loading}>{loading ? 'Enviando...' : 'Enviar Pedido'}</button>
      </form>
    </section>
  )
}
