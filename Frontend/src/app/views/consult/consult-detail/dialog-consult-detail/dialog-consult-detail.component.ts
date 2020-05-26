import { ActivatedRoute, Params, Router } from '@angular/router';
import { ConsultDetailService } from './../../../../service/consult-detail.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormGroup, FormControl } from '@angular/forms';
import { ConsultDetail } from './../../../../model/ConsultDetail';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-dialog-consult-detail',
  templateUrl: './dialog-consult-detail.component.html',
  styleUrls: ['./dialog-consult-detail.component.css']
})
export class DialogConsultDetailComponent implements OnInit {

  consultDetail: ConsultDetail;
  formConsultDetail: FormGroup;
  isSaving = true;

  path: string;
  idConsult: number;
  regex = /(\d+)/g;

  private readonly newProperty = this.idConsult;

  constructor(
    private dialogRef: MatDialogRef<DialogConsultDetailComponent>,
    private consultDetailServ: ConsultDetailService,
    private snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) private dataConsultDetail: ConsultDetail,
    private router: Router
  ) { }

  ngOnInit() {

    this.path = this.router.url;
    const id = this.path.match(this.regex);
    id.forEach(element => {
      this.idConsult = Number(element);
    });

    if (Object.entries(this.dataConsultDetail).length === 0) {
      this.save();
    } else {
      this.isSaving = false;
      this.edit();
    }
  }

  save() {
    this.formConsultDetail = new FormGroup({
      id: new FormControl(''),
      diagnosis: new FormControl(''),
      treatment: new FormControl('')
    });
  }

  edit() {
    this.formConsultDetail = new FormGroup({
      id: new FormControl(this.dataConsultDetail.id),
      diagnosis: new FormControl(this.dataConsultDetail.diagnosis),
      treatment: new FormControl(this.dataConsultDetail.treatment)
    });
  }

  action() {
    this.consultDetail = new ConsultDetail();
    this.consultDetail.diagnosis = this.formConsultDetail.value.diagnosis;
    this.consultDetail.treatment = this.formConsultDetail.value.treatment;
    if (this.isSaving) {
      const dataForm = {
        diagnosis: this.formConsultDetail.value.diagnosis,
        treatment: this.formConsultDetail.value.treatment,
        consult: {
          id: this.idConsult
        }
      };

      const json = JSON.stringify(dataForm);
      const dataDB = JSON.parse(json);
      // console.log(dataDB);

      this.consultDetailServ.save(dataDB).subscribe(() => {
        this.closeDialog();
        this.consultDetailServ.reactVar.next('save');
      });
    } else {
      this.consultDetail.id = this.formConsultDetail.value.id;
      this.consultDetailServ.edit(this.consultDetail).subscribe(() => {
        this.closeDialog();
        this.consultDetailServ.reactVar.next('edit');
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
