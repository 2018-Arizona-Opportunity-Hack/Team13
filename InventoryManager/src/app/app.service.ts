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

  getPieChartData(month:any,year:any):Observable<any>{
    month="Aug";
    year=2018;
    var k;
    k=this.endpoint+'data/'+year+'/'+month;
    return this.http.get(this.endpoint +'data/2018/Aug').pipe(
      map(this.extractData));
  }

  getLineChartData(month:any,year:any):Observable<any>{
    year=2018;
    var k;
    k=this.endpoint+'data/'+year;
    return this.http.get(k).pipe(
      map(this.extractData)
    );
  }
}

