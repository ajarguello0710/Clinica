import { AutorService } from 'src/app/service/autor.service';
import { Autor } from './../../../model/Autor';
import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-autor-dialog',
  templateUrl: './autor-dialog.component.html',
  styleUrls: ['./autor-dialog.component.css'],
  providers: [DatePipe]
})
export class AutorDialogComponent implements OnInit {

  autor: Autor;
  isSaving = false;

  constructor(
    private dialogRef: MatDialogRef<AutorDialogComponent>,
    @Inject(MAT_DIALOG_DATA) private dataAutor: Autor,
    private autorServ: AutorService,
    private datePipe: DatePipe
  ) { }

  ngOnInit() {
    if (this.dataAutor.id == null) {
      this.isSaving = true;
    } else {
      this.isSaving = false;
    }

    this.autor = new Autor();
    this.autor.id = this.dataAutor.id;
    this.autor.nombre = this.dataAutor.nombre;
    this.autor.email = this.dataAutor.email;
    this.autor.fechanacimiento = this.dataAutor.fechanacimiento;
  }

  operar() {
    if (this.isSaving) {
      this.autorServ.guardar(this.autor).subscribe(() => {
        this.autorServ.varReactive.next('guardar');
        this.closeDialog();
      });
      // }, err => {
      //   console.log(err.error.message);
      // });
    } else {
      this.autorServ.editar(this.autor).subscribe(() => {
        this.autorServ.varReactive.next('Se ha editar correctamente');
        this.closeDialog();
      });
    }
  }

  closeDialog() {
    this.dialogRef.close();
  }

}
