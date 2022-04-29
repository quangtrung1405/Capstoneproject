import {Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';

import {Subscription} from "rxjs/internal/Subscription";
import { ProductOrders } from 'src/app/product-orders';
import { ProductOrder } from 'src/app/product-order';
import { EcommerceService } from 'src/app/ecommerce-service.service';

@Component({
    selector: 'app-shopping-cart',
    templateUrl: './shopping-cart.component.html',
    styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit, OnDestroy {
    orderFinished: boolean;
    orders!: ProductOrders;
    total: number;
    sub!: Subscription;
    private shoppingCartOrders!: ProductOrders;


    @Output() onOrderFinished: EventEmitter<boolean>;

    constructor(private ecommerceService: EcommerceService) {
        this.total = 0;
        this.orderFinished = false;
        this.onOrderFinished = new EventEmitter<boolean>();
    }

    ngOnInit() {
        this.orders = new ProductOrders();
        this.loadCart();
        this.loadTotal();

    }

    private calculateTotal(products: ProductOrder[]): number {
        // let sum = 0;
        products.forEach(value => {
            this.total += (value.product.price * value.quantity);
        });
        return this.total;
    }

    ngOnDestroy() {
        this.sub.unsubscribe();
    }

    finishOrder() {
        this.orderFinished = true;
        this.ecommerceService.Total = this.total;
        this.onOrderFinished.emit(this.orderFinished);
    }
    finishOrders(orderFinished: boolean) {
        this.orderFinished = orderFinished;
    }

    loadTotal() {
        this.sub = this.ecommerceService.OrdersChanged.subscribe(() => {
            this.total = this.calculateTotal(this.orders.productOrders);
        });
    }

    loadCart() {
        this.sub = this.ecommerceService.ProductOrderChanged.subscribe(() => {
            let productOrder = this.ecommerceService.SelectedProductOrder;
            if (productOrder) {
                this.orders.productOrders.push(new ProductOrder(
                    productOrder.product, productOrder.quantity));
            }
            this.ecommerceService.ProductOrders = this.orders;
            this.orders = this.ecommerceService.ProductOrders;
            this.total = this.calculateTotal(this.orders.productOrders);
        });
    }

    reset() {
        this.orderFinished = false;
        this.orders = new ProductOrders();
        this.orders.productOrders = []
        this.loadTotal();
        this.total = 0;
    }

}
