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