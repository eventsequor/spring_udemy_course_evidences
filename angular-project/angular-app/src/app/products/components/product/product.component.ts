import { Component } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product';
import { FormComponent } from '../form/form.component';

@Component({
  selector: 'app-product',
  imports: [FormComponent],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent {

  products: Product[] = [];

  productSelected: Product = new Product();

  constructor(private service: ProductService) { }

  ngOnInit(): void {
    this.service.findAll().subscribe(products => this.products = products)
  }

  addProduct(product: Product): void {
    //product.id = new Date().getTime();
    //this.products.push(product);
    if (product.id > 0) {
      this.service.update(product).subscribe(productUpdate => {
        this.products = this.products.map(prod => {
          if (prod.id == product.id) {
            return { ...productUpdate };
          }
          return prod;
        });
      })
    } else {
      this.service.create(product).subscribe(newProduct => {
        this.products = [...this.products, { ...newProduct }]
      })
    }
    this.productSelected = new Product();
  }

  onUpdateProduct(productRow: Product) {
    this.productSelected = { ...productRow };
  }

  onRemoveProduct(id: number): void {
    this.service.remove(id).subscribe(() => {
      this.products = this.products.filter(prod => prod.id != id);
    })
  }
}
