import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Restaurant } from '../model/restaurant';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  url:string = "http://localhost:9090/restaurants"

  constructor(private http:HttpClient) { }

  getAllRestaurants():Observable<any> {
    return this.http.get<any>(this.url);
  }

  getRestaurantById(id:Number):Observable<any>{
    return this.http.get<any>(this.url+"/"+id);
  }

  addRestaurant(toAdd:Restaurant):Observable<any>{
    return this.http.post<any>(this.url, toAdd);
  }

  editRestaurant(toEdit:Restaurant):Observable<any>{
    return this.http.put<any>(this.url, toEdit);
  }

  deleteRestaurant(id:any):Observable<any>{
    return this.http.delete<any>(this.url + "/" + id);
  }
}
