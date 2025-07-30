import axios from "axios";

const initProducts = [
    { id: 1, name: 'macbook pro m3', price: 100, description: "Apple computer" },
    { id: 2, name: 'macbook air m3', price: 80, description: "Apple computer" },
    { id: 3, name: 'mac mini m3', price: 60, description: "Apple computer" },
    { id: 4, name: 'ipad pro m3', price: 50, description: "Apple tablet" },
    { id: 5, name: 'ipad air m3', price: 40, description: "Apple tablet" },
    { id: 6, name: 'iphone 15 pro', price: 30, description: "Apple phone" },
    { id: 7, name: 'iphone 15', price: 20, description: "Apple phone" }
];

export const listProducts = () => {
    return initProducts;
}

const baseUrl = 'http://localhost:8080';
const productsApi = `${baseUrl}/products`;

export const findAll = async () => {
    try {
        const response = await axios.get(productsApi);
        return response;
    } catch (error) {
        console.log(error);
        return null;
    }
}

export const create = async ({ name, description, price }) => {
    try {
        const response = await axios.post(productsApi,
            {
                name: name,
                description: description,
                price: price
            }
        );

        return response;
    } catch (error) {
        console.log(error);
        return undefined;
    }
}

export async function update({ id, name, description, price }) {
    try {
        console.log(`${productsApi}/${id}`);
        const response = await axios.put(`${productsApi}/${id}`,
            {
                name: name,
                description: description,
                price: price
            }
        );

        return response;
    } catch (error) {
        console.log(error);
        return undefined;
    }
}

export const remove = async (id) => {
    try {
        await axios.delete(`${productsApi}/${id}`)
    } catch (error) {
        console.log(error);
    }
}