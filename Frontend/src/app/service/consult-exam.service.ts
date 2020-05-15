import { environment } from './../../environments/environment';
import { ConsultExam } from './../model/ConsultExam';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConsultExamService {

  url = `${environment.HOST}/consultExam`;

  constructor(private http: HttpClient) { }

  list(id: number) {
    return this.http.get<ConsultExam>(`${this.url}/getId/${id}`);
  }
}
