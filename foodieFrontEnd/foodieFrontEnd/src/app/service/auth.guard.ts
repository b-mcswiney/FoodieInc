import { CanActivateFn, Router } from '@angular/router';
import { UserService } from './user.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const us:UserService = inject(UserService);
  const router:Router = inject(Router);

  if(us.isAuthenticated()){
    return true;
  }

  alert('please login');
  router.navigate(['login']);
  return false;
};
