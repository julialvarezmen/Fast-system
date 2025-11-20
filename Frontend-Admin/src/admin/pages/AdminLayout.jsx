import React from 'react';
import AdminSidebar from '../components/AdminSidebar';
import AdminTopbar from '../components/AdminTopbar';
import './AdminLayout.css';

export default function AdminLayout({ children, title }) {
  return (
    <div className="layout-container">

      <AdminSidebar />

      <div className="layout-content">
        <AdminTopbar title={title || "Panel Admin"} />

        <div className="layout-inner">
          {children}
        </div>
      </div>

    </div>
  );
}
