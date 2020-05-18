import { FormGroup, FormControl } from '@angular/forms';
import { ConsultService } from 'src/app/service/consult.service';
import { MatDialogRef } from '@angular/material/dialog';
import { ConsultDetail } from './../../../../model/ConsultDetail';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dialog-detail-consult-save',
  templateUrl: './dialog-detail-consult-save.component.html',
  styleUrls: ['./dialog-detail-consult-save.component.css']
})
export class DialogDetailConsultSaveComponent implements OnInit {

  formDetailSave: FormGroup;

  consultDetail: ConsultDetail;

  constructor(
    private dialogRef: MatDialogRef<DialogDetailConsultSaveComponent>,
    private consultServ: ConsultService
  ) { }

  ngOnInit() {
    this.save();
  }

  save() {
    this.formDetailSave = new FormGroup({
      id: new FormControl('0'),
      diagnosis: new FormControl(''),
      treatment: new FormControl('')
    });
  }

  action() {
    this.consultDetail = new ConsultDetail();
    this.consultDetail.diagnosis = this.formDetailSave.value.diagnosis;
    this.consultDetail.treatment = this.formDetailSave.value.treatment;
    this.closeDialog();
    this.consultServ.reactDetail.next(this.consultDetail);
  }

  closeDialog() {
    this.dialogRef.close();
  }

}
