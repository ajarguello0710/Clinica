import { ExamService } from './../../../service/exam.service';
import { ConsultExam } from './../../../model/ConsultExam';
import { DialogConsultExamComponent } from './dialog-consult-exam/dialog-consult-exam.component';
import { ConsultDetailService } from './../../../service/consult-detail.service';
import { DialogConsultDetailComponent } from './dialog-consult-detail/dialog-consult-detail.component';
import { MatDialog } from '@angular/material/dialog';
import { ConsultDetail } from './../../../model/ConsultDetail';
import { ExamReportDtos } from './../../../model/ExamReportDtos';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ConsultExamService } from './../../../service/consult-exam.service';
import { ActivatedRoute, Params } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { Exam } from 'src/app/model/Exam';

@Component({
  selector: 'app-consult-detail',
  templateUrl: './consult-detail.component.html',
  styleUrls: ['./consult-detail.component.css']
})
export class ConsultDetailComponent implements OnInit {

  idExam: number;
  exam: Exam;

  displayedColumnsExam: string[] = ['id', 'name', 'description', 'acciones'];
  dataSourceExam = new MatTableDataSource<ExamReportDtos>();

  displayedColumnsConsultDetail: string[] = ['id', 'diagnosis', 'treatment', 'acciones'];
  dataSourceConsultDetail = new MatTableDataSource<ConsultDetail>();

  @ViewChild(MatSort, { static: true }) mSortExam: MatSort;
  @ViewChild(MatPaginator, { static: true }) mPaginatorExam: MatPaginator;

  @ViewChild(MatSort, { static: true }) mSortConsultDetail: MatSort;
  @ViewChild(MatPaginator, { static: true }) mPaginatorConsultDetail: MatPaginator;

  constructor(
    private consultExamServ: ConsultExamService,
    private consultDetailService: ConsultDetailService,
    private examServ: ExamService,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
  ) { }

  ngOnInit() {
    this.route.params.subscribe((param: Params) => {
      this.idExam = param.id;
      // console.log(this.idExam);

      this.consultDetailService.reactVar.subscribe(data => {
        if (data === 'save') {
          this.showMessage('Detalle de la Consulta Guardada con éxito', 'Guardar');
        } else if (data === 'edit') {
          this.showMessage('Detalle de la Consulta Editada con éxito', 'Editar');
        } else if (data === 'delete') {
          this.showMessage('Detalle de la Consulta Eliminada con éxito', 'Eliminar');
        } else {
          this.showMessage('No se puede agregar el paciente y doctor', 'Error');
        }
        this.listConsultDetail();
      });

      this.examServ.reactVar.subscribe(data => {
        if (data === 'save') {
          this.showMessage('Exámen Guardado con éxito', 'Guardar');
        } else if (data === 'edit') {
          this.showMessage('Exámen Editado con éxito', 'Editar');
        } else if (data === 'delete') {
          this.showMessage('Exámen Eliminado con éxito', 'Eliminar');
        } else {
          this.showMessage('No se puede agregar el paciente y doctor', 'Error');
        }
        this.listExam();
      });
    });

    this.listConsultDetail();
    this.listExam();

  }

  listConsultDetail() {
    this.consultExamServ.list(this.idExam).subscribe(dataExamServ => {
      // console.log(dataExamServ.consultDto.consultDetails[0]);

      this.mPaginatorConsultDetail._intl.itemsPerPageLabel = 'Registros por página';
      this.mPaginatorConsultDetail._intl.nextPageLabel = 'Página siguiente';
      this.mPaginatorConsultDetail._intl.previousPageLabel = 'Página anterior';
      this.mPaginatorConsultDetail._intl.firstPageLabel = 'Primera Página';
      this.mPaginatorConsultDetail._intl.lastPageLabel = 'Ultima Página';
      this.dataSourceConsultDetail = new MatTableDataSource(dataExamServ.consultDto.consultDetails);
      this.dataSourceConsultDetail.sort = this.mSortConsultDetail;
      this.dataSourceConsultDetail.paginator = this.mPaginatorConsultDetail;
    });
  }

  listExam() {
    this.consultExamServ.list(this.idExam).subscribe(dataExamServ => {

      this.mPaginatorExam._intl.itemsPerPageLabel = 'Registros por página';
      this.mPaginatorExam._intl.nextPageLabel = 'Página siguiente';
      this.mPaginatorExam._intl.previousPageLabel = 'Página anterior';
      this.mPaginatorExam._intl.firstPageLabel = 'Primera Página';
      this.mPaginatorExam._intl.lastPageLabel = 'Ultima Página';
      this.dataSourceExam = new MatTableDataSource(dataExamServ.examReportDtos);
      this.dataSourceExam.sort = this.mSortExam;
      this.dataSourceExam.paginator = this.mPaginatorExam;
    });
  }

  applyFilterExam(filterValue: string) {
    this.dataSourceExam.filter = filterValue.trim().toLowerCase();
  }

  applyFilterExamConsultDetail(filterValue: string) {
    this.dataSourceConsultDetail.filter = filterValue.trim().toLowerCase();
  }

  openDiaogEditConsult(obj?: ConsultDetail) {
    const consultDetail = obj != null ? obj : new ConsultDetail();
    this.dialog.open(DialogConsultDetailComponent, {
      width: '40%',
      data: consultDetail
    });
  }

  openDialogEditExam(obj?: ExamReportDtos) {
    const exam = obj != null ? obj : new ExamReportDtos();
    this.dialog.open(DialogConsultExamComponent, {
      width: '40%',
      data: exam.exam
    });
  }

  showMessage(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 3000,
    });
  }

}
