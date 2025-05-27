import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  private baseUrl = environment.roomUrl;
  private PgUrl = environment.adminUrl;

  constructor(private http: HttpClient) {}

  addrooms(roomdata: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/addroom`, roomdata);
  }

  uploadRoomImages(formData: FormData): Observable<any> {
    return this.http.post(`${this.baseUrl}/addRoomWithImages`, formData);
  }

  getRooms(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}`); // Fetches the room data from the backend
  }

  getAllData(): Observable<any> {
    return this.http.get<any>(`${this.PgUrl}/getPgData`); // Fetches the room data from the backend
  }

  removeRoom(roomNumber: string): Observable<any> {
    const headers = new HttpHeaders().set(
      'Authorization',
      'Bearer ' + localStorage.getItem('authToken')
    );
    return this.http.delete(`${this.baseUrl}/${roomNumber}`, { headers });
  }

  getAllRooms(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/onlyrooms`);
  }

  addTenant(tenant: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/addtenant`, tenant);
  }

  getRentDetails(): Observable<any> {
    return this.http.get<any>(`${this.PgUrl}/getRentsAll`);
  }

  getAllTenants(): Observable<any> {
    return this.http.get<any>(`${this.PgUrl}/GetAllTenants`);
  }

  deleteTenantByTno(tno: string): Observable<any> {
    return this.http.delete(`${this.PgUrl}/deleteTenant`, { params: { tno } });
  }

  sendNotification(notification: any): Observable<any> {
    return this.http.post(`${this.PgUrl}/message`, notification);
  }

  getMessage(): Observable<any> {
    return this.http.get<any>(`${this.PgUrl}/getMessages`);
  }

  generateRentReport(year: number, month: number): Observable<Blob> {
    return this.http.get(
      `${this.PgUrl}/generateRentReport?year=${year}&month=${month}`,
      {
        responseType: 'blob', // Important to get the binary PDF data as a Blob
      }
    );
  }
}
