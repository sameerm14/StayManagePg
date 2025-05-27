import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private adminbaseUrl = environment.adminUrl;
  constructor(private http: HttpClient) {}

  register(userData: any): Observable<any> {
    return this.http.post(`${this.adminbaseUrl}/register`, userData);
  }

  login(userData: any): Observable<any> {
    return this.http.post<any>(`${this.adminbaseUrl}/login`, userData);
  }
}
