import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  title = 'Front_End_Actual';
  flag:boolean=true;
  inbound:boolean=true;
  retrieveUsers: Product[];
  loginFinished: boolean;
  products: Product[];
  searchKey:string="";
  public searchTerm:string="";
  public filterCategory:any;
  constructor(public route:Router,private pserv:ProductService){}
  ngOnInit(): void {
    this.getProducts();
    this.pserv.search.subscribe((val:any)=>{
      this.searchKey=val;
    });
  }

  firstLogin(){
    alert("Please sign in to Continue!!!");
    this.route.navigate(["userlogin"])
  }
  ChangeNav():void{
    this.flag = false;
    this.route.navigate(["userlogin"]);

  }
  getProducts(){ 
    this.pserv.getAllProducts().subscribe(
      (response: Product[]) => {
        this.retrieveUsers = response;
        this.filterCategory=response;
        this.retrieveUsers.forEach((a:any)=>{
          if(a.category==="clock"||a.category==="shelves"||a.category==="antique")
          Object.assign(a,{quantity:1,total:a.price});
        });
        console.log(this.retrieveUsers);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  ChangeNavAd():void{
    this.flag = false;
    this.route.navigate(["adminlogin"]);
  }
  search(event:any){
    this.searchTerm=(event.target as HTMLInputElement).value;
    console.log(this.searchTerm)
    this.pserv.search.next(this.searchTerm);
  }
  filter(category:string){
    this.filterCategory=this.retrieveUsers
    .filter((a:any)=>{
      if(a.category==category||category==''){
        return a;
      }
    });
    }
}
