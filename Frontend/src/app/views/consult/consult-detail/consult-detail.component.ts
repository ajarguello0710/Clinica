import { ConsultDetail } from './../../../model/ConsultDetail';
import { ExamReportDtos } from './../../../model/ExamReportDtos';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ConsultExamService } from './../../../service/consult-exam.service';
import { ActivatedRoute, Params } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-consult-detail',
  templateUrl: './consult-detail.component.html',
  styleUrls: ['./consult-detail.component.css']
})
export class ConsultDetailComponent implements OnInit {

  idExam: number;

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
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe((param: Params) => {
      this.idExam = param.id;
      // console.log(this.idExam);
    });

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

}
