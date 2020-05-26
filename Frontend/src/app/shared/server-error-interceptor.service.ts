import { MatSnackBar } from '@angular/material';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpResponse } from '@angular/common/http';
import { Observable, EMPTY } from 'rxjs';
import { tap, catchError, retry } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ServerErrorInterceptorService implements HttpInterceptor {

  constructor(
    private snackBar: MatSnackBar,
    private router: Router) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(retry(environment.REINTENTOS)).pipe(tap(event => {
      if (event instanceof HttpResponse) {
        if (event.body && event.body.error === true && event.body.errorMessage) {
          throw new Error(event.body.errorMessage);
        }/*else{
                              //this.snackBar.open("EXITO", 'AVISO', { duration: 5000 });

                          }*/
      }
    })).pipe(catchError((err) => {
      console.log('Entro Error Filtro');
      console.log(err);
      // err.message
      // err.error.mensaje

      if (err.status === 400) {
        if (Object.entries(err.error.error_description).length === 0) {
          this.snackBar.open(err.error.message, 'ERROR', { duration: 5000 });
        } else {
          if (err.error.error_description === 'Bad credentials') {
            this.snackBar.open('Usuario o contraseña incorrectos', 'Invalido', { duration: 5000 });
          } else {
            this.snackBar.open(err.error.error_description, 'Invalido', { duration: 5000 });
          }
        }
      } else if (err.status === 401) {
        if (err.error.error === 'invalid_token') {
          this.snackBar.open('Token Inválido, intente de nuevo', 'Reintentar', { duration: 5000 });
          sessionStorage.clear();
        } else {
          this.snackBar.open(err.error.message, err.error.exception, { duration: 5000 });
          this.router.navigate(['/login']);
        }
      } else if (err.status === 500) {
        // this.snackBar.open(err.error.mensaje, 'ERROR 500', { duration: 5000 });
        if (err.error.message === 'Access is denied') {
          this.router.navigate([`/error/${err.status}/Acceso Denegado`]);
        } else {
          this.router.navigate([`/error/${err.status}/Lo sentimos`]);
        }
      } else {
        // this.snackBar.open(err.error.mensaje, 'ERROR', { duration: 5000 });
        this.router.navigate([`/error/Servidor/Temporamente Sin Servicio`]);
      }
      // this.snackBar.open(err.error.message.defaultmessage, 'ERROR', { duration: 5000 });
      // this.snackBar.open(`Error: ${err.status} Ha ocurrido un error, intente mas tarde`, 'ERROR', { duration: 5000 });
      // this.router.navigate([`/error/${err.status}/${err.error.mensaje}`]);
      // this.router.navigate([`/error/${err.status}/Lo sentimos`]);
      return EMPTY;
    }));

  }

}
