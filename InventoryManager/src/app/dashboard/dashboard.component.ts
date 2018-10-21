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
  categories=[];
  month:number;
  year:number;
  chartData=[];
  pieChartColors:any;
  pieChartColors2:any;

constructor(private _appservice: AppService){}

  ngOnInit() {

    this.pieChartColors = [ {backgroundColor:["#ff9900","red","aqua","#00CCFF","#CCFF00","#00FF66","","",""]}];
    this.pieChartColors2=[ {backgroundColor:["#ff9900","red","aqua","#00CCFF","#CCFF00","#00FF66","","",""]}];
  this._appservice.getCategory().subscribe(data=>{
  data["categoryList"].forEach(element => {
    this.categories.push(element);
     });
  });
  this._appservice.getPieChartData(this.month,this.year).subscribe(data=>{
    this.pieChartData=[];
    this.pieChartDollarData=[]; 
    data["aggregate"].forEach(element => {
          this.pieChartData.push(element.totalPounds);
          this.pieChartLabels.push(element.category);
          this.pieChartDollarData.push(2); // to be changed to element.dollarValue
      });
    
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
        if(element["month"]=="Jan") temp=0;
        if(element["month"]=="Feb") temp=1;
        if(element["month"]=="Mar") temp=2;
        if(element["month"]=="Apr") temp=3;
        if(element["month"]=="May") temp=4;
        if(element["month"]=="Jun") temp=5;
        if(element["month"]=="Jul") temp=6;
        if(element["month"]=="Aug") temp=7;
        if(element["month"]=="Sep") temp=8;
        if(element["month"]=="Oct") temp=9;
        if(element["month"]=="Nov") temp=10;
        if(element["month"]=="Dec") temp=11;

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
public pieChartDollarData:number[]=[];
public pieChartType:string = 'pie';


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

