import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RestaurantsComponent } from './restaurants/restaurants.component';
import { RestaurantPageComponent } from './restaurant-page/restaurant-page.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { MyOrdersComponent } from './my-orders/my-orders.component';
import { authGuard } from './service/auth.guard';
import { AddRestaurantComponent } from './add-restaurant/add-restaurant.component';
import { AddMenuItemComponent } from './add-menu-item/add-menu-item.component';
import { EditRestaurantComponent } from './edit-restaurant/edit-restaurant.component';
import { EditMenuItemComponent } from './edit-menu-item/edit-menu-item.component';

const routes: Routes = [
  {path: "", redirectTo:'restaurants', pathMatch:"full"},
  {path: "login", component:LoginComponent},
  {path: "signup", component:SignUpComponent},
  {path: "restaurants", component:RestaurantsComponent},
  {path: "restaurants/:id", component:RestaurantPageComponent},
  {path: "orders/:id", component:MyOrdersComponent, canActivate:[authGuard]},
  {path: "add/restaurant", component:AddRestaurantComponent, canActivate:[authGuard]},
  {path: "add/menu", component:AddMenuItemComponent, canActivate:[authGuard]},
  {path: "restaurants/edit/:id", component:EditRestaurantComponent, canActivate:[authGuard]},
  {path: "menu/edit/:id", component:EditMenuItemComponent, canActivate:[authGuard]},
  {path: "**", component:PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
