import { Component } from '@angular/core';
import { MatDialog } from '@angular/material';
import { DialogComponent } from '../dialog/dialog.component';
import { UploadService } from '../upload.service';
import { NgModel } from '@angular/forms';
import {MatTableDataSource} from '@angular/material';
import { AppService } from 'src/app/app.service';


export interface Element {
  donorId: string,
  organization: string;
  category: string;
}

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})

export class UploadComponent {
  constructor(public dialog: MatDialog, public uploadService: UploadService, public appService: AppService) {
    dialog.afterAllClosed.subscribe(()=>{
      this.checkUnknownCategory();
    });
  }
  displayedColumns = ['donorId', 'organization', 'category'];
  dataSource:MatTableDataSource<Element>;
  ngOninit(){
    this.appService.getCategory().subscribe(data=>{
      data["categoryList"].forEach(element => {
        this.categories.push(element);
         });
      });
  }

  public openUploadDialog() {
    let dialogRef = this.dialog.open(DialogComponent, { width: '50%', height: '50%' });
  }
 year=2018;
 public downloadCSV(){
   this.uploadService.export(this.year);
 }
 categories;
 unknownCategoriesArray:Element[];

 shouldShowCategoryAddView = false;
 data: JSON;
 monthReceived:string;
 yearReceived:string;

 checkUnknownCategory(){
  this.data = JSON.parse(localStorage.getItem('unknownCategory'))
  this.unknownCategoriesArray = this.data['mapping'];
  this.monthReceived = this.data['month'];
  this.yearReceived = this.data['year'];
  if (this.unknownCategoriesArray != null){
    this.shouldShowCategoryAddView = true;
    console.log(this.unknownCategoriesArray)
    this.dataSource = new MatTableDataSource<Element>(this.unknownCategoriesArray);
    console.log(this.dataSource)
  }
}

onCategorySubmit(){
  this.appService.postCategories(this.unknownCategoriesArray,this.monthReceived,this.yearReceived);
}
}

// const ELEMENT_DATA:Element[]=[
//   {donorId:"1",organization:"Company1",category:"grocery"},
//   {donorId:"2",organization:"Company2",category:"grocery"}
// ];
