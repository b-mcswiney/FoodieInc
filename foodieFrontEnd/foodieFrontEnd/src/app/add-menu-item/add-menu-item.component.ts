import { Component } from '@angular/core';
import { Restaurant } from '../model/restaurant';
import { RestaurantService } from '../service/restaurant.service';
import { Menu } from '../model/menu';
import { MenuService } from '../service/menu.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-menu-item',
  standalone: false,
  
  templateUrl: './add-menu-item.component.html',
  styleUrl: './add-menu-item.component.css'
})
export class AddMenuItemComponent {
  nameInput:String;
  priceInput:number;
  restaurant:any;
  toAdd:Menu;
  restaurants:Restaurant[] = [];

  constructor(private router:Router, public restaurantService:RestaurantService, public menuService:MenuService) {
    this.nameInput = '';
    this.priceInput = 0;
    this.toAdd = {id: 0, name: "", price: 0, restaurant: null};
    this.restaurant = {id: 0, name: "", description: "", address: "", rating: 0, menu: null}
  }

  ngOnInit() {
    this.restaurantService.getAllRestaurants()
    .subscribe(resp => {
      console.log(resp);
      this.restaurants = resp;
    });
  }

  onSubmit(){
    this.toAdd = {id: null, name: this.nameInput, price: this.priceInput, restaurant: {"id": this.restaurant}}
    
    console.log(this.toAdd);
    this.menuService.addMenuItem(this.toAdd).subscribe(resp => {
      console.log(resp);
      this.router.navigate(['/restaurants/'+this.restaurant]);
    });
  }
}
