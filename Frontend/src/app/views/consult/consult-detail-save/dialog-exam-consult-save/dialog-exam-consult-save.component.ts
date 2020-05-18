import { ConsultService } from 'src/app/service/consult.service';
import { Exam } from './../../../../model/Exam';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dialog-exam-consult-save',
  templateUrl: './dialog-exam-consult-save.component.html',
  styleUrls: ['./dialog-exam-consult-save.component.css']
})
export class DialogExamConsultSaveComponent implements OnInit {

  formExamSave: FormGroup;

  exam: Exam;

  constructor(
    private dialogRef: MatDialogRef<DialogExamConsultSaveComponent>,
    private consultServ: ConsultService
  ) { }

  ngOnInit() {
    this.save();
  }

  save() {
    this.formExamSave = new FormGroup({
      id: new FormControl('0'),
      name: new FormControl(''),
      description: new FormControl('')
    });
  }

  action() {
    this.exam = new Exam();
    this.exam.name = this.formExamSave.value.name;
    this.exam.description = this.formExamSave.value.description;
    this.closeDialog();
    this.consultServ.reactExam.next(this.exam);
  }

  closeDialog() {
    this.dialogRef.close();
  }

}
