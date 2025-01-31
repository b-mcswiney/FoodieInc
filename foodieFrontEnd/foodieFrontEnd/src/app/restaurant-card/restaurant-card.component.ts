import { Component, Input } from '@angular/core';
import { Restaurant } from '../model/restaurant';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-restaurant-card',
  standalone: false,
  
  templateUrl: './restaurant-card.component.html',
  styleUrl: './restaurant-card.component.css'
})
export class RestaurantCardComponent {
  @Input()
  restaurant:Restaurant
    constructor(public userService:UserService) {
      this.restaurant = {id:1, name:"",description:"", address:"", rating:1, menu:""}
    }
}
