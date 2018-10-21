import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { MatDatepickerInputEvent } from '@angular/material';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  dropdownList = [];
  selectedItems = [];
  dropdownSettings = {};
  categories=["Church","Grocerry"];
  month:number;
  year:number;
  chartData=[];

constructor(private _appservice: AppService){}

  ngOnInit() {

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
  // this.chartData = [
  //   { data: [330, 600, 260, 700], label: 'Account A' },
  //   { data: [120, 455, 100, 340], label: 'Account B' },
  //   { data: [45, 67, 800, 500], label: 'Account C' }
  // ];
  this._appservice.getPieChartData(this.month,this.year).subscribe(data=>{
    this.pieChartData=[]; 
    data["aggregate"].forEach(element => {
          this.pieChartData.push(element.totalPounds);
          this.pieChartLabels.push(element.category);
      });
    this.pieChartOptions={'backgroundColor': [
      "#FF6384",
      "#4BC0C0",
      "#FFCE56",
      "#E7E9ED",
      "#36A2EB"
      ]};
  });

  this._appservice.getLineChartData(this.month,this.year).subscribe(res=>{
    var chartData=[];
    this.chartData=[];
    var i;
    for(i=0;i<this.categories.length;i++)
    {
      var temp={data: [0,0,0,0,0,0,0,0,0,0,0,0], label: this.categories[i]};
      chartData.push(temp);
    }
      res["resultList"].forEach(element => {
        var temp;
        if(element["month"]=="Aug") temp=7;
        if(element["month"]=="Sep") temp=8;
       element["aggregate"].forEach(e1 => {
         chartData.forEach(e2=>{
            if(e2["label"]==e1["category"])
              e2["data"][temp]=e1["totalPounds"]
         });
       });
      });
      this.chartData=chartData;
  });
}
onItemSelect (item:any) {
  console.log(item);
}
onSelectAll (items: any) {
  console.log(items);
}

public pieChartLabels:string[] = [];
public pieChartData:number[] = [];
public pieChartType:string = 'pie';
public pieChartOptions:any = {}

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


chartLabels = ['January', 'February', 'March', 'April','May','June','July','August','September','October','November','December'];

onChartClick(event) {
  console.log(event);
}
addEvent(type: string, event: MatDatepickerInputEvent<Date>) {
  this.month=event.value.getMonth();
  this.year=event.value.getFullYear();
  console.log(this.month)
}
}

