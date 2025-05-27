import { Injectable } from '@angular/core';
import { environment } from '../../environment/environment';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root',
})
export class contactService {
  constructor(private http: HttpClient) {}
  private baseurl = environment.contacturl;

  contactData(formdata: any): Observable<any> {
    return this.http.post(`${this.baseurl}/contact`, formdata);
  }
}
