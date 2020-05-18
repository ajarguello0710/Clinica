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
  {path: 'doctor', component: DoctorComponent},
  {path: 'patient', component: PatientComponent},
  {path: 'consult', component: ConsultComponent, children: [
    {path: 'detail/:id', component: ConsultDetailComponent},
    {path: 'save', component: ConsultDetailSaveComponent}
  ]},
  // {path: 'autor', component: AutorComponent},
  // {path: 'libro', component: LibroComponent},
  {path: '404', component: Not404Component},
  {path: 'error/:status/:message', component: ErrorComponent},
  {path: '', redirectTo: 'doctor', pathMatch: 'full'},
  {path: '**', redirectTo: '404', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
