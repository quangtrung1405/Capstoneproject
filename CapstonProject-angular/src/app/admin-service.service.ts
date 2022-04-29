import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AdminEntity } from './admin-entity';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {

  constructor(public http:HttpClient) { }

  adminSignUp(admin:AdminEntity):Observable<string>{
    return this.http.post("http://localhost:8181/AdminActivity/signUp",admin,{responseType:'text'});
  }
  adminSignIn(admin:AdminEntity):Observable<string>{
    return this.http.post("http://localhost:8181/AdminActivity/signIn",admin,{responseType:'text'});
  }
}
