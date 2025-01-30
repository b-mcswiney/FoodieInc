import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { User } from '../model/user';

@Component({
  selector: 'app-sign-up',
  standalone: false,
  
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent {
  usernameInput:String;
  passwordInput:String;
  emailInput:String;
  addressInput:String;
  toAdd:User;
  signupValid:boolean = true;

  constructor(private router:Router, public userService:UserService) {
    this.usernameInput="";
    this.passwordInput="";
    this.addressInput="";
    this.emailInput="";
    this.toAdd={"id": null, "username": this.usernameInput, "password": this.passwordInput, "email": this.emailInput, "address": this.addressInput};
  }

  onSubmit() {
    console.log("submitted");
    console.log("username: ",this.usernameInput, " password: ", this.passwordInput,
                " address: ", this.addressInput, " email: ", this.emailInput);
    this.toAdd = {"id": null, "username": this.usernameInput, "password": this.passwordInput, "email": this.emailInput, "address": this.addressInput};

    this.userService.addUser(this.toAdd)
      .subscribe(resp => {
        console.log(resp);
        if(!('error' in resp)){
          this.router.navigate(['/login']);
        }
        else{
          console.log(resp['error']);
        }
      });
  }
}
