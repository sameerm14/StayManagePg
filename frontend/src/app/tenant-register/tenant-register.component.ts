import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { TenantService } from '../services/tenant.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tenant-register',
  standalone: false,
  templateUrl: './tenant-register.component.html',
  styleUrl: './tenant-register.component.css',
})
export class TenantRegisterComponent {
  successMessage: String = '';
  messageColor: String = '';

  tenant = {
    phone: '',
    email: '',
    pass: '',
  };
  constructor(
    private http: HttpClient,
    private tenantservice: TenantService,
    private router: Router
  ) {}

  registerTenant() {
    this.tenantservice.registerTenant(this.tenant).subscribe({
      next: () => {
        this.successMessage = 'Tenant Registration Successfull ';
        this.messageColor = 'green';
        setTimeout(() => {
          this.successMessage = 'Redirect to Tenant Login... ';
          this.messageColor = '#003366';
        }, 1000);
        setTimeout(() => {
          this.router.navigate(['/TLogin']);
        }, 3000);
      },
      error: (error) => {
        this.successMessage = 'Allready Registered or Admin is Not Added';
        this.messageColor = 'red';
      },
    });
  }

  logintenant() {
    this.router.navigate(['/TLogin']);
  }
  gotoSignup() {
    this.router.navigate(['/signup']);
  }
}
