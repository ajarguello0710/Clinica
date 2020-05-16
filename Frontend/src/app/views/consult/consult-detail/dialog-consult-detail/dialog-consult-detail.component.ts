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

  formConsultDetailEdit: FormGroup;

  constructor(
    private dialogRef: MatDialogRef<DialogConsultDetailComponent>,
    private consultDetailServ: ConsultDetailService,
    private snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) private dataConsultDetail: ConsultDetail
  ) { }

  ngOnInit() {
    this.edit();
  }

  edit() {
    this.formConsultDetailEdit = new FormGroup({
      id: new FormControl(this.dataConsultDetail.id),
      diagnosis: new FormControl(this.dataConsultDetail.diagnosis),
      treatment: new FormControl(this.dataConsultDetail.treatment)
    });
  }

  action() {
    this.consultDetail = new ConsultDetail();
    this.consultDetail.id = this.formConsultDetailEdit.value.id;
    this.consultDetail.diagnosis = this.formConsultDetailEdit.value.diagnosis;
    this.consultDetail.treatment = this.formConsultDetailEdit.value.treatment;

    this.consultDetailServ.edit(this.consultDetail).subscribe(() => {
      this.closeDialog();
      this.consultDetailServ.reactVar.next('edit');
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
