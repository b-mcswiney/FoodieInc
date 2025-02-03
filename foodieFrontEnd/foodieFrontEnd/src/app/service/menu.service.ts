import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Menu } from '../model/menu';

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

  addMenuItem(toAdd:Menu):Observable<any> {
    return this.http.post(this.url, toAdd);
  }

  deleteMenuItem(id:number):Observable<any> {
    return this.http.delete<any>(this.url+"/"+id);
  }
}
