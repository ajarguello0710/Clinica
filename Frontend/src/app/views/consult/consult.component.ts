import { DialogConsultComponent } from './dialog-consult/dialog-consult.component';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Consult } from './../../model/Consult';
import { MatTableDataSource } from '@angular/material/table';
import { ConsultService } from './../../service/consult.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-consult',
  templateUrl: './consult.component.html',
  styleUrls: ['./consult.component.css']
})
export class ConsultComponent implements OnInit {

  showProgressBar = true;

  totalElements = 0;

  displayedColumns: string[] = ['id', 'name', 'date', 'doctor', 'acciones'];
  dataSource = new MatTableDataSource<Consult>();

  @ViewChild(MatSort, { static: true }) mSort: MatSort;
  @ViewChild(MatPaginator, { static: true }) mPaginator: MatPaginator;

  constructor(
    private consultServ: ConsultService,
    public route: ActivatedRoute,
    private dialog: MatDialog,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit() {
    this.consultServ.reactVar.subscribe(data => {
      if (data === 'save') {
        this.showMessage('Consulta Guardada con éxito', 'Guardar');
      } else if (data === 'edit') {
        this.showMessage('Consulta Editada con éxito', 'Editar');
      } else if (data === 'delete') {
        this.showMessage('Consulta Eliminada con éxito', 'Eliminar');
      } else if (data === 'saveFull') {
        this.showMessage('Consulta Guardada con éxito', 'Guardar');
      } else {
        this.showMessage('No se puede agregar el paciente y doctor', 'Error');
      }
      this.list(0 , 10);
    });
    this.list(0, 10);
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  list(page: number, size: number) {
    this.consultServ.listPage(page, size).subscribe(dataConsultServ => {
      console.log(dataConsultServ);
      this.totalElements = dataConsultServ.totalElements;
      this.mPaginator._intl.itemsPerPageLabel = 'Registros por página';
      this.mPaginator._intl.nextPageLabel = 'Página siguiente';
      this.mPaginator._intl.previousPageLabel = 'Página anterior';
      this.mPaginator._intl.firstPageLabel = 'Primera Página';
      this.mPaginator._intl.lastPageLabel = 'Ultima Página';
      this.dataSource = new MatTableDataSource(dataConsultServ.content);
      this.dataSource.sort = this.mSort;
      // this.dataSource.paginator = this.mPaginator;
    });
    this.showProgressBar = false;
  }

  changePage(e: any) {
    console.log(e);
    this.list(e.pageIndex, e.pageSize);
  }

  openDialogEdit(obj?: Consult) {
    const consult = obj != null ? obj : new Consult();
    this.dialog.open(DialogConsultComponent, {
      width: '40%',
      data: consult
    });
  }

  showMessage(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 3000,
    });
  }

}
