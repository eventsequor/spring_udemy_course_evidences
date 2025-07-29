import React, { useEffect, useState } from 'react';
import { listProducts } from '../services/ProductService';
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

    useEffect(() => {
        const initProducts = listProducts();
        setProducts(initProducts);
    }, []);

    const handlerAddProduct = (product) => {
        console.log(product);
        if (product.id > 0) {
            setProducts(products.map(prod => {
                if (prod.id == product.id) {
                    return { ...product }
                }
                return { ...prod }
            }));
        } else {
            setProducts([...products, { ...product, id: new Date().getTime() }]);
        }


    }

    const handlerRemoveProduct = (id) => {
        console.log("name: " + id)
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