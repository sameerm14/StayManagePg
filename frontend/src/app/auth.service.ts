import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor() {}

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token'); // check if JWT exists
  }

  logout() {
    localStorage.removeItem('token'); // remove token on logout
  }
}
