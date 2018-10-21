import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient) { }
  readonly endpoint = 'http://localhost:3000/api/v1/';
  readonly httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };

  private extractData(res: Response) {
    let body = res;
    return body || { };
  }

  getCategory(): Observable<any> {
    return this.http.get(this.endpoint + 'category').pipe(
      map(this.extractData));
  }

  getPieChartData(month:any,year:any):Observable<any>{

    let params=new HttpParams()
    .set('month',month)
    .set('year',year);
    
    return this.http.get(this.endpoint +'aggredatedata/',{params}).pipe(
      map(this.extractData));
  }

  getLineChartData(month:any,year:any):Observable<any>{
    let params=new HttpParams()
    .set('month',month)
    .set('year',year);

    return this.http.get(this.endpoint + 'data/',{params}).pipe(
      map(this.extractData)
    );
  }
}

