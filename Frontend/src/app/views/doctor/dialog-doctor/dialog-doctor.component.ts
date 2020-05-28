import { MatSnackBar } from '@angular/material/snack-bar';
import { Address } from './../../../model/Address';
import { FormGroup, FormControl } from '@angular/forms';
import { Doctor } from './../../../model/Doctor';
import { ActivatedRoute } from '@angular/router';
import { DoctorService } from './../../../service/doctor.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-dialog-doctor',
  templateUrl: './dialog-doctor.component.html',
  styleUrls: ['./dialog-doctor.component.css']
})
export class DialogDoctorComponent implements OnInit {

  doctor: Doctor;
  address: Address;
  isSaving = true;
  formDoctor: FormGroup;
  valid = false;

  dateSelected: Date;
  dateNow: Date = new Date();

  constructor(
    private dialogRef: MatDialogRef<DialogDoctorComponent>,
    private doctorServ: DoctorService,
    private router: ActivatedRoute,
    private snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) private dataDoctor: Doctor
  ) { }

  ngOnInit() {
    if (Object.entries(this.dataDoctor).length === 0) {
      this.save();
    } else {
      this.isSaving = false;
      this.edit();
    }
  }

  save() {
    this.formDoctor = new FormGroup({
      id: new FormControl(''),
      idAddress: new FormControl(''),
      name: new FormControl(''),
      lastName: new FormControl(''),
      birthDate: new FormControl(''),
      address: new FormControl(''),
      neighborhood: new FormControl(''),
      state: new FormControl('')
    });
  }

  edit() {
    console.log(this.dataDoctor);
    this.formDoctor = new FormGroup({
      id: new FormControl(this.dataDoctor.id),
      idAddress: new FormControl(this.dataDoctor.address.id),
      name: new FormControl(this.dataDoctor.name),
      lastName: new FormControl(this.dataDoctor.lastName),
      address: new FormControl(this.dataDoctor.address.address),
      neighborhood: new FormControl(this.dataDoctor.address.neighborhood),
      birthDate: new FormControl(this.dataDoctor.dateBirth),
      state: new FormControl(this.dataDoctor.state)
    });
  }

  action() {
    this.dateSelected = this.formDoctor.value.birthDate;
    if (this.isSaving) {
      if (this.dateSelected.getDate() > this.dateNow.getDate()) {
        this.showMessage('Por favor, seleccionar una fecha válida', 'Error');
      } else {
        this.valid = true;
      }
    } else {
      if (this.dateSelected > this.dateNow) {
        this.showMessage('Por favor, seleccionar una fecha válida', 'Error');
      } else {
        this.valid = true;
      }
    }

    if (this.valid === true) {
      this.doctor = new Doctor();
      this.address = new Address();
      this.doctor.name = this.formDoctor.value.name;
      this.doctor.lastName = this.formDoctor.value.lastName;
      this.doctor.dateBirth = this.formDoctor.value.birthDate;
      this.address.address = this.formDoctor.value.address;
      this.address.neighborhood = this.formDoctor.value.neighborhood;
      this.doctor.state = this.formDoctor.value.state;


      // console.log(this.doctor);
      // console.log(this.address);
      if (this.isSaving) {

        // console.log(this.doctor);
        this.doctor.address = this.address;
        this.doctorServ.save(this.doctor).subscribe(() => {
          this.closeDialog();
          this.doctorServ.reactVar.next('save');
        });
      } else {
        this.doctor.id = this.formDoctor.value.id;
        this.address.id = this.formDoctor.value.idAddress;
        this.doctor.address = this.address;

        // console.log(this.doctor);
        this.doctorServ.edit(this.doctor).subscribe(() => {
          this.closeDialog();
          this.doctorServ.reactVar.next('edit');
        });
      }
    }

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
