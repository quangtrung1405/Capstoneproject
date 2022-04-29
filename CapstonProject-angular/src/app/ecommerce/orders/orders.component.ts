import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';

import {Subscription} from "rxjs/internal/Subscription";
import { EcommerceService } from 'src/app/ecommerce-service.service';
import { ProductOrders } from 'src/app/product-orders';


@Component({
    selector: 'app-orders',
    templateUrl: './orders.component.html',
    styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
    orders: ProductOrders;
    total!: number ;
    paid!: boolean;
    sub!: Subscription;
    username:string="";

    constructor(private ecommerceService: EcommerceService,public route:Router) {
        this.orders = this.ecommerceService.ProductOrders;
    }

    ngOnInit() {
        this.paid = false;
        this.sub = this.ecommerceService.OrdersChanged.subscribe(() => {
            this.orders = this.ecommerceService.ProductOrders;
        });
        this.loadTotal();
        let res = sessionStorage.getItem("usname");
        if(res!=null){
          this.username=res;
        }
    }

    pay() {
        this.paid = true;
        this.ecommerceService.saveOrder(this.orders).subscribe();
    }

    loadTotal() {
        this.sub = this.ecommerceService.TotalChanged.subscribe(() => {
            this.total = this.ecommerceService.Total;
        });
    }

    userlogout(){
        sessionStorage.removeItem("usname");
       this.route.navigate(["useractivity"])
      }

      home(){
        this.route.navigate(["userhome"])
      }
}