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
   const token = localStorage.getItem('token');
  if (token) {
    const payload = token.split('.')[1]; // get the payload
    const decodedPayload = JSON.parse(atob(payload)); // decode Base64
    this.userRole = decodedPayload.role; // get role
    this.name = decodedPayload.username || decodedPayload.sub; 
    console.log('Role from token:', this.userRole);
    console.log('Name from token:', this.name);
    this.adminService.getAllData().subscribe((data: any) => {
    this.pgData = data;
  });
  }
  
  goToDashboard() {
    this.router.navigate(['./myRoom']);
  }
}
