import { Component } from '@angular/core';
import { RestaurantService } from '../service/restaurant.service';
import { Router } from '@angular/router';
import { Restaurant } from '../model/restaurant';

@Component({
  selector: 'app-add-restaurant',
  standalone: false,
  
  templateUrl: './add-restaurant.component.html',
  styleUrl: './add-restaurant.component.css'
})
export class AddRestaurantComponent {
  nameInput:String;
  descriptionInput:String;
  addressInput:String;
  ratingInput:Number;
  toAdd:Restaurant;

  constructor(private router:Router, public restaurantService:RestaurantService) {
    this.nameInput = "";
    this.descriptionInput = "";
    this.addressInput ="";
    this.ratingInput=0;
    this.toAdd = {id: 0, name: "", description: "", address: "", rating: 0, menu: null}
  }

  onSubmit() {
    console.log("submitted");

    this.toAdd = {"id": null, "name": this.nameInput, "description": this.descriptionInput, "address": this.addressInput, "rating": this.ratingInput, "menu": null};

    this.restaurantService.addRestaurant(this.toAdd)
    .subscribe(resp => {
      console.log(resp);
      if(!('error' in resp)){
        this.router.navigate(['/']);
      }
      else{
        console.log(resp['error']);
      }
    })
  }
}
