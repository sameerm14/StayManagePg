import { Component } from '@angular/core';
import { AdminService } from '../services/admin.service';
import { Router } from '@angular/router';
import jwt_decode from 'jwt-decode';

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
      const decoded: any = jwt_decode(token); // decode JWT
      this.userRole = decoded.role;           // get role from token
      this.name = decoded.username || decoded.sub; // get username (depends on your JWT claim)
    }
    console.log('Role from token:', this.userRole);
    console.log('Name from token:', this.name);
  }
  goToDashboard() {
    this.router.navigate(['./myRoom']);
  }
}
