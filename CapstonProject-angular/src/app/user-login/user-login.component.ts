import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {
  
  showreg!:boolean;
  // showlog!:boolean;
   RegUser:string="";
   LogUser:string="";
   showlog=true;

  UserLogRef = new FormGroup({
    email: new FormControl("",[Validators.required]),
    password:new FormControl("",[Validators.required,Validators.minLength(6)])
  })
  
  UserRegRef = new FormGroup({
    username: new FormControl("",[Validators.required,Validators.minLength(6)]),
    email:new FormControl("",[Validators.required]),
     password:new FormControl("",[Validators.required,Validators.minLength(6)]),
    contact:new FormControl("",[Validators.required,Validators.minLength(10),Validators.maxLength(10)]),
    gender: new FormControl("",[Validators.required]),
    address:new FormControl("",[Validators.required]),
    city: new FormControl("",[Validators.required]),
    state:new FormControl("",[Validators.required])
  })
  constructor(public usSer:UserService,public route:Router) { }

  ngOnInit(): void {
  }
  registration():void{
    this.showreg=true;
    this.showlog=false;
  }
  login():void{
    this.showlog=true;
    this.showreg=false;
  }
  userLogin():void{
    this.usSer.userSignIn(this.UserLogRef.value).subscribe(result=>{
      if(result.startsWith("Welcome")){
        sessionStorage.setItem("usname",this.UserLogRef.value.email);
        this.route.navigate(["userhome"]);
      }
      else{
        this.LogUser=result;
        this.UserLogRef.reset();
      alert("Please Enter Valid Credentials!!")} }
      ,err=>console.log(err),()=>console.log("User Login failed"));
   
  }

  userRegister():void{
   this.usSer.userSignUp(this.UserRegRef.value).subscribe(res=>this.RegUser=res,err=>console.log(err),()=>console.log("Register User"));
   this.UserRegRef.reset();
  }

}

