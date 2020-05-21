import { Subject } from 'rxjs';
import { Doctor } from './../model/Doctor';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  url = `${environment.HOST}/doctor`;

  reactVar = new Subject<string>();

  constructor(private http: HttpClient) { }

  list() {
    return this.http.get<Doctor[]>(`${this.url}/get`);
  }

  listPage(page: number, size: number) {
    return this.http.get<any>(`${this.url}/getPageable?page=${page}&size=${size}`);
  }

  save(doctor: Doctor) {
    return this.http.post(`${this.url}/save`, doctor);
  }

  edit(doctor: Doctor) {
    return this.http.put(`${this.url}/edit`, doctor);
  }

}
