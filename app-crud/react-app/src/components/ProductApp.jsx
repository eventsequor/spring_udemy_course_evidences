import React, { useEffect, useState } from 'react';
import { findAll, update, create, remove } from '../services/ProductService';
import PropTypes from "prop-types";
import { ProductGrip } from './ProductGrip';
import { ProductFrom } from './ProductFrom';

export const ProductApp = ({ title }) => {

    const [products, setProducts] = useState([]);

    const [productSelected, setProductSelected] = useState({
        id: 0,
        name: '',
        description: '',
        price: ''
    })

    const getProducts = async () => {
        const result = await findAll();
        setProducts(result.data._embedded.products);
    }

    useEffect(() => {
        getProducts();
    }, []);

    const handlerAddProduct = async (product) => {
        console.log(product);
        if (product.id > 0) {
            const response = await update(product);
            setProducts(products.map(prod => {
                if (prod.id == response.data.id) {
                    return { ...response.data }
                }
                return { ...prod }
            }));
        } else {
            const response = await create(product);
            setProducts([...products, { ...response.data }]);
        }


    }

    const handlerRemoveProduct = (id) => {
        remove(id);
        setProducts(products.filter((product) => product.id != id))
    }

    const handlerProductSelected = (product) => {
        setProductSelected({ ...product });
    }

    return (
        <>
            <div className='container my-4'>
                <h2>{title}</h2>
                <div className='row'>
                    <div >
                        <ProductFrom handlerAdd={handlerAddProduct} productSelected={productSelected} />
                    </div>
                    <div >
                        {
                            products.length == 0 ? <div className='alert alert-warning'>There aren't products in the system</div> :
                                <ProductGrip products={products} handlerRemove={handlerRemoveProduct} handlerProductSelected={handlerProductSelected} />
                        }

                    </div>
                </div>
            </div>
        </>
    );
};

ProductApp.propTypes = {
    title: PropTypes.string.isRequired
}