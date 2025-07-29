import React, { useState } from 'react';

const initialProducts = [
    { id: 1, name: 'Producto A', description: 'Descripción A', price: 100 },
    { id: 2, name: 'Producto B', description: 'Descripción B', price: 200 },
];

export default function ProductTable() {
    const [products, setProducts] = useState(initialProducts);
    const [editingId, setEditingId] = useState(null);
    const [newProduct, setNewProduct] = useState(null);

    const handleEditChange = (id, field, value) => {
        setProducts(prev =>
            prev.map(p => (p.id === id ? { ...p, [field]: value } : p))
        );
    };

    const handleEnableEdit = (id) => {
        setEditingId(id);
    };

    const handleUpdate = () => {
        setEditingId(null);
    };

    const handleDelete = (id) => {
        setProducts(prev => prev.filter(p => p.id !== id));
    };

    const handleAddNewRow = () => {
        setNewProduct({ id: '', name: '', description: '', price: '' });
    };

    const handleNewChange = (field, value) => {
        setNewProduct(prev => ({ ...prev, [field]: value }));
    };

    const handleSaveNew = () => {
        const newId = products.length ? Math.max(...products.map(p => p.id)) + 1 : 1;
        setProducts([{ ...newProduct, id: newId }, ...products]);
        setNewProduct(null);
    };

    const handleCancelNew = () => {
        setNewProduct(null);
    };

    return (
        <div className="p-4">
            <button
                onClick={handleAddNewRow}
                className="mb-4 bg-blue-500 text-white px-4 py-2 rounded"
            >
                Adicionar
            </button>
            <table className="w-full table-auto border-collapse border border-gray-300">
                <thead>
                    <tr className="bg-gray-200">
                        <th className="border px-4 py-2">ID</th>
                        <th className="border px-4 py-2">Nombre</th>
                        <th className="border px-4 py-2">Descripción</th>
                        <th className="border px-4 py-2">Precio</th>
                        <th className="border px-4 py-2">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {newProduct && (
                        <tr className="bg-yellow-100">
                            <td className="border px-4 py-2">Nuevo</td>
                            <td className="border px-4 py-2">
                                <input
                                    type="text"
                                    value={newProduct.name}
                                    onChange={(e) => handleNewChange('name', e.target.value)}
                                    className="w-full border px-2 py-1"
                                />
                            </td>
                            <td className="border px-4 py-2">
                                <input
                                    type="text"
                                    value={newProduct.description}
                                    onChange={(e) => handleNewChange('description', e.target.value)}
                                    className="w-full border px-2 py-1"
                                />
                            </td>
                            <td className="border px-4 py-2">
                                <input
                                    type="number"
                                    value={newProduct.price}
                                    onChange={(e) => handleNewChange('price', e.target.value)}
                                    className="w-full border px-2 py-1"
                                />
                            </td>
                            <td className="border px-4 py-2 space-x-2">
                                <button onClick={handleSaveNew} className="bg-green-500 text-white px-2 py-1 rounded">Guardar</button>
                                <button onClick={handleCancelNew} className="bg-red-500 text-white px-2 py-1 rounded">Desestimar</button>
                            </td>
                        </tr>
                    )}
                    {products.map(product => (
                        <tr key={product.id}>
                            <td className="border px-4 py-2">{product.id}</td>
                            <td className="border px-4 py-2">
                                {editingId === product.id ? (
                                    <input
                                        value={product.name}
                                        onChange={(e) => handleEditChange(product.id, 'name', e.target.value)}
                                        className="w-full border px-2 py-1"
                                    />
                                ) : (
                                    product.name
                                )}
                            </td>
                            <td className="border px-4 py-2">
                                {editingId === product.id ? (
                                    <input
                                        value={product.description}
                                        onChange={(e) => handleEditChange(product.id, 'description', e.target.value)}
                                        className="w-full border px-2 py-1"
                                    />
                                ) : (
                                    product.description
                                )}
                            </td>
                            <td className="border px-4 py-2">
                                {editingId === product.id ? (
                                    <input
                                        type="number"
                                        value={product.price}
                                        onChange={(e) => handleEditChange(product.id, 'price', e.target.value)}
                                        className="w-full border px-2 py-1"
                                    />
                                ) : (
                                    `$${product.price}`
                                )}
                            </td>
                            <td className="border px-4 py-2 space-x-2">
                                {editingId === product.id ? (
                                    <>
                                        <button onClick={handleUpdate} className="bg-green-500 text-white px-2 py-1 rounded">Actualizar</button>
                                    </>
                                ) : (
                                    <>
                                        <button
                                            onClick={() => handleEnableEdit(product.id)}
                                            className="bg-blue-500 text-white px-2 py-1 rounded"
                                        >
                                            Modificar
                                        </button>
                                    </>
                                )}
                                <button
                                    onClick={() => handleDelete(product.id)}
                                    className="bg-red-500 text-white px-2 py-1 rounded"
                                >
                                    Eliminar
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}