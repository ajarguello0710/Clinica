import { GuardService } from './service/guard.service';
import { InicioComponent } from './views/inicio/inicio.component';
import { Not401Component } from './views/not401/not401.component';
import { LoginComponent } from './views/login/login.component';
import { ConsultDetailSaveComponent } from './views/consult/consult-detail-save/consult-detail-save.component';
import { ConsultDetailComponent } from './views/consult/consult-detail/consult-detail.component';
import { ConsultComponent } from './views/consult/consult.component';
import { PatientComponent } from './views/patient/patient.component';
import { DoctorComponent } from './views/doctor/doctor.component';
import { ErrorComponent } from './views/error/error.component';
import { Not404Component } from './views/not404/not404.component';
import { LibroComponent } from './views/libro/libro.component';
import { AutorComponent } from './views/autor/autor.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: 'doctor', component: DoctorComponent, canActivate: [GuardService] },
  { path: 'patient', component: PatientComponent, canActivate: [GuardService] },
  {
    path: 'consult', component: ConsultComponent, canActivate: [GuardService], children: [
      { path: 'detail/:id', component: ConsultDetailComponent },
      { path: 'save', component: ConsultDetailSaveComponent }
    ]
  },
  // {path: 'autor', component: AutorComponent},
  // {path: 'libro', component: LibroComponent},
  { path: '404', component: Not404Component },
  { path: '401', component: Not401Component },
  { path: 'error/:status/:message', component: ErrorComponent },
  { path: 'inicio', component: InicioComponent },
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: '**', redirectTo: '404', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
