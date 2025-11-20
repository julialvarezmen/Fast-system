import React, { useState } from 'react'
import usePolling from '../hooks/usePolling.js'
import OrderStatusCard from '../components/OrderStatusCard.jsx'
import './MisPedidos.css'

export default function MisPedidos(){
  const [orders, setOrders] = useState([])
  usePolling(async ()=>{
    try{
      const res = await fetch('/api/orders/history')
      const data = await res.json()
      setOrders(data)
    }catch(e){
      // no romper la UI si falla
    }
  }, 5000)

  return (
    <section className="mis-pedidos-section container">
      <h1>Mis Pedidos</h1>
      {orders.length === 0 ? <p className="no-orders">No tienes pedidos recientes.</p> :
        <div className="orders-grid">
          {orders.map(o => <OrderStatusCard key={o.id} order={o} />)}
        </div>
      }
    </section>
  )
}
