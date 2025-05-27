import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-logoutconfirmation',
  standalone: false,
  templateUrl: './logoutconfirmation.component.html',
  styleUrl: './logoutconfirmation.component.css',
  template: `
    <h2 mat-dialog-title>Logout</h2>
    <mat-dialog-content> Are you sure you want to logout? </mat-dialog-content>
    <mat-dialog-actions align="end">
      <!-- Explicitly return false for cancel -->
      <button mat-button [mat-dialog-close]="false">Cancel</button>
      <!-- Explicitly return true for logout -->
      <button mat-button color="warn" [mat-dialog-close]="true">Logout</button>
    </mat-dialog-actions>
  `,
})
export class LogoutconfirmationComponent {
  constructor(public dialogRef: MatDialogRef<LogoutconfirmationComponent>) {}
}
