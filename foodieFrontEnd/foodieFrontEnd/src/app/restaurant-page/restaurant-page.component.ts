import { Component, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MenuService } from '../service/menu.service';
import { Menu } from '../model/menu';
import { Restaurant } from '../model/restaurant';
import { RestaurantService } from '../service/restaurant.service';

@Component({
  selector: 'app-restaurant-page',
  standalone: false,
  
  templateUrl: './restaurant-page.component.html',
  styleUrl: './restaurant-page.component.css'
})
export class RestaurantPageComponent {
  restaurantId:Number = 0;
  restaurant:Restaurant = {id:1, name:"",description:"", address:"", rating:1, menu:""};
  menuItems:Menu[] = [];

  constructor(private currentRoute: ActivatedRoute, public menuService:MenuService, public restaurantService:RestaurantService){}

  ngOnInit() {
    this.currentRoute.params.subscribe(params => {
      this.restaurantId = params['id'];
    });

    console.log("Restaurant ID: ", this.restaurantId);

    this.menuService.getMenuItemsByRestaurant(this.restaurantId)
      .subscribe(resp => {
        console.log(resp);
        this.menuItems = resp;
        console.log(this.menuItems);
      });
    this.restaurantService.getRestaurantById(this.restaurantId)
      .subscribe(resp => {
        this.restaurant = resp;
      })
  }
}
