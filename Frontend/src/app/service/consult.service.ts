import { Subject } from 'rxjs';
import { environment } from './../../environments/environment';
import { Consult } from './../model/Consult';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConsultService {

  url = `${environment.HOST}/consult`;

  reactVar = new Subject<string>();

  constructor(private http: HttpClient) { }

  list() {
    return this.http.get<Consult[]>(`${this.url}/gets/false`);
  }

  edit(consult: Consult) {
    return this.http.put(`${this.url}/edit`, consult);
  }
}
