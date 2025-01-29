import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  url:string = "http://localhost:8080/restaurants"

  constructor(private http:HttpClient) { }

  getAllRestaurants():Observable<any> {
    return this.http.get<any>(this.url);
  }
}
