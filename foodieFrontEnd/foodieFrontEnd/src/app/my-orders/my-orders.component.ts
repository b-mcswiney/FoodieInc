import { Component } from '@angular/core';
import { Order } from '../model/order';
import { OrderService } from '../service/order.service';
import { MenuService } from '../service/menu.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-my-orders',
  standalone: false,
  
  templateUrl: './my-orders.component.html',
  styleUrl: './my-orders.component.css'
})
export class MyOrdersComponent {
  orderItems:Order[] = [];
  username:any;
  orderDisplay:boolean[] = [];
  count:number = 0;

  constructor(
    public orderService:OrderService,
    public menuService:MenuService,
  ) {}

  ngOnInit() {
    this.username = localStorage.getItem("username");

    this.orderService.getOrdersByUser(this.username).subscribe(resp => {
      console.log(resp);
      this.orderItems = resp;

      this.count = 0;
      while(this.count < this.orderItems.length){
        this.orderDisplay[this.count] = false;
        this.count = this.count+1;
      }
      console.log(this.orderItems);
      // this.orderItems.forEach(item => {
      //   item.items.forEach(value => {
      //     this.menuService.getMenuItemById(value).subscribe(response => {
      //       value = response;
      //       console.log(value);
      //     });
      //   });
      // });
    });
  }
}
