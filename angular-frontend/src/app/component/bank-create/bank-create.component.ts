import { Component } from '@angular/core';
import { Bank, BankType } from '../../model/bank';

@Component({
  selector: 'app-bank-create',
  standalone: false,
  templateUrl: './bank-create.component.html',
  styleUrl: './bank-create.component.css'
})
export class BankCreateComponent {
  bank: Bank = {
    id: 0,
    name: '',
    openingDate: new Date(),
    type: {} as BankType
  };

  bankTypes: BankType[] = [
    { id: 1, type: 'Retail' },
    { id: 2, type: 'Commercial' },
    { id: 3, type: 'Investment' }
  ];

  onSubmit(): void {
    // You'd usually send this data to a backend via a service
    console.log('Bank created:', this.bank);
    // Simulate navigation after submission
    this.goBack();
  }

  goBack(): void {
    window.location.href = '/main-menu';
  }
}