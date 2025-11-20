import React, { useEffect, useState } from 'react';
import AdminLayout from "../pages/AdminLayout";
import './OrdersManager.css';

export default function OrdersManager() {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    fetchOrders();
  }, []);

  async function fetchOrders() {
    setLoading(true);
    try {
      const res = await fetch('/api/orders'); // Debe devolver pedidos ACTUALES
      if (!res.ok) throw new Error('API error');
      const data = await res.json();
      setOrders(data);
    } catch (e) {
      console.warn('API caída → cargando MOCK');
      setOrders(mockOrders());
    } finally {
      setLoading(false);
    }
  }

  async function updateStatus(id, status) {
    try {
      const res = await fetch(`/api/orders/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ status }),
      });
      if (!res.ok) throw new Error('Error al actualizar');

      setOrders(prev =>
        prev.map(o => (o.id === id ? { ...o, status } : o))
      );
    } catch (e) {
      console.error(e);
      alert('No se pudo actualizar el estado');
    }
  }

  return (
    <AdminLayout title="Gestión de Pedidos">
      <div className="orders-header">
        <h2>Pedidos Recibidos</h2>
        <button onClick={fetchOrders} className="orders-refresh">
          Actualizar
        </button>
      </div>

      {loading ? (
        <div className="orders-loading">Cargando pedidos...</div>
      ) : (
        <div className="orders-grid">
          {orders.map(order => (
            <div key={order.id} className="order-card">
              <div className="order-top">
                <strong>Pedido #{order.id}</strong>
                <span className={`order-status ${order.status}`}>
                  {order.status}
                </span>
              </div>

              <div className="order-body">
                <div><strong>Cliente:</strong> {order.customer}</div>
                <div><strong>Productos:</strong></div>
                <ul className="order-products">
                  {order.items.map((item, index) => (
                    <li key={index}>
                      {item.name} x {item.qty}
                    </li>
                  ))}
                </ul>
                <div><strong>Total:</strong> ${order.total}</div>
                <div><strong>Dirección:</strong> {order.address}</div>
              </div>

              <div className="order-actions">
                <button
                  className="order-btn pending"
                  onClick={() => updateStatus(order.id, 'pendiente')}
                >
                  Pendiente
                </button>

                <button
                  className="order-btn delivered"
                  onClick={() => updateStatus(order.id, 'entregado')}
                >
                  Entregado
                </button>

                <button
                  className="order-btn canceled"
                  onClick={() => updateStatus(order.id, 'cancelado')}
                >
                  Cancelado
                </button>
              </div>
            </div>
          ))}
        </div>
      )}
    </AdminLayout>
  );
}


/* Mock temporal por si la API está caída */
function mockOrders() {
  return [
    {
      id: 101,
      customer: 'Julian',
      address: 'Calle 123',
      total: 42000,
      status: 'pendiente',
      items: [
        { name: 'Salchipapa Clásica', qty: 1 },
        { name: 'Monster', qty: 2 },
      ],
    },
    {
      id: 102,
      customer: 'Maria',
      address: 'Cra 50 #20',
      total: 18000,
      status: 'entregado',
      items: [{ name: 'Mexicana', qty: 1 }],
    },
    {
      id: 103,
      customer: 'Carlos',
      address: 'Barrio Centro',
      total: 25000,
      status: 'cancelado',
      items: [{ name: 'Monster', qty: 1 }],
    },
  ];
}
