import { environment } from './../../environments/environment';
import { LoginService } from './login.service';
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class GuardService implements CanActivate {

  constructor(
    private loginServ: LoginService,
    private router: Router
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    if (this.loginServ.logged) {

      const helper = new JwtHelperService();
      const token = sessionStorage.getItem(environment.TOKEN_NAME);
      if (!helper.isTokenExpired(token)) {

        const url = state.url;
        const decodedToken = helper.decodeToken(token);
        const rol: string = decodedToken.authorities[0];

        if (url.includes('doctor') && rol === 'Administrador') {
          return true;
        }
        if (url.includes('doctor') && rol === 'Medico') {
          return true;
        }
        if (url.includes('consult') && rol === 'Administrador') {
          return true;
        }
        if (url.includes('consult') && rol === 'Asistente') {
          return true;
        }
        if (url.includes('consult') && rol === 'Medico') {
          return true;
        }
        if (url.includes('patient') && rol === 'Administrador') {
          return true;
        }
        if (url.includes('patient') && rol === 'Paciente') {
          return true;
        }
        this.router.navigate([`/401`]);
        return false;

      } else {
        this.router.navigate([`/login`]);
        return false;
      }

    } else {
      sessionStorage.clear();
      this.router.navigate([`/401`]);
      return false;
    }

  }

}
