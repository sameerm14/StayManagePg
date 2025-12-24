import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  loginForm: FormGroup;

  message: string = '';
  messageColor: string = '';

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthService
  ) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      this.authService.login(this.loginForm.value).subscribe({
        next: (response: any) => {
          localStorage.setItem('token', response.token);
          localStorage.setItem('role', response.role);
          console.log("LOGIN RESPONSE 👉", response);
          console.log(response.role);
          const usernames = this.loginForm.get('username')?.value;
          localStorage.setItem('username', usernames);
          this.message = 'Login successfull!';
          this.messageColor = 'green';
          setTimeout(() => {
            this.message = 'Redirecting to admin panel...';
            this.messageColor = '#003366';
          }, 1000);

          setTimeout(() => {
            this.router.navigate(['/adminPanel']);
          }, 3000);
        },
        error: (error) => {
          this.message = 'Login failed. Please try again.';
          this.messageColor = 'red';
        },
      });
    }
  }

  goToSignup() {
    this.router.navigate(['/signup']);
  }
}
