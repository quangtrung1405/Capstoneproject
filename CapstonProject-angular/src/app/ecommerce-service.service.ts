import { ProductOrder } from "./product-order";
import {Subject} from "rxjs/internal/Subject";

import {HttpClient} from '@angular/common/http';
import {Injectable} from "@angular/core";
import { ProductOrders } from "./product-orders";

@Injectable({
  providedIn: 'root'
})
export class EcommerceService {
    private productsUrl = "http://localhost:8181/ProductCRUDS/retrieveProducts";
    private ordersUrl = "http://localhost:8282/api/orders";

    private productOrder!: ProductOrder;
    private orders: ProductOrders = new ProductOrders();

    private productOrderSubject = new Subject();
    private ordersSubject = new Subject();
    private totalSubject = new Subject();

    private total!: number;

    ProductOrderChanged = this.productOrderSubject.asObservable();
    OrdersChanged = this.ordersSubject.asObservable();
    TotalChanged = this.totalSubject.asObservable();

    constructor(private http: HttpClient) {
    }

    getAllProducts() {
        return this.http.get(this.productsUrl);
    }
    getAllOrders(){
        return this.http.get(this.ordersUrl);
    }

    saveOrder(order: ProductOrders) {
        console.log(order);
        return this.http.post(this.ordersUrl, order);
    }

    set SelectedProductOrder(value: ProductOrder) {
        this.productOrder = value;
        this.productOrderSubject.next(0);
    }

    get SelectedProductOrder() {
        return this.productOrder;
    }

    set ProductOrders(value: ProductOrders) {
        this.orders = value;
        this.ordersSubject.next(0);
    }

    get ProductOrders() {
        return this.orders;
    }

    get Total() {
        return this.total;
    }

    set Total(value: number) {
        this.total = value;
        this.totalSubject.next(0);
    }
}