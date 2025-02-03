import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RestaurantsComponent } from './restaurants/restaurants.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HttpClientModule } from '@angular/common/http';
import { RestaurantCardComponent } from './restaurant-card/restaurant-card.component';
import { RestaurantPageComponent } from './restaurant-page/restaurant-page.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { MyOrdersComponent } from './my-orders/my-orders.component';
import { OrderItemsComponent } from './order-items/order-items.component';
import { AddRestaurantComponent } from './add-restaurant/add-restaurant.component';
import { AddMenuItemComponent } from './add-menu-item/add-menu-item.component';
import { EditRestaurantComponent } from './edit-restaurant/edit-restaurant.component';
import { MenuItemCardComponent } from './menu-item-card/menu-item-card.component';

@NgModule({
  declarations: [
    AppComponent,
    RestaurantsComponent,
    NavbarComponent,
    RestaurantCardComponent,
    RestaurantPageComponent,
    PageNotFoundComponent,
    SignUpComponent,
    LoginComponent,
    MyOrdersComponent,
    OrderItemsComponent,
    AddRestaurantComponent,
    AddMenuItemComponent,
    EditRestaurantComponent,
    MenuItemCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
