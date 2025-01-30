import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { User } from '../model/user';
import { catchError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  url:string = "http://localhost:8080/users"
  
  constructor(private http:HttpClient) { }
  
  addUser(user:User):Observable<any> {
    return this.http.post<User>(this.url, user);
  }
}
