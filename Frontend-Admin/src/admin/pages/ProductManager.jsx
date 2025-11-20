import React, { useEffect, useState } from 'react';
import AdminLayout from './AdminLayout';
import './ProductManager.css';

const initialForm = { name: '', price: '', description: '', imageUrl: '', id: null };

export default function ProductManager() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [form, setForm] = useState(initialForm);
  const [showForm, setShowForm] = useState(false);

  useEffect(() => {
    fetchProducts();
  }, []);

  async function fetchProducts() {
    setLoading(true);
    try {
      const res = await fetch('/api/products');
      if (!res.ok) throw new Error();
      const data = await res.json();
      setProducts(data);
    } catch (e) {
      console.warn('Fallo API, usando mock');
      setProducts(mockProducts());
    } finally {
      setLoading(false);
    }
  }

  function editProduct(p) {
    setForm({
      id: p.id,
      name: p.name,
      price: p.price,
      description: p.description,
      imageUrl: p.imageUrl
    });
    setShowForm(true);
  }

  function newProduct() {
    setForm(initialForm);
    setShowForm(true);
  }

  function handleChange(e) {
    const { name, value } = e.target;
    setForm(f => ({ ...f, [name]: value }));
  }

  async function saveProduct(e) {
    e.preventDefault();

    const payload = {
      name: form.name,
      price: Number(form.price),
      description: form.description,
      imageUrl: form.imageUrl
    };

    try {
      const method = form.id ? 'PUT' : 'POST';
      const url = form.id ? `/api/products/${form.id}` : '/api/products';

      const res = await fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload),
      });

      if (!res.ok) throw new Error();

      await fetchProducts();
      setShowForm(false);

    } catch (err) {
      console.error(err);
      alert('Error al guardar producto');
    }
  }

  async function softDelete(id) {
    if (!confirm('¿Deshabilitar producto? (soft delete)')) return;

    try {
      const res = await fetch(`/api/products/${id}/soft`, { method: 'PUT' });
      if (!res.ok) throw new Error();

      setProducts(p => p.filter(x => x.id !== id));
    } catch (e) {
      console.error(e);
      alert('No se pudo deshabilitar');
    }
  }

  return (
    <AdminLayout title="Gestión de Productos">
      
      {/* Encabezado */}
      <div className="pm-header">
        <h2 className="pm-title">Productos</h2>
        <button className="pm-button" onClick={newProduct}>Nuevo Producto</button>
      </div>

      {/* Lista de productos */}
      {loading ? (
        <div className="pm-loading">Cargando...</div>
      ) : (
        <div className="pm-grid">
          {products.map(p => (
            <div key={p.id} className="pm-card">

              <div className="pm-image-box">
                <img src={p.imageUrl} alt={p.name} />
              </div>

              <div className="pm-card-title">{p.name}</div>
              <div className="pm-card-desc">{p.description}</div>

              <div className="pm-card-footer">
                <div className="pm-price">${p.price}</div>

                <div className="pm-card-actions">
                  <button onClick={() => editProduct(p)} className="pm-edit">Editar</button>
                  <button onClick={() => softDelete(p.id)} className="pm-delete">Deshabilitar</button>
                </div>
              </div>

            </div>
          ))}
        </div>
      )}

      {/* Modal Form */}
      {showForm && (
        <div className="pm-modal-overlay">
          <form className="pm-modal" onSubmit={saveProduct}>
            
            <h3 className="pm-modal-title">
              {form.id ? "Editar Producto" : "Crear Producto"}
            </h3>

            <label className="pm-label">
              Nombre
              <input name="name" value={form.name} onChange={handleChange} required />
            </label>

            <label className="pm-label">
              Precio
              <input name="price" value={form.price} type="number" step="0.01" onChange={handleChange} required />
            </label>

            <label className="pm-label">
              Descripción
              <textarea name="description" value={form.description} rows="3" onChange={handleChange} />
            </label>

            <label className="pm-label">
              URL Foto
              <input name="imageUrl" value={form.imageUrl} onChange={handleChange} required />
            </label>

            <div className="pm-modal-actions">
              <button type="button" onClick={() => setShowForm(false)} className="pm-cancel">Cancelar</button>
              <button type="submit" className="pm-submit">{form.id ? "Guardar" : "Crear"}</button>
            </div>

          </form>
        </div>
      )}

    </AdminLayout>
  );
}

/* Mock temporal */
function mockProducts() {
  return [
    { id: 1, name: 'Salchipapa Clásica', price: 18000, description: 'Salchichas premium, papas', imageUrl: '/assets/c8903a94-b35c-4b92-a20a-48141780978b.png' },
    { id: 2, name: 'Salchipapa Monster', price: 25000, description: 'Porción gigante con todo', imageUrl: '/assets/ffa04a5b-3f41-4918-9626-c855a075aa8c.png' },
    { id: 3, name: 'Salchipapa Mexicana', price: 22000, description: 'Con guacamole y jalapeños', imageUrl: '/assets/8dc478b9-1c07-455e-a156-3c3856ffe034.png' },
  ];
}
