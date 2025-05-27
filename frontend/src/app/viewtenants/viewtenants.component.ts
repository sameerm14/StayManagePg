import { Component } from '@angular/core';
import { AdminService } from '../services/admin.service';

@Component({
  selector: 'app-viewtenants',
  standalone: false,
  templateUrl: './viewtenants.component.html',
  styleUrl: './viewtenants.component.css',
})
export class ViewtenantsComponent {
  tenants: any[] = [];

  constructor(private adminservice: AdminService) {}

  ngOnInit(): void {
    this.fetchTenants();
  }

  fetchTenants() {
    this.adminservice.getAllTenants().subscribe({
      next: (response) => {
        console.log(response);
        this.tenants = response;
      },
    });
  }

  removeTenant(tno: string) {
    if (confirm('Are you sure you want to remove this tenant?')) {
      this.adminservice.deleteTenantByTno(tno).subscribe({
        next: () => {
          alert('Tenant removed successfully.');
          this.fetchTenants();
        },
        error: (err) => {
          console.error(err);
          alert('Error removing tenant.');
        },
      });
    }
  }
}
