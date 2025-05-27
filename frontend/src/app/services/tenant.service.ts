import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment';

@Injectable({
  providedIn: 'root',
})
export class TenantService {
  private baseUrl = environment.tenantUrl;
  private baseTUrl = environment.tenantFUrl;

  constructor(private http: HttpClient) {}

  addTenant(tenant: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/addtenant`, tenant);
  }

  registerTenant(tenant: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/tenantregister`, tenant);
  }

  loginTenant(tenant: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/tenantlogin`, tenant);
  }

  Payrent(payments: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/Paymentdone`, payments);
  }

  getMyRoom(email: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/myroom?email=${email}`, {});
  }
  addTenantImage(formData: FormData): Observable<any> {
    return this.http.post(`${this.baseTUrl}/addImages`, formData);
  }

  getData(email: string): Observable<any> {
    return this.http.get<any>(`${this.baseTUrl}/getMyroom`, {
      params: { email },
    });
  }
}
