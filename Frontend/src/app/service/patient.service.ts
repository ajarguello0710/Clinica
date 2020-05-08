import { Patient } from './../model/Patients';
import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  url = `${environment.HOST}/doctor`;

  constructor(private http: HttpClient) { }

  list() {
    return this.http.get<Patient[]>(`${this.url}/get`);
  }
}
