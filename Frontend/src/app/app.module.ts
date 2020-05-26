import { environment } from './../environments/environment';
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
import { DialogConsultExamComponent } from './views/consult/consult-detail/dialog-consult-exam/dialog-consult-exam.component';
import { ConsultDetailSaveComponent } from './views/consult/consult-detail-save/consult-detail-save.component';
// tslint:disable-next-line: max-line-length
import { DialogExamConsultSaveComponent } from './views/consult/consult-detail-save/dialog-exam-consult-save/dialog-exam-consult-save.component';
// tslint:disable-next-line: max-line-length
import { DialogDetailConsultSaveComponent } from './views/consult/consult-detail-save/dialog-detail-consult-save/dialog-detail-consult-save.component';
// tslint:disable-next-line: max-line-length
import { DialogExamConsultEditComponent } from './views/consult/consult-detail-save/dialog-exam-consult-edit/dialog-exam-consult-edit.component';
// tslint:disable-next-line: max-line-length
import { DialogDetailConsultEditComponent } from './views/consult/consult-detail-save/dialog-detail-consult-edit/dialog-detail-consult-edit.component';
import { DialogDoctorComponent } from './views/doctor/dialog-doctor/dialog-doctor.component';
import { DialogPatientComponent } from './views/patient/dialog-patient/dialog-patient.component';
import { LoginComponent } from './views/login/login.component';

import { JwtModule } from '@auth0/angular-jwt';
import { Not401Component } from './views/not401/not401.component';
import { HomeComponent } from './views/home/home.component';


export function tokenGetter() {
  return sessionStorage.getItem(environment.TOKEN_NAME);
}


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
    DialogConsultExamComponent,
    ConsultDetailSaveComponent,
    DialogExamConsultSaveComponent,
    DialogDetailConsultSaveComponent,
    DialogExamConsultEditComponent,
    DialogDetailConsultEditComponent,
    DialogDoctorComponent,
    DialogPatientComponent,
    LoginComponent,
    Not401Component,
    HomeComponent,
  ], entryComponents: [
    DialogConsultComponent,
    DialogConsultDetailComponent,
    DialogConsultExamComponent,
    DialogExamConsultSaveComponent,
    DialogDetailConsultSaveComponent,
    DialogExamConsultEditComponent,
    DialogDetailConsultEditComponent,
    DialogDoctorComponent,
    DialogPatientComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ApiReferenceModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    JwtModule.forRoot({
      config: {
        tokenGetter,
        whitelistedDomains: [
          'localhost:8080',
          // ''
        ],
        blacklistedRoutes: [
          'http://localhost:8080/login',
          // ''
        ]
      }
    })
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
