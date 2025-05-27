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
  pgData: any;
  userRole: string | null = '';

  ngOnInit() {
    console.log(localStorage.getItem('username'));
    this.name = localStorage.getItem('username');
    this.userRole = localStorage.getItem('role');
    this.adminService.getAllData().subscribe((data: any) => {
      console.log('Received PG data:', data);
      this.pgData = data;
    });
  }

  goToDashboard() {
    this.router.navigate(['./myRoom']);
  }
}
