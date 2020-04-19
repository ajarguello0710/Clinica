import { MaterialModule } from './_material/material/material.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddressComponent } from './views/address/address.component';
import { ConsultComponent } from './views/consult/consult.component';
import { ConsultDetailComponent } from './views/consult-detail/consult-detail.component';
import { DoctorComponent } from './views/doctor/doctor.component';
import { PatientComponent } from './views/patient/patient.component';
import { SpecialtyComponent } from './views/specialty/specialty.component';

@NgModule({
  declarations: [
    AppComponent,
    AddressComponent,
    ConsultComponent,
    ConsultDetailComponent,
    DoctorComponent,
    PatientComponent,
    SpecialtyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
