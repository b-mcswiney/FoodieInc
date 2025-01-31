import { Component, Input } from '@angular/core';
import { Order } from '../model/order';
import { Menu } from '../model/menu';
import { MenuService } from '../service/menu.service';

@Component({
  selector: 'app-order-items',
  standalone: false,
  
  templateUrl: './order-items.component.html',
  styleUrl: './order-items.component.css'
})
export class OrderItemsComponent {
  order_items:Menu[] = [];
  @Input()
  order:Order

  
  constructor(public menuService:MenuService) {
    this.order = {id:1, user: null, restaurant: null, items: [], order_time: null}
  }

  ngOnInit(){
    console.log(this.order);

    this.order.items.forEach(item => {
      this.menuService.getMenuItemById(item).subscribe(resp => {
        this.order_items.push(resp);
      })
    })
  }
}
