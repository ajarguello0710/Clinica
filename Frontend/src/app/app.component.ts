import { environment } from './../environments/environment';
import { LoginService } from './service/login.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Clinica';

  // token = sessionStorage.getItem(environment.TOKEN_NAME);
  // isValid = false;

  // // tslint:disable-next-line: use-lifecycle-interface
  // ngOnInit() {
  //   // tslint:disable-next-line: triple-equals
  //   if (this.token === null) {
  //     this.isValid = true;
  //     console.log('entro');
  //   } else {
  //     console.log('no entro');
  //   }
  // }

  constructor(private loginServ: LoginService) {}

  logout() {
    this.loginServ.logout();
  }
}
