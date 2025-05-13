import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../model/user';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-user-registration',
  standalone: false,
  templateUrl: './user-registration.component.html',
  styleUrl: './user-registration.component.css'
})
export class UserRegistrationComponent {

  user: User = new User();

  constructor(
    private userService: UserService,
    private router: Router
  ) {}

  onRegister(): void {
    // Handle form submission logic here
    console.log('User Registration Details:');
    console.log('First Name:', this.user.firstName);
    console.log('Last Name:', this.user.lastName);
    console.log('Username:', this.user.username);
    console.log('Password:', this.user.password);

    this.userService.registerUser(this.user).subscribe({
      next: (response) => {
        console.log('User registered successfully:', response);
        alert(`User registered successfully!
          \nFirst Name: ${this.user.firstName}
          \nLast Name: ${this.user.lastName}
          \nUsername: ${this.user.username}`);
        // Optionally, redirect to a different page or show a success message
        this.router.navigate(['/user-login']).then(success => {
          console.log('Navigation success:', success);
        }
        ).catch(err => {
          console.error('Navigation error:', err);
        }
        );
      },
      error: (error) => {
        console.error('Error registering user:', error);
        // Optionally, show an error message to the user
        alert('Error registering user. Please try again.');
      }
    });
  }
}
