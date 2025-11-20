import React, { useState, useEffect } from "react";
import AdminLayout from "../pages/AdminLayout";
import "./Settings.css";

export default function Settings() {
  const [form, setForm] = useState({
    name: "",
    email: "",
    password: "",
  });

  useEffect(() => {
    // Más adelante aquí conectamos la API:
    // fetch('/api/admin/profile')
    setForm({
      name: "Administrador",
      email: "admin@salchifast.com",
      password: "",
    });
  }, []);

  function handleChange(e) {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  }

  function handleSubmit(e) {
    e.preventDefault();
    console.log("Datos enviados:", form);

    // Aquí va la API real:
    // fetch('/api/admin/settings', { method: 'PUT', body: JSON.stringify(form) })
  }

  return (
    <AdminLayout title="Configuración">
      <div className="settings-container">

        <h2 className="settings-title">Configuración de la Cuenta</h2>

        <form className="settings-form" onSubmit={handleSubmit}>

          <label>
            Nombre
            <input
              type="text"
              name="name"
              value={form.name}
              onChange={handleChange}
              className="settings-input"
            />
          </label>

          <label>
            Email
            <input
              type="email"
              name="email"
              value={form.email}
              onChange={handleChange}
              className="settings-input"
            />
          </label>

          <label>
            Nueva Contraseña
            <input
              type="password"
              name="password"
              value={form.password}
              onChange={handleChange}
              className="settings-input"
              placeholder="Opcional"
            />
          </label>

          <button type="submit" className="settings-btn">
            Guardar Cambios
          </button>
        </form>
      </div>
    </AdminLayout>
  );
}
