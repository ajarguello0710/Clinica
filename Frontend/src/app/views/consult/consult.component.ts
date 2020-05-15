import { ActivatedRoute } from '@angular/router';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Consult } from './../../model/Consult';
import { MatTableDataSource } from '@angular/material/table';
import { ConsultService } from './../../service/consult.service';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-consult',
  templateUrl: './consult.component.html',
  styleUrls: ['./consult.component.css']
})
export class ConsultComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'date', 'acciones'];
  dataSource = new MatTableDataSource<Consult>();

  @ViewChild(MatSort, { static: true }) mSort: MatSort;
  @ViewChild(MatPaginator, { static: true }) mPaginator: MatPaginator;

  constructor(  private consultServ: ConsultService,
                public route: ActivatedRoute) { }

  ngOnInit() {
    this.consultServ.list().subscribe(dataConsultServ => {
      // console.log(dataConsultServ);

      this.mPaginator._intl.itemsPerPageLabel = 'Registros por página';
      this.mPaginator._intl.nextPageLabel = 'Página siguiente';
      this.mPaginator._intl.previousPageLabel = 'Página anterior';
      this.mPaginator._intl.firstPageLabel = 'Primera Página';
      this.mPaginator._intl.lastPageLabel = 'Ultima Página';
      this.dataSource = new MatTableDataSource(dataConsultServ);
      this.dataSource.sort = this.mSort;
      this.dataSource.paginator = this.mPaginator;
    });
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
