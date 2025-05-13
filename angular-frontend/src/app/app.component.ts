import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './service/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {

  title = 'angular-frontend';
  username: string | null = null;

  constructor(
    private router: Router,
    private authService: AuthenticationService // Assuming you have an AuthenticationService to manage tokens
  ) {}

  ngOnInit(): void {
    // Subscribe to the username$ Observable to update email dynamically
    this.authService.username$.subscribe((username) => {
      this.username = username;
    });
  }

  toggleLoginLogout(): void {
    if (this.username) {
      // If the user is logged in, log them out
      this.authService.logout();
      this.router.navigate(['/user-login']);
    } else {
      // If no user is logged in, navigate to the login page
      this.router.navigate(['/user-login']);
    }
  }
}
