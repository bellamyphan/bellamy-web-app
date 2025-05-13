import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'angular-frontend';

  onLogIn(): void {
    // Navigate to the user login page
    window.location.href = '/user-login';
  }
}
