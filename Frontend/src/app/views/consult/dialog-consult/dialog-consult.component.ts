import { Doctor } from './../../../model/Doctor';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PatientService } from './../../../service/patient.service';
import { FormGroup, FormControl } from '@angular/forms';
import { DoctorService } from './../../../service/doctor.service';
import { Consult } from './../../../model/Consult';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, OnInit, Inject } from '@angular/core';
import { ConsultService } from 'src/app/service/consult.service';

@Component({
  selector: 'app-dialog-consult',
  templateUrl: './dialog-consult.component.html',
  styleUrls: ['./dialog-consult.component.css']
})
export class DialogConsultComponent implements OnInit {

  consult: Consult;
  doctors = [];
  patients = [];
  doctorValue: Doctor;

  formConsultEdit: FormGroup;

  constructor(
    private dialogRef: MatDialogRef<DialogConsultComponent>,
    private doctorServ: DoctorService,
    private patientServ: PatientService,
    private consultServ: ConsultService,
    private snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) private dataConsult: Consult
  ) { }

  ngOnInit() {
    this.doctorServ.list().subscribe(dataDoctors => {
      // console.log(dataDoctors);
      this.doctors = dataDoctors;
    });

    this.patientServ.list().subscribe(dataPatients => {
      // console.log(dataPatients);
      this.patients = dataPatients;
    });

    this.edit();
  }

  edit() {
    this.formConsultEdit = new FormGroup({
      id: new FormControl(this.dataConsult.id),
      name: new FormControl(this.dataConsult.name),
      date: new FormControl(this.dataConsult.date),
      doctor: new FormControl(this.dataConsult.doctor),
      patient: new FormControl('')
    });
    // revisar no inicializa docotor
    this.doctorValue = this.dataConsult.doctor;
  }

  action() {
    const datosForm = {
      id: this.formConsultEdit.value.id,
      date: this.formConsultEdit.value.date,
      name: this.formConsultEdit.value.name,
      patient: {
        id: this.formConsultEdit.value.patient,
      },
      doctor: {
        id: this.formConsultEdit.value.doctor
      }
    };

    const json = JSON.stringify(datosForm);
    const dataDB = JSON.parse(json);

    // console.log(dataDB);

    this.consultServ.edit(dataDB).subscribe(() => {
      this.closeDialog();
      this.consultServ.reactVar.next('edit');
    });
  }

  closeDialog() {
    this.dialogRef.close();
  }

  showMessage(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 3000,
    });
  }

}
