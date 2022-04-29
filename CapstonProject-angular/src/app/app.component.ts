import { HttpErrorResponse } from '@angular/common/http';
import { Component, Directive, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from './product';
import { ProductService } from './product.service';
import { UserLoginComponent } from './user-login/user-login.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Front_End_Actual';
  flag:boolean=true;
  inbound:boolean=true;
  retrieveUsers: Product[];
  loginFinished: boolean;
  constructor(public route:Router,private renderer2: Renderer2,private elRef:ElementRef,private pserv:ProductService){}
  ngOnInit(): void {
   this.getUsers
  }

  ChangeNav():void{
    this.flag = false;
    this.route.navigate(["userlogin"]);

  }
  ChangeNavAd():void{
    this.flag = false;
    this.route.navigate(["adminlogin"]);

  }
  getUsers(): void {
    this.pserv.getAllProducts().subscribe(
      (response: Product[]) => {
        this.retrieveUsers = response;
        console.log(this.retrieveUsers);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
 
}
