import { Component } from '@angular/core';
import { Menu } from '../model/menu';
import { Restaurant } from '../model/restaurant';
import { ActivatedRoute, Router } from '@angular/router';
import { RestaurantService } from '../service/restaurant.service';
import { MenuService } from '../service/menu.service';

@Component({
  selector: 'app-edit-menu-item',
  standalone: false,
  
  templateUrl: './edit-menu-item.component.html',
  styleUrl: './edit-menu-item.component.css'
})
export class EditMenuItemComponent {
  menuId:Number;
  nameInput:String;
  priceInput:number;
  restaurant:any;
  toUpdate:Menu;
  restaurants:Restaurant[] = [];
  
  constructor(private currentRoute: ActivatedRoute, private router:Router, public restaurantService:RestaurantService, public menuService:MenuService) {
    this.menuId = 0;
    this.nameInput = '';
    this.priceInput = 0;
    this.toUpdate = {id: 0, name: "", price: 0, restaurant: null};
    this.restaurant = {id: 0, name: "", description: "", address: "", rating: 0, menu: null}
  }
  
  ngOnInit() {
    this.currentRoute.params.subscribe(params =>{
      this.menuId = params['id'];
    });

    this.menuService.getMenuItemById(this.menuId).subscribe(resp => {
      this.nameInput = resp.name;
      this.priceInput = resp.price;
      this.restaurant = resp.restaurant.id;
    })

    this.restaurantService.getAllRestaurants()
    .subscribe(resp => {
      console.log(resp);
      this.restaurants = resp;
    });
  }
  
  onSubmit(){
    this.toUpdate = {id: this.menuId, name: this.nameInput, price: this.priceInput, restaurant: {"id": this.restaurant}}
      
    console.log(this.toUpdate);
    this.menuService.editMenuItem(this.toUpdate).subscribe(resp => {
      console.log(resp);
      this.router.navigate(['/restaurants/'+this.restaurant]);
    });
  }
}
