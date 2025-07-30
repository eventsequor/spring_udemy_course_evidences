import PropTypes from "prop-types";
import { ProductDetail } from "./ProductDetail";

export const ProductGrip = ({products, handlerRemove, handlerProductSelected}) => {
    return (
        <table className="table table-hover table-striped">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Description</th>
                    <th>Update</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>
                {products.map((product, index) => (
                    <ProductDetail handlerProductSelected={handlerProductSelected} key={index} product={product} index={index} handlerRemove={handlerRemove} />
                ))}
            </tbody>
        </table>
    );
}

ProductGrip.propTypes = {
    products: PropTypes.array.isRequired,
    handlerRemove: PropTypes.func.isRequired,
    handlerProductSelected: PropTypes.func.isRequired
}