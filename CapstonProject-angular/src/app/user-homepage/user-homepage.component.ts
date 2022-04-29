import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-user-homepage',
  templateUrl: './user-homepage.component.html',
  styleUrls: ['./user-homepage.component.css']
})
export class UserHomepageComponent implements OnInit {

  username:string="";

  products:Array<Product>=[];
  retrieveUsers: any;

  constructor(public route:Router,public ProdSer:ProductService) { }

  ngOnInit(): void {
    let res = sessionStorage.getItem("usname");
    if(res!=null){
      this.username=res;
    }
    this.loadProducts();
  }
  userlogout(){
    sessionStorage.removeItem("adname");
   this.route.navigate([""])
  }


  addToCart(){
    this.route.navigate(["shoppingcart"])
  }

  loadProducts(): void{

    this.ProdSer.getAllProducts().subscribe(res=>this.products=res);
  }
  getUsers(): void {
    this.ProdSer.getAllProducts().subscribe(
      (response: Product[]) => {
        this.retrieveUsers = response;
        console.log(this.retrieveUsers);
        this.username!=sessionStorage.getItem("usname");
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public searchUser(key: string): void {
    console.log(key);
    const results: Product[] = [];
    for (const employee of this.retrieveUsers) {
      if (employee.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.description.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.category.toString().indexOf(key.toLowerCase()) !== -1) {
        results.push(employee);
      }
    }
    this.retrieveUsers = results;
    if (results.length === 0 || !key) {
      this.getUsers();
    }
  }

}
