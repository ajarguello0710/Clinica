import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Autor } from '../model/Autor';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AutorService {

  varReactive = new Subject<string>();
  url = `${environment.HOST}/autores`;

  constructor(private http: HttpClient) { }

  listar() {
    return this.http.get<Autor[]>(`${this.url}/obtener`);
  }

  guardar(autor: Autor) {
    return this.http.post(`${this.url}/guardar`, autor);
  }

  listarId(id: number) {
    return this.http.get<Autor>(`${this.url}/obtenerId/${id}`);
  }

  editar(autor: Autor) {
    return this.http.put(`${this.url}/editar`, autor);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.url}/eliminar/${id}`);
  }
}
