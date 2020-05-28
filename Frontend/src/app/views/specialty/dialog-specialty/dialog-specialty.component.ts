import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { SpecialtyService } from './../../../service/specialty.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormGroup, FormControl } from '@angular/forms';
import { Specialty } from './../../../model/Specialty';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-dialog-specialty',
  templateUrl: './dialog-specialty.component.html',
  styleUrls: ['./dialog-specialty.component.css']
})
export class DialogSpecialtyComponent implements OnInit {

  specialty: Specialty;
  isSaving = true;
  formSpecialty: FormGroup;

  constructor(
    private dialogRef: MatDialogRef<DialogSpecialtyComponent>,
    private specialtyServ: SpecialtyService,
    private router: ActivatedRoute,
    private snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) private dataSpecialty: Specialty
  ) { }

  ngOnInit() {
    if (Object.entries(this.dataSpecialty).length === 0) {
      this.save();
    } else {
      this.isSaving = false;
      this.edit();
    }
  }

  save() {
    this.formSpecialty = new FormGroup({
      id: new FormControl(''),
      name: new FormControl('')
    });
  }

  edit() {
    this.formSpecialty = new FormGroup({
      id: new FormControl(this.dataSpecialty.id),
      name: new FormControl(this.dataSpecialty.name)
    });
  }

  action() {
    this.specialty = new Specialty();
    this.specialty.name = this.formSpecialty.value.name;
    if (this.isSaving) {
      this.specialtyServ.save(this.specialty).subscribe(() => {
        this.closeDialog();
        this.specialtyServ.reactVar.next('save');
      });
    } else {
      this.specialty.id = this.formSpecialty.value.id;
      this.specialtyServ.edit(this.specialty).subscribe(() => {
        this.closeDialog();
        this.specialtyServ.reactVar.next('edit');
      });
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
