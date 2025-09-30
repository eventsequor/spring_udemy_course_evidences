import { Injectable } from '@angular/core';
import { Product } from '../models/product';
import { map, Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private products: Product[] = [
    { id: 1, name: 'Product 1', description: 'Description 1', price: 100 },
    { id: 2, name: 'Product 2', description: 'Description 2', price: 200 },
    { id: 3, name: 'Product 3', description: 'Description 3', price: 300 }
  ];

  private url: string = 'http://localhost:8080/products'

  constructor(private http: HttpClient) { }

  findAll(): Observable<Product[]> {
    //return of(this.products);
    return this.http.get<Product[]>(this.url).pipe(map((response: any) => response._embedded.products as Product[]))
  }

  create(product: Product): Observable<Product> {
    return this.http.post<Product>(this.url, product);
  }

  update(product: Product): Observable<Product> {
    return this.http.put<Product>(`${this.url}/${product.id}`, product);
  }

  remove(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
