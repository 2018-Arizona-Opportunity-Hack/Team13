import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { UploadService } from '../upload.service';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent{
  years: string[] = [
    '2018', '2017', '2016', '2015', '2014', '2013', '2012', '2011',
    '2010', '2009', '2008', '2007', '2006', '2005', '2004', '2003', '2002',
    '2001'
  ];

  months: string[] = [
    'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug',
    'Sep', 'Oct', 'Nov', 'Dec'
  ];
  
  @ViewChild('file') file;
  public files: Set<File> = new Set();
  progress;
canBeClosed = true; 
primaryButtonText = 'Upload';
showCancelButton = true; 
uploading = false;
uploadSuccessful = false;
month: string;
year: string;

constructor(public dialogRef: MatDialogRef<DialogComponent>, public uploadService: UploadService) {}

  addFiles() {
    this.file.nativeElement.click();
  }

  onFilesAdded() {
    const files: { [key: string]: File } = this.file.nativeElement.files;
    for (let key in files) {
      if (!isNaN(parseInt(key))) {
        this.files.add(files[key]);
      }
    }
  }

  closeDialog() {
    // if everything was uploaded already, just close the dialog
    if (this.uploadSuccessful) {
      return this.dialogRef.close();
    }
  
    // set the component state to "uploading"
    this.uploading = true;
  
    // start the upload and save the progress map
    this.progress = this.uploadService.upload(this.files,this.year,this.month);
  
    // convert the progress map into an array
    let allProgressObservables = [];
    for (let key in this.progress) {
      allProgressObservables.push(this.progress[key].progress);
    }
  
    // Adjust the state variables
  
    // The OK-button should have the text "Finish" now
    this.primaryButtonText = 'Finish';
  
    // The dialog should not be closed while uploading
    this.canBeClosed = false;
    this.dialogRef.disableClose = true;
  
    // Hide the cancel-button
    this.showCancelButton = false;
  
    // When all progress-observables are completed...
    forkJoin(allProgressObservables).subscribe(end => {
      // ... the dialog can be closed again...
      this.canBeClosed = true;
      this.dialogRef.disableClose = false;
  
      // ... the upload was successful...
      this.uploadSuccessful = true;
  
      // ... and the component is no longer uploading
      this.uploading = false;
    });
  }
}