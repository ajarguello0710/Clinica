import { Exam } from './../../../../model/Exam';
import { FormGroup, FormControl } from '@angular/forms';
import { ConsultService } from 'src/app/service/consult.service';
import { DialogExamConsultSaveComponent } from './../dialog-exam-consult-save/dialog-exam-consult-save.component';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-dialog-exam-consult-edit',
  templateUrl: './dialog-exam-consult-edit.component.html',
  styleUrls: ['./dialog-exam-consult-edit.component.css']
})
export class DialogExamConsultEditComponent implements OnInit {

  formExamEdit: FormGroup;

  exam: Exam;

  constructor(
    private dialogRef: MatDialogRef<DialogExamConsultSaveComponent>,
    private consultServ: ConsultService,
    @Inject(MAT_DIALOG_DATA) private dataExamEdit: Exam
  ) { }

  ngOnInit() {
    this.edit();
  }

  edit() {
    this.formExamEdit = new FormGroup({
      id: new FormControl(this.dataExamEdit.id),
      name: new FormControl(this.dataExamEdit.name),
      description: new FormControl(this.dataExamEdit.description)
    });
  }

  action() {
    this.exam = new Exam();
    this.exam.id = this.formExamEdit.value.id;
    this.exam.name = this.formExamEdit.value.name;
    this.exam.description = this.formExamEdit.value.description;
    this.closeDialog();
    this.consultServ.reactExamEdit.next(this.exam);
  }

  closeDialog() {
    this.dialogRef.close();
  }

}
