import { Component } from '@angular/core';
import { AppConfig } from '../../app-config';

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

  viewTransactions(): void {
    // Navigate to the user registration page
    window.location.href = '/money-transactions';
  }

  addBank(): void {
    // Navigate to the bank create page
    window.location.href = 'money-banks/create';
  }

  tickTacToe() {
    // Navigate to the tic tac toe game
    window.location.href = '/tic-tac-toe';
  }

  downloadResume() {
    const baseUrl = AppConfig.springApiUrl;
    const link = document.createElement('a');
    link.href = baseUrl + "/api/resume";
    link.download = 'resume.pdf'; // optional, helps hint filename
    link.target = '_blank';
    link.click();
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
