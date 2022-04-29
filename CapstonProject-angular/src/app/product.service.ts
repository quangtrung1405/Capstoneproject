import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from './product';
import { BehaviorSubject, map, Observable, Subject } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ProductService {

public search= new BehaviorSubject<string>("");

private product!: Product;
private productSubject = new Subject();
  constructor(public http:HttpClient) { }

  set SelectedProductOrder(value: Product) {
    this.product = value;
    this.productSubject.next(0);
}

get SelectedProductOrder() {
    return this.product;
}
getProductById(id:number):Observable<Product>{
    return this.http.get<Product>("http://localhost:8181/ProductCRUDS/product/"+id)
}
  getAllProducts():Observable<Product[]>{
    return this.http.get<Product[]>("http://localhost:8282/ProductCRUD/retrieveProducts");
  }
  storeProductInfo(product:Product):Observable<Product>{
    return this.http.post<Product>(` http://localhost:8282/ProductCRUD/createProduct`,product);
  }
  deleteProduct(id:number){
    return this.http.delete(` http://localhost:8282/ProductCRUD/deleteProduct/${id}`);
  }
  updateProductInfo(product:Product):Observable<Product>{
    return this.http.put<Product>(`http://localhost:8282/ProductCRUD/updateProduct`,product);
  }



}
