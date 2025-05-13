import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../service/authentication.service';

@Component({
  selector: 'app-user-login',
  standalone: false,
  templateUrl: './user-login.component.html',
  styleUrl: './user-login.component.css'
})
export class UserLoginComponent {

  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(
    private authService: AuthenticationService,
    private router: Router) { }


  onLogin(): void {
    // Call login method in AuthenticationService
    this.authService.login(this.username, this.password).subscribe({
      next: (response) => {
        // Store token upon successful login
        this.authService.storeToken(response.token);
        console.log('Login successful, token stored:', response.token);
        // Redirect to the homepage or dashboard
        this.router.navigate(['/main-menu']).then(success => {
          console.log('Navigation success:', success);
        }).catch(err => {
          console.error('Navigation error:', err);
        });
      },
      error: (err) => {
        this.errorMessage = 'Invalid username or password';
        console.error('Login failed:', err);
        this.password = ''; // Clear password field on error
      }
    });
  }
}
