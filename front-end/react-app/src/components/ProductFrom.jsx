import { useEffect, useState } from "react";

const initialDataForm = {
    id: 0,
    name: "",
    description: "",
    price: ""
}

export const ProductFrom = ({ handlerAdd, productSelected }) => {

    const [form, setFrom] = useState(initialDataForm);

    const { id, name, description, price } = form;

    useEffect(() => {
        setFrom(productSelected);

    }, [productSelected]);

    return (
        <form onSubmit={(event) => {
            event.preventDefault();
            if (!name || !description || !price) {
                alert("All fields are required");
                return;
            }
            //console.log(form);
            handlerAdd(form);
            setFrom(initialDataForm);
        }}>
            <div><input
                placeholder="Name"
                className="form-control my-3 w-75"
                name="name"
                value={name}
                onChange={(event) => setFrom({ ...form, name: event.target.value })}
            /></div>
            <div><input
                placeholder="Description"
                className="form-control my-3 w-75"
                name="description"
                value={description}
                onChange={(event) => setFrom({ ...form, description: event.target.value })}
            /></div>
            <div><input
                placeholder="Price"
                className="form-control my-3 w-75"
                name="price"
                value={price}
                onChange={(event) => setFrom({ ...form, price: event.target.value })}
            /></div>
            <div>
                <button className="btn btn-primary" type="submit">
                    {id > 0? 'Update':'Create'}
                </button>
            </div>
        </form>
    )
}