import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserEntity } from './user-entity';
import { Observable} from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(public http:HttpClient) { }

  userSignIn(user:UserEntity):Observable<string>{
    return this.http.post("http://localhost:8282/UserActivity/signIn",user,{responseType:'text'});
  }
  userSignUp(user:UserEntity):Observable<string>{
    return this.http.post("http://localhost:8282/UserActivity/signUp",user,{responseType:'text'});
  }

  getAllUsers():Observable<UserEntity[]>{
    return this.http.get<UserEntity[]>(`http://localhost:8282/UserActivity/retrieveUsers`);
  }
  addUser(user:UserEntity):Observable<UserEntity>{
    return this.http.post<UserEntity>(`http://localhost:8282/UserActivity/createUser`,user);
  }
  deleteUserInfo(id:number):Observable<void>{
    return this.http.delete<void>(`http://localhost:8282/UserActivity/deleteUser/${id}`);
  }
  updateUser(user:UserEntity):Observable<UserEntity>{
    return this.http.put<UserEntity>(`http://localhost:8282/UserActivity/updateUser`,user);
  }
  

}
