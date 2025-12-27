import { Component } from '@angular/core';
import { AdminService } from '../services/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-panel',
  standalone: false,
  templateUrl: './admin-panel.component.html',
  styleUrl: './admin-panel.component.css',
})
export class AdminPanelComponent {
  constructor(private adminService: AdminService, private router: Router) {}
  name: string | null = '';
  userRole: string | null = '';
  pgData: any;

  
   ngOnInit(): void {
    const token = localStorage.getItem('token');

    if (token) {
      const payload = JSON.parse(atob(token.split('.')[1]));
      this.name = payload.name;
      this.userRole = payload.role;
    }

    this.adminService.getAllData().subscribe({
      next: (data: any) => {
        console.log('Received PG data:', data);
        this.pgData = data;
      },
      error: (err) => {
        console.error('Error fetching PG data:', err);
      }
    });
  }
  goToDashboard() {
    this.router.navigate(['./myRoom']);
  }
}
