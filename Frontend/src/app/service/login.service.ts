import { Subject } from 'rxjs';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url = `${environment.HOST}/oauth/token`;

  // reactToolbar = new Subject<boolean>();

  constructor(
    private http: HttpClient,
    private router: Router
    ) { }

  login(user: string, password: string) {
    const body = `grant_type=password&username=${encodeURIComponent(user)}&password=${encodeURIComponent(password)}`;
    return this.http.post<any>(this.url, body, {
        headers: new HttpHeaders()
        .set('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8')
        .set('Authorization', 'Basic ' + btoa(environment.TOKEN_AUTH_USERNAME + ':' + environment.TOKEN_AUTH_PASSWORD))
    });
  }

  logout() {
    const token = sessionStorage.getItem(environment.TOKEN_NAME);

    this.http.get(`${environment.HOST}/logout/cancel/${token}`).subscribe(() => {
      sessionStorage.clear();
      // this.router.navigate([`/login`]);
      window.location.assign('/login');
    });
  }

  logged() {
    const token = sessionStorage.getItem(environment.TOKEN_NAME);
    return token != null;
  }

}
