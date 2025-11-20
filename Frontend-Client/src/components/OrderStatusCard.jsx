import React from 'react'
import './OrderStatusCard.css'

function statusColor(status){
  if(!status) return '#888'
  switch(status.toLowerCase()){
    case 'pending':
    case 'pendiente':
      return '#ff6d00'
    case 'cooking':
    case 'en proceso':
    case 'en camino':
      return '#009688'
    case 'ready':
    case 'entregado':
      return '#4caf50'
    case 'cancelled':
    case 'cancelado':
      return '#f44336'
    default: return '#888'
  }
}

export default function OrderStatusCard({ order }){
  return (
    <article className="order-card">
      <div className="order-top">
        <div className="order-date">{order.date || order.created_at || '—'}</div>
        <div className="order-status" style={{background:statusColor(order.status)}}>
          {order.status}
        </div>
      </div>
      <div className="order-body">
        <div>Items: {order.items ? order.items.length : 0}</div>
        <div className="order-total">Total: ${order.total?.toLocaleString?.() ?? '0'}</div>
      </div>
    </article>
  )
}
