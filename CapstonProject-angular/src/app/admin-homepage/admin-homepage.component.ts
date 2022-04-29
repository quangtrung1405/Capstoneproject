import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { EcommerceService } from '../ecommerce-service.service';
import { Orders } from '../Orders';
import { Product } from '../product';
import { ProductOrder } from '../product-order';
import { ProductOrders } from '../product-orders';
import { ProductService } from '../product.service';
import { UserEntity } from '../user-entity';
import { UserService } from '../user.service';

@Component({
  selector: 'app-admin-homepage',
  templateUrl: './admin-homepage.component.html',
  styleUrls: ['./admin-homepage.component.css'],
})
export class AdminHomepageComponent implements OnInit {
  adminname:string="";
  products:Array<Product>=[];
  retrieveUsers:Array<UserEntity>=[];
  searchKey:string="";
  orders: Array<Orders>=[];
  order: any;
  showdata: any;
  searchTerm: string;
  searchTerms:string;
  filterCategory: UserEntity[];
  private ordersUrl = "http://localhost:8282/api/orders";
  constructor(public route:Router,public UsServ:UserService,public prodSer:ProductService,private oserv:EcommerceService) { }
  ngOnInit(): void {
   this.getOrders()
   this.prodSer.search.subscribe((val:any)=>{
    this.searchKey=val;
  });
  }


loadProducts(): void{

  this.prodSer.getAllProducts().subscribe(res=>this.products=res);
}
getOrders() {
    this.oserv.getAllOrders().subscribe(
      (response:any) => {
        this.orders = response;
        this.filterCategory=response
        console.log(this.orders);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  search(event:any){
    this.searchTerm=(event.target as HTMLInputElement).value;
    console.log(this.searchTerm)
    this.prodSer.search.next(this.searchTerm);
  }
  filter(category:string){
    this.filterCategory=this.retrieveUsers
    .filter((a:any)=>{
      if(a.category==category||category==''){
        return a;
      }
    });
    }
  adminlogout(){
    sessionStorage.removeItem("adname");
    this.route.navigate([""])
  }
}

