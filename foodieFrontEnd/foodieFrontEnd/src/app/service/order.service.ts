import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  url:string = "http://localhost:8080/foodorders"


  constructor(private http:HttpClient) { }

  getOrdersByUser(username:string):Observable<any> {
    return this.http.get<any>(this.url + "/user/" + username);
  }
}
