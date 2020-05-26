import { environment } from './../../../environments/environment';
import { LoginService } from './../../service/login.service';
import { FormGroup, FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  formLogin: FormGroup;

  constructor(
    private loginServ: LoginService
  ) { }

  ngOnInit() {
    this.login();
  }

  login() {
    this.formLogin = new FormGroup({
      user: new FormControl(''),
      password: new FormControl('')
    });
  }

  action() {
    console.log('Logeando :v');
    console.log(this.formLogin.value.user);
    console.log(this.formLogin.value.password);

    console.log('Coprobando c:');

    this.loginServ.login(this.formLogin.value.user, this.formLogin.value.password).subscribe(data => {
      // console.log(data);
      sessionStorage.setItem(environment.TOKEN_NAME, data.access_token);
    });
  }

}
