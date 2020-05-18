import { ConsultService } from 'src/app/service/consult.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ConsultDetail } from './../../../../model/ConsultDetail';
import { FormGroup, FormControl } from '@angular/forms';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-dialog-detail-consult-edit',
  templateUrl: './dialog-detail-consult-edit.component.html',
  styleUrls: ['./dialog-detail-consult-edit.component.css']
})
export class DialogDetailConsultEditComponent implements OnInit {

  formDetailEdit: FormGroup;

  consultDetail: ConsultDetail;

  constructor(
    private dialogRef: MatDialogRef<DialogDetailConsultEditComponent>,
    private consultServ: ConsultService,
    @Inject(MAT_DIALOG_DATA) private dataDetailEdit: ConsultDetail
  ) { }

  ngOnInit() {
    this.edit();
  }

  edit() {
    this.formDetailEdit = new FormGroup({
      id: new FormControl(this.dataDetailEdit.id),
      diagnosis: new FormControl(this.dataDetailEdit.diagnosis),
      treatment: new FormControl(this.dataDetailEdit.treatment)
    });
  }

  action() {
    this.consultDetail = new ConsultDetail();
    this.consultDetail.id = this.formDetailEdit.value.id;
    this.consultDetail.diagnosis = this.formDetailEdit.value.diagnosis;
    this.consultDetail.treatment = this.formDetailEdit.value.treatment;
    this.closeDialog();
    this.consultServ.reactDetailEdit.next(this.consultDetail);
  }

  closeDialog() {
    this.dialogRef.close();
  }

}
