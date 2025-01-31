import { Component } from '@angular/core';
import { Restaurant } from '../model/restaurant';
import { ActivatedRoute, Router } from '@angular/router';
import { RestaurantService } from '../service/restaurant.service';

@Component({
  selector: 'app-edit-restaurant',
  standalone: false,
  
  templateUrl: './edit-restaurant.component.html',
  styleUrl: './edit-restaurant.component.css'
})
export class EditRestaurantComponent {
  restaurantId:Number;
  nameInput:String;
  descriptionInput:String;
  addressInput:String;
  ratingInput:Number;
  toEdit:Restaurant;

  constructor(private currentRoute: ActivatedRoute, private router:Router, public restaurantService:RestaurantService) {
    this.restaurantId = 0;
    this.nameInput = "";
    this.descriptionInput = "";
    this.addressInput ="";
    this.ratingInput=0;
    this.toEdit = {id: 0, name: "", description: "", address: "", rating: 0, menu: null}
  }

  ngOnInit() {
    this.currentRoute.params.subscribe(params => {
      this.restaurantId = params['id'];
    });

    this.restaurantService.getRestaurantById(this.restaurantId)
    .subscribe(resp => {
      this.nameInput = resp.name;
      this.addressInput = resp.address;
      this.descriptionInput = resp.description;
      this.ratingInput = resp.rating;
    });
  }

  onSubmit() {
    console.log("submitted");

    this.toEdit = {"id": this.restaurantId, "name": this.nameInput, "description": this.descriptionInput, "address": this.addressInput, "rating": this.ratingInput, "menu": null};

    this.restaurantService.editRestaurant(this.toEdit)
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
