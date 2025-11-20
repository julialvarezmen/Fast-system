import React from 'react';
import { NavLink } from 'react-router-dom';
import './AdminSidebar.css';

export default function AdminSidebar() {
  return (
    <aside className="admin-sidebar">
      
      <div className="sidebar-header">
        <div className="sidebar-logo">SF</div>
        <div>
          <div className="sidebar-title">SalchiFast</div>
          <div className="sidebar-subtitle">Panel Admin</div>
        </div>
      </div>

      <nav className="sidebar-nav">
        <NavLink 
          to="/admin" 
          end
          className={({ isActive }) => isActive ? "sidebar-link active" : "sidebar-link"}
        >
          Dashboard
        </NavLink>

        <NavLink 
          to="/admin/products" 
          className={({ isActive }) => isActive ? "sidebar-link active" : "sidebar-link"}
        >
          Productos
        </NavLink>

        <NavLink 
          to="/admin/orders" 
          className={({ isActive }) => isActive ? "sidebar-link active" : "sidebar-link"}
        >
          Pedidos
        </NavLink>

        <NavLink to="/admin/settings" className="sidebar-link">
          Configuración
        </NavLink>
      </nav>

    </aside>
  );
}
