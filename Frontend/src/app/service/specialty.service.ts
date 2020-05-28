import { Subject } from 'rxjs';
import { Specialty } from './../model/Specialty';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SpecialtyService {

  url = `${environment.HOST}/specialty`;

  reactVar = new Subject<string>();

  constructor(private http: HttpClient) { }

  list() {
    return this.http.get<Specialty[]>(`${this.url}/get`);
  }

  save(specialty: Specialty) {
    return this.http.post(`${this.url}/save`, specialty);
  }

  edit(specialty: Specialty) {
    return this.http.put(`${this.url}/edit`, specialty);
  }
}
