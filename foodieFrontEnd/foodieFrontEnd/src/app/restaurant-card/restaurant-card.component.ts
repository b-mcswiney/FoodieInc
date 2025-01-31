import { Component, Input } from '@angular/core';
import { Restaurant } from '../model/restaurant';
import { UserService } from '../service/user.service';
import { RestaurantService } from '../service/restaurant.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-restaurant-card',
  standalone: false,
  
  templateUrl: './restaurant-card.component.html',
  styleUrl: './restaurant-card.component.css'
})
export class RestaurantCardComponent {
  @Input()
  restaurant:Restaurant
    constructor(private router:Router, public userService:UserService, public restaurantService:RestaurantService) {
      this.restaurant = {id:1, name:"",description:"", address:"", rating:1, menu:""}
    }

  deleteRestaurant(){
    console.log("delete", this.restaurant);
    this.restaurantService.deleteRestaurant(this.restaurant.id)
    .subscribe(resp =>{
      if(!('error' in resp)){
        window.location.reload();
      }
      else{
        console.log(resp['error']);
      }
    });
  }
}
