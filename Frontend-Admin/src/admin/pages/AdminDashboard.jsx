import React, { useEffect, useState } from 'react';
import AdminLayout from './AdminLayout';
import './AdminDashboard.css';

export default function AdminDashboard() {
  const [summary, setSummary] = useState({
    totalOrders: 0,
    pending: 0,
    delivered: 0
  });

  useEffect(() => {
    // Datos simulados
    setSummary({ totalOrders: 124, pending: 18, delivered: 90 });
  }, []);

  return (
    <AdminLayout title="Dashboard">

      {/* Tarjetas de resumen */}
      <div className="dashboard-cards">

        <div className="dashboard-card">
          <div className="card-label">Pedidos Totales</div>
          <div className="card-value highlight">{summary.totalOrders}</div>
        </div>

        <div className="dashboard-card">
          <div className="card-label">Pendientes</div>
          <div className="card-value">{summary.pending}</div>
        </div>

        <div className="dashboard-card">
          <div className="card-label">Despachados</div>
          <div className="card-value">{summary.delivered}</div>
        </div>

      </div>

      {/* Actividad reciente */}
      <section className="dashboard-activity">
        <h2 className="activity-title">Actividad reciente</h2>
        <p className="activity-text">
          Aquí puedes mostrar últimas órdenes, métricas u gráficos.
        </p>
      </section>

    </AdminLayout>
  );
}
