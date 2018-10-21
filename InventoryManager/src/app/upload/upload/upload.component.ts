import { Component } from '@angular/core';
import { MatDialog } from '@angular/material';
import { DialogComponent } from '../dialog/dialog.component';
import { UploadService } from '../upload.service';
import { NgModel } from '@angular/forms';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent {
  constructor(public dialog: MatDialog, public uploadService: UploadService) {}
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