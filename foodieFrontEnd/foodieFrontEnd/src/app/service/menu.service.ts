import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MenuService {
  url:string = "http://localhost:8080/menu"

  constructor(private http:HttpClient) { }

  getAllMenuItems():Observable<any> {
    return this.http.get<any>(this.url);
  }

  getMenuItemById(id:Number):Observable<any> {
    return this.http.get<any>(this.url + "/" + id);
  }

  getMenuItemsByRestaurant(id:Number):Observable<any> {
    return this.http.get<any>(this.url + "/restaurant/" + id);
  }
}
