import React from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import AdminDashboard from './admin/pages/AdminDashboard';
import ProductManager from './admin/pages/ProductManager';
import OrdersManager from './admin/components/OrdersManager';
import Settings from './admin/pages/Settings';

export default function App() {
  return (
    <BrowserRouter>
      <Routes>

        {/* Redirección */}
        <Route path="/" element={<Navigate to="/admin" replace />} />

        {/* Dashboard */}
        <Route path="/admin" element={<AdminDashboard />} />

        {/* Productos */}
        <Route path="/admin/products" element={<ProductManager />} />

        {/* Pedidos */}
        <Route path="/admin/orders" element={<OrdersManager />} />

        {/* Configuración */}
        <Route path="/admin/settings" element={<Settings />} />

      </Routes>
    </BrowserRouter>
  );
}
