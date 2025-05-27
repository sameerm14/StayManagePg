import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { LogoutconfirmationComponent } from '../logoutconfirmation/logoutconfirmation.component';

@Component({
  selector: 'app-navbar',
  standalone: false,
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
})
export class NavbarComponent implements OnInit {
  menuOpen = false;
  isAdmin = false;
  isTenant = false;

  ngOnInit(): void {
    const role = localStorage.getItem('role');
    this.isAdmin = role === 'ADMIN';
    this.isTenant = role === 'TENANT';
  }

  toggleNavbar() {
    this.menuOpen = !this.menuOpen;
  }
  constructor(private router: Router, private dialog: MatDialog) {}

  // In your component
  confirmLogout(event: Event) {
    event.preventDefault();

    const dialogRef = this.dialog.open(LogoutconfirmationComponent, {
      width: '350px',
      disableClose: true,
      data: { message: 'Are you sure you want to logout?' },
    });

    dialogRef.afterClosed().subscribe((confirmed: boolean) => {
      if (confirmed) {
        // Clear user data
        localStorage.removeItem('authToken');

        this.router.navigate(['/login']);
        // Optional: show confirmation
      }
    });
  }
}
