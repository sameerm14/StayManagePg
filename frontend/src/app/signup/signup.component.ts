import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-signup',
  standalone: false,
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css',
})
export class SignupComponent {
  signupForm: FormGroup;
  message: string = '';
  messageColor: string = '';

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthService
  ) {
    this.signupForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.signupForm.valid) {
      this.authService.register(this.signupForm.value).subscribe({
        next: (response) => {
          this.message = 'Registration successful!';
          this.messageColor = 'green';
          setTimeout(() => {
            this.message = 'Redirect to login...';
            this.messageColor = '#003366';
          }, 1000);
          setTimeout(() => {
            this.router.navigate(['/login']);
          }, 3000);
        },
        error: (error) => {
          this.message = 'Registration failed. Please try again.';
          this.messageColor = 'red';
        },
      });
    }
  }

  goToLogin() {
    this.router.navigate(['/login']);
  }

  goToTenant() {
    this.router.navigate(['/Tregister']);
  }
}
