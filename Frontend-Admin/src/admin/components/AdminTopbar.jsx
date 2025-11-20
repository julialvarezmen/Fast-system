import React from 'react';
import './AdminTopbar.css';

export default function AdminTopbar({ title }) {
  return (
    <header className="admin-topbar">
      <h1>{title}</h1>

      <button className="logout-btn">
        Cerrar sesión
      </button>
    </header>
  );
}
