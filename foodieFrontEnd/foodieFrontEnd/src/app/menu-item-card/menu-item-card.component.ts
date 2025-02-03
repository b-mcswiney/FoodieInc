import { Component, Input, input } from '@angular/core';
import { Menu } from '../model/menu';
import { UserService } from '../service/user.service';
import { MenuService } from '../service/menu.service';

@Component({
  selector: 'app-menu-item-card',
  standalone: false,
  
  templateUrl: './menu-item-card.component.html',
  styleUrl: './menu-item-card.component.css'
})
export class MenuItemCardComponent {
  @Input()
  item:Menu

  constructor(public userService:UserService, public menuService:MenuService){
    this.item ={id:0, name: "", price: 0, restaurant: 0};
  }

  deleteItem(){
    console.log("Deleted", this.item);

    this.menuService.deleteMenuItem(this.item.id).subscribe(resp=>{
      console.log(resp)
      if(!('error' in resp)){
        window.location.reload();
      }
      else{
        console.log(resp['error']);
      }
    });
  }
}
