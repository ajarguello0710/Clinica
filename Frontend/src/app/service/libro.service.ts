import { Libro } from './../model/Libro';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LibroService {

  varReactive = new Subject<string>();

  // url: string = environment.HOST + 'libros'; abajo la mejor forma
  url = `${environment.HOST}/libros`;

  constructor(private http: HttpClient) { }

  listar() {
    return this.http.get<Libro[]>(`${this.url}/obtener`);
  }

  guardar(libro: Libro) {
    return this.http.post(`${this.url}/guardar`, libro);
  }

  listarId(id: number) {
    return this.http.get<Libro>(`${this.url}/obtenerId/${id}`);
  }

  editar(libro: Libro) {
    return this.http.put(`${this.url}/editar`, libro);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.url}/eliminar/${id}`);
  }
}
