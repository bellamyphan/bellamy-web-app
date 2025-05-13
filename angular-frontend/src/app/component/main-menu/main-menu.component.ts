import { Component } from '@angular/core';

@Component({
  selector: 'app-main-menu',
  standalone: false,
  templateUrl: './main-menu.component.html',
  styleUrl: './main-menu.component.css'
})
export class MainMenuComponent {

  userRegistration(): void {
    // Navigate to the user registration page
    window.location.href = '/user-registration';
  }

  sendEmail(): void {
    const subject = 'Hello Bellamy from the Bellamy Web App';
    const body = 'I hope you are doing well. This is a test email from the Bellamy Web App.';
    const mailtoLink = `mailto:bellamyphan@icloud.com
      ?subject=${encodeURIComponent(subject)}
      &body=${encodeURIComponent(body)}`;
      window.location.href = mailtoLink;
  }

}
