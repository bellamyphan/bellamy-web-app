import { Component } from '@angular/core';
import { User } from '../../model/user';

@Component({
  selector: 'app-user-registration',
  standalone: false,
  templateUrl: './user-registration.component.html',
  styleUrl: './user-registration.component.css'
})
export class UserRegistrationComponent {

  user: User = new User();

  registerUser() {
    console.log('User registered:', this.user);
    alert(`User ${this.user.username} registered successfully!`);
  }
}
