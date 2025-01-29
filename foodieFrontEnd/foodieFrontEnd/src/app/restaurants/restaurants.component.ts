import { Component } from '@angular/core';
import { Restaurant } from '../model/restaurant';
import { RestaurantService } from '../service/restaurant.service';

@Component({
  selector: 'app-restaurants',
  standalone: false,
  
  templateUrl: './restaurants.component.html',
  styleUrl: './restaurants.component.css'
})
export class RestaurantsComponent {
  restaurants:Restaurant[] = [];
  constructor(public restaurantService:RestaurantService) {}

  ngOnInit(): void{
    console.log("restaurant component made");
    this.restaurantService.getAllRestaurants()
      .subscribe(resp =>{
        console.log('Fetched response');
        console.log(resp);
        this.restaurants = resp;
      })
  }
}
