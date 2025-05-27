import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environment/environment';

@Injectable({
  providedIn: 'root',
})
export class foodService {
  private baseUrl = environment.foodUrl;

  constructor(private http: HttpClient) {}

  addOrUpdateFood(formData: FormData) {
    return this.http.post(`${this.baseUrl}/addfoodwithimage`, formData);
  }

  getAllFoods(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/allmeals`);
  }
}
