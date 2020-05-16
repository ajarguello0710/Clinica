import { environment } from './../../environments/environment';
import { Subject } from 'rxjs';
import { ConsultDetail } from './../model/ConsultDetail';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConsultDetailService {

  url = `${environment.HOST}/consultDetail`;
  reactVar = new Subject<string>();

  constructor(private http: HttpClient) { }

  edit(consultDetail: ConsultDetail) {
    return this.http.put(`${this.url}/edit`, consultDetail);
  }
}
