import { ConsultExamReport } from './../model/ConsultExamReport';
import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ConsultExam } from '../model/ConsultExam';

@Injectable({
  providedIn: 'root'
})
export class ConsultExamService {

  url = `${environment.HOST}/consultExam`;

  constructor(private http: HttpClient) { }

  list(id: number) {
    return this.http.get<ConsultExamReport>(`${this.url}/getId/${id}`);
  }

  edit(consultExam: ConsultExam) {
    return this.http.put(`${this.url}/edit`, consultExam);
  }

  save() {

  }

  saveConsult() {

  }
}
