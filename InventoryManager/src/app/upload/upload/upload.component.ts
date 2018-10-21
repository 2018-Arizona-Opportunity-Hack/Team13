import { Component } from '@angular/core';
import { MatDialog } from '@angular/material';
import { DialogComponent } from '../dialog/dialog.component';
import { UploadService } from '../upload.service';
import { NgModel } from '@angular/forms';
import {MatTableDataSource} from '@angular/material';


export interface Element {
  name: string;
  position: number;
}

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})

export class UploadComponent {
  constructor(public dialog: MatDialog, public uploadService: UploadService) {}
  displayedColumns = ['position', 'name'];
  dataSource = new MatTableDataSource<Element>(ELEMENT_DATA);
  ngOninit(){
    
  }
  public openUploadDialog() {
    let dialogRef = this.dialog.open(DialogComponent, { width: '50%', height: '50%' });
  }
 year=2018;
 public downloadCSV(){
   this.uploadService.export(this.year);
 }
}

const ELEMENT_DATA:Element[]=[
  {position:1,name:"Company1"},
  {position:2,name:"Company2"}
];