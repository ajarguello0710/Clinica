import { Address } from './../../../model/Address';
import { FormGroup, FormControl } from '@angular/forms';
import { Patient } from './../../../model/Patients';
import { ActivatedRoute } from '@angular/router';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PatientService } from './../../../service/patient.service';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-dialog-patient',
  templateUrl: './dialog-patient.component.html',
  styleUrls: ['./dialog-patient.component.css']
})
export class DialogPatientComponent implements OnInit {

  patient: Patient;
  address: Address;
  isSaving = true;
  formPatient: FormGroup;

  constructor(
    private dialogRef: MatDialogRef<DialogPatientComponent>,
    private patientServ: PatientService,
    private router: ActivatedRoute,
    @Inject(MAT_DIALOG_DATA) private dataPatient: Patient
  ) { }

  ngOnInit() {
    if (Object.entries(this.dataPatient).length === 0) {
      this.save();
    } else {
      this.isSaving = false;
      this.edit();
    }
  }

  save() {
    this.formPatient = new FormGroup({
      id: new FormControl(''),
      name: new FormControl(''),
      lastName: new FormControl(''),
      address: new FormControl(''),
      neighborhood: new FormControl(''),
      dateBirth: new FormControl(''),
      mail: new FormControl(''),
      state: new FormControl('')
    });
  }

  edit() {
    console.log(this.dataPatient);
    this.formPatient = new FormGroup({
      id: new FormControl(this.dataPatient.id),
      name: new FormControl(this.dataPatient.name),
      lastName: new FormControl(this.dataPatient.lastName),
      dateBirth: new FormControl(this.dataPatient.dateBirth),
      address: new FormControl(this.dataPatient.address.address),
      neighborhood: new FormControl(this.dataPatient.address.neighborhood),
      mail: new FormControl(this.dataPatient.mail),
      state: new FormControl(this.dataPatient.state)
    });
  }

  action() {
    this.patient = new Patient();
    this.address = new Address();
    this.patient.name = this.formPatient.value.name;
    this.patient.lastName = this.formPatient.value.lastName;
    this.patient.dateBirth = this.formPatient.value.dateBirth;
    this.patient.mail = this.formPatient.value.mail;
    this.patient.state = this.formPatient.value.state;
    this.address.address = this.formPatient.value.address;
    this.address.neighborhood = this.formPatient.value.neighborhood;

    this.patient.address = this.address;

    if (this.isSaving) {
      console.log(this.patient);
      this.patientServ.save(this.patient).subscribe(() => {
        this.closeDialog();
        this.patientServ.reactVar.next('save');
      });
    } else {
      this.patient.id = this.formPatient.value.id;

      console.log(this.patient);
      this.patientServ.edit(this.patient).subscribe(() => {
        this.closeDialog();
        this.patientServ.reactVar.next('edit');
      });
    }
  }

  closeDialog() {
    this.dialogRef.close();
  }

}
