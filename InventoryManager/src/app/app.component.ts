import { Component,OnInit } from '@angular/core';

import {AppService} from './app.service';

import {PieChartData} from './models/pieChartData';

import {LineChartData} from './models/lineChartData';


import {MatDatepickerInputEvent} from '@angular/material/datepicker';

import { from } from 'rxjs';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  dropdownList = [];
  selectedItems = [];
  dropdownSettings = {};
  categories=[];
  month:number;
  year:number;
  public piechartData:PieChartData;
  public lineChartData:LineChartData;
  constructor(private _appservice:AppService){}

  ngOnInit () {  
    this._appservice.getCategory()
    .subscribe(data=>{
        this.categories=data;
    });
    this.dropdownList = [
      { item_id: 1, item_text: 'Mumbai' },
      { item_id: 2, item_text: 'Bangaluru' },
      { item_id: 3, item_text: 'Pune' },
      { item_id: 4, item_text: 'Navsari' },
      { item_id: 5, item_text: 'New Delhi' }
    ];
    this.selectedItems = this.dropdownList;

    this.dropdownSettings = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'item_text',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 2,
      allowSearchFilter: true
    };
    this._appservice.getPieChartData(this.month,this.year).subscribe(data=>{
        this.piechartData=data;
    });

    this._appservice.getLineChartData(this.month,this.year).subscribe(data=>{
        this.lineChartData=data
    });
  }
  onItemSelect (item:any) {
    console.log(item);
  }
  onSelectAll (items: any) {
    console.log(items);
  }
  title = 'InventoryManager';
  public pieChartLabels:string[] = ["Pending", "InProgress", "OnHold", "Complete", "Cancelled"];
  public pieChartData:number[] = [21, 39, 10, 10, 16];
  public pieChartType:string = 'pie';
  public pieChartOptions:any = {'backgroundColor': [
            "#FF6384",
            "#4BC0C0",
            "#FFCE56",
            "#E7E9ED",
            "#36A2EB"
            ]}
 
  // events on slice click
  public chartClicked(e:any):void {
    console.log(e);
  }
 
 // event on pie chart slice hover
  public chartHovered(e:any):void {
    console.log("a");
  }
  chartOptions = {
    responsive: true
  };

  chartData = [
    { data: [330, 600, 260, 700], label: 'Account A' },
    { data: [120, 455, 100, 340], label: 'Account B' },
    { data: [45, 67, 800, 500], label: 'Account C' }
  ];

  chartLabels = ['January', 'February', 'March', 'April'];

  onChartClick(event) {
    console.log(event);
  }
  addEvent(type: string, event: MatDatepickerInputEvent<Date>) {
    this.month=event.value.getMonth();
    this.year=event.value.getFullYear();
    console.log(this.month)
  }
}
