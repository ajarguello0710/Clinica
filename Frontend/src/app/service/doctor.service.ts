import { Doctor } from './../model/Doctor';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  url = `${environment.HOST}/doctor`;

  constructor(private http: HttpClient) { }

  list() {
    return this.http.get<Doctor[]>(`${this.url}/get`);
  }
}
