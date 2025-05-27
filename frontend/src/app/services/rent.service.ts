import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment';

@Injectable({
  providedIn: 'root',
})
export class rentService {
  private baseUrl = environment.foodUrl;

  constructor(private http: HttpClient) {}

  payRent(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/pay`, data);
  }
}
