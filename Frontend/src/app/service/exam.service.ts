import { Exam } from 'src/app/model/Exam';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ExamService {

  url = `${environment.HOST}/exam`;

  reactVar = new Subject<string>();

  constructor(private http: HttpClient) { }

  edit(exam: Exam) {
    return this.http.put(`${this.url}/edit`, exam);
  }
}
