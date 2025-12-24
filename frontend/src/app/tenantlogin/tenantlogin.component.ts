import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { TenantService } from '../services/tenant.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tenantlogin',
  standalone: false,
  templateUrl: './tenantlogin.component.html',
  styleUrl: './tenantlogin.component.css',
})
export class TenantloginComponent {
  constructor(
    private http: HttpClient,
    private tenantservice: TenantService,
    private router: Router
  ) {}

  successMessage: String = '';
  messageColor: String = '';

  tenant = {
    email: '',
    pass: '',
  };

  logintenant() {
    this.tenantservice.loginTenant(this.tenant).subscribe({
      next: (response: any) => {
        localStorage.setItem('token', response.token);
        localStorage.setItem('role', response.role);
        localStorage.setItem('tenantEmail', this.tenant.email);
        this.successMessage = 'Tenant login Successfull';
        this.messageColor = 'green';
        setTimeout(() => {
          this.successMessage = 'Redirect to TenantPanel...';
          this.messageColor = '#003366';
        }, 1000);

        setTimeout(() => {
          this.router.navigate(['/adminPanel']);
        }, 3000);
      },
      error: (error) => {
        this.successMessage = 'Login failed. Please try again.';
        this.messageColor = 'red';
      },
    });
  }
  gotoRegister() {
    this.router.navigate(['/Tregister']);
  }
}
