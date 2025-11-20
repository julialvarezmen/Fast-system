import React from "react";
import "./Order.css";

const Order = ({ order, onUpdateStatus }) => {
  const handleChange = (e) => {
    onUpdateStatus(order.id, e.target.value);
  };

  return (
    <div className="order-card">
      <div className="order-info">
        <h3>Pedido #{order.id}</h3>
        <p><strong>Cliente:</strong> {order.client}</p>
        <p><strong>Producto:</strong> {order.product}</p>
        <p><strong>Cantidad:</strong> {order.quantity}</p>
      </div>

      <div className="order-actions">
        <span className={`status-badge ${order.status.toLowerCase()}`}>
          {order.status}
        </span>

        <select
          value={order.status}
          onChange={handleChange}
          className="order-select"
        >
          <option value="Pendiente">Pendiente</option>
          <option value="Entregado">Entregado</option>
          <option value="Cancelado">Cancelado</option>
        </select>
      </div>
    </div>
  );
};

export default Order;
