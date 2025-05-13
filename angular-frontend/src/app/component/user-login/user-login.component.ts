import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-login',
  standalone: false,
  templateUrl: './user-login.component.html',
  styleUrl: './user-login.component.css'
})
export class UserLoginComponent {

  username: string = '';
  password: string = '';

  constructor(private router: Router) {}

  onSubmit() {
    // Normally you would send a request to your server to authenticate the user
    // For now, let's just log the credentials and navigate on success
    if (this.username === 'testuser' && this.password === 'testpassword') {
      console.log('Logged in successfully');
      // Navigate to the main menu or home page after login
      this.router.navigate(['/main-menu']);
    } else {
      alert('Invalid credentials');
    }
  }

}
