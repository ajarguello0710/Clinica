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
          this.snackBar.open(err.error.message, 'ERROR', { duration: 5000 });
        } else if (err.status === 401) {
          this.snackBar.open(err.message, 'ERROR 401', { duration: 5000 });
          this.router.navigate(['/#']);
        } else if (err.status === 500) {
          // this.snackBar.open(err.error.mensaje, 'ERROR 500', { duration: 5000 });
          this.router.navigate([`/error/${err.status}/Lo sentimos`]);
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
