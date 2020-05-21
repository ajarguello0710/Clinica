import { Subject } from 'rxjs';
import { Patient } from './../model/Patients';
import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  url = `${environment.HOST}/patient`;

  reactVar = new Subject<string>();

  constructor(private http: HttpClient) { }

  list() {
    return this.http.get<Patient[]>(`${this.url}/get`);
  }

  listPaginated(page: number, size: number) {
    return this.http.get<any>(`${this.url}/getPageable?page=${page}&size=${size}`);
  }

  save(patient: Patient) {
    return this.http.post(`${this.url}/save`, patient);
  }

  edit(patient: Patient) {
    return this.http.put(`${this.url}/edit`, patient);
  }
}
