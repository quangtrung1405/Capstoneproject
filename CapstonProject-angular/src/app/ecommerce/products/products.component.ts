import {Component, OnInit} from '@angular/core';
import {Subscription} from "rxjs/internal/Subscription";
import { ProductOrder } from 'src/app/product-order';
import { ProductOrders } from 'src/app/product-orders';
import { Product } from 'src/app/product';
import { EcommerceService } from 'src/app/ecommerce-service.service';
import { ProductService } from 'src/app/product.service';

@Component({
    selector: 'app-products',
    templateUrl: './products.component.html',
    styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
    productOrders: ProductOrder[] = [];
    products: Product[] = [];
    selectedProductOrder!: ProductOrder;
    private shoppingCartOrders!: ProductOrders;
    sub!: Subscription;
    productSelected: boolean = false;
    username:string="";
    searchKey:string="";

    constructor(private ecommerceService: EcommerceService,private pserv:ProductService) {
    }

    ngOnInit() {
        this.productOrders = [];
        this.loadProducts();
        this.loadOrders();
        let res = sessionStorage.getItem("usname");
        if(res!=null){
          this.username=res;
        }
        
    }
    search(event:any){
        this.pserv.search.next(this.searchKey )
    }
    addToCart(order: ProductOrder) {
        this.ecommerceService.SelectedProductOrder = order;
        this.selectedProductOrder = this.ecommerceService.SelectedProductOrder;
        this.productSelected = true;
    }

    removeFromCart(productOrder: ProductOrder) {
        let index = this.getProductIndex(productOrder.product);
        if (index > -1) {
            this.shoppingCartOrders.productOrders.splice(
                this.getProductIndex(productOrder.product), 1);
        }
        this.ecommerceService.ProductOrders = this.shoppingCartOrders;
        this.shoppingCartOrders = this.ecommerceService.ProductOrders;
        this.productSelected = false;
    }

    getProductIndex(product: Product): number {
        return this.ecommerceService.ProductOrders.productOrders.findIndex(
          (            value: { product: any; }) => value.product === product);
    }

    isProductSelected(product: Product): boolean {
        return this.getProductIndex(product) > -1;
    }

    loadProducts() {
        this.ecommerceService.getAllProducts()
            .subscribe(
                (products: any) => {
                    this.products = products;
                    this.products.forEach(product => {
                        this.productOrders.push(new ProductOrder(product, 0));
                    })
                },
                (error: any) => console.log(error)
            );
    }

    loadOrders() {
        this.sub = this.ecommerceService.OrdersChanged.subscribe(() => {
            this.shoppingCartOrders = this.ecommerceService.ProductOrders;
        });
    }

    reset() {
        this.productOrders = [];
        this.loadProducts();
        this.ecommerceService.ProductOrders.productOrders = [];
        this.loadOrders();
        this.productSelected = false;
    }
}