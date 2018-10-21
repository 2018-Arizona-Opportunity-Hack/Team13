import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient) { }
  readonly endpoint = 'http://18.237.252.206:8080/';
  readonly httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };

  public year:number;
  public month:string;
  private extractData(res: Response) {
    let body = res;
    return body || { };
  }

  getCategory(): Observable<any> {
    return this.http.get(this.endpoint + 'category').pipe(
      map(this.extractData));
  }

  getPieChartData(month:string,year:any):Observable<any>{
    month=month;
    year=year;
    var k;
    k=this.endpoint+'data/'+year+'/'+month;
    return this.http.get(k).pipe(
      map(this.extractData));
  }

  getLineChartData(year:any):Observable<any>{
    year=year;
    var k;
    k=this.endpoint+'data/'+year;
    return this.http.get(k).pipe(
      map(this.extractData)
    );
  }

  postCategories(data:any, month:string,year:string){
    this.http.post(this.endpoint+'data/missingCategory/'+year+'/'+month,data);
  }
}

