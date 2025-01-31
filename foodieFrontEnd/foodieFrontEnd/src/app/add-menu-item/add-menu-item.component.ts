import { Component } from '@angular/core';
import { Restaurant } from '../model/restaurant';
import { RestaurantService } from '../service/restaurant.service';

@Component({
  selector: 'app-add-menu-item',
  standalone: false,
  
  templateUrl: './add-menu-item.component.html',
  styleUrl: './add-menu-item.component.css'
})
export class AddMenuItemComponent {
  nameInput:String;
  priceInput:number|undefined;
  restaurant:any;
  restaurants:Restaurant[] = [];

  constructor(public restaurantService:RestaurantService) {
    this.nameInput = '';
    this.restaurant = {id: 0, name: "", description: "", address: "", rating: 0, menu: null}
  }

  ngOnInit() {
    // TODO: get all restaurants for drop down menu
  }

  onSubmit(){
    // TODO: submit new menu item to db
  }
}
