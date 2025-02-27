import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-login',
  standalone: false,
  
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  usernameInput:string;
  passwordInput:string;
  loginValid:boolean = true;

  constructor(private router:Router, private userService:UserService) {
    this.usernameInput = "";
    this.passwordInput = "";
  }

  onSubmit(){
    console.log("login", this.usernameInput, this.passwordInput);
    this.loginValid=false;

    this.userService.getUserByName(this.usernameInput)
    .subscribe({
      next: resp=>{
        if(resp.status == 400){
          this.loginValid = false;
        }
        if(resp != undefined && resp.length != 0 && resp.username == this.usernameInput && resp.password == this.passwordInput) {
          localStorage.setItem("username", this.usernameInput);
          console.log(resp.id);
          localStorage.setItem("id", resp.id);
          this.loginValid = true;
          this.router.navigate(['']);
        }
        else{
          this.loginValid = false;
        }
      }
    })
  }
}
