import { ServerErrorInterceptorService } from './shared/server-error-interceptor.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
/**
 * Referencias de los componentes
 */
import { AutorComponent } from './views/autor/autor.component';
import { LibroComponent } from './views/libro/libro.component';
import { ApiReferenceModule } from './api/api-reference.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AutorDialogComponent } from './views/autor/autor-dialog/autor-dialog.component';
import { Not404Component } from './views/not404/not404.component';
import { ErrorComponent } from './views/error/error.component';
import { DoctorComponent } from './views/doctor/doctor.component';
import { PatientComponent } from './views/patient/patient.component';
import { ConsultComponent } from './views/consult/consult.component';
import { ConsultDetailComponent } from './views/consult/consult-detail/consult-detail.component';
import { DialogConsultComponent } from './views/consult/dialog-consult/dialog-consult.component';
import { DialogConsultDetailComponent } from './views/consult/consult-detail/dialog-consult-detail/dialog-consult-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    AutorComponent,
    LibroComponent,
    AutorDialogComponent,
    Not404Component,
    ErrorComponent,
    DoctorComponent,
    PatientComponent,
    ConsultComponent,
    ConsultDetailComponent,
    DialogConsultComponent,
    DialogConsultDetailComponent,
  ], entryComponents: [
    DialogConsultComponent,
    DialogConsultDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ApiReferenceModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ServerErrorInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
