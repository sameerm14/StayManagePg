import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TenantService } from '../services/tenant.service';

@Component({
  selector: 'app-tenant',
  standalone: false,
  templateUrl: './tenant.component.html',
  styleUrl: './tenant.component.css',
})
export class TenantComponent {
  tenant = {
    name: '',
    phone: '',
    tno: '',
    roomSharing: '',
    room: {
      roomNumber: '',
    },
    trent: '',
  };
  message: string = '';
  messageColor: string = '';

  constructor(private http: HttpClient, private tenantService: TenantService) {}
  onSubmit() {
    this.tenantService.addTenant(this.tenant).subscribe({
      next: (response) => {
        this.message = 'Tenant added Successfully ';
        this.messageColor = 'green';
        setTimeout(() => {
          window.location.reload();
        }, 3000);
      },
      error: (Error) => {
        this.message = 'Tenant allready exists';
        this.messageColor = 'red';
      },
    });
  }
}
