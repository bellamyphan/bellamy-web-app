import { Component, OnInit } from '@angular/core';
import { Bank, BankType } from '../../model/bank';
import { BankService } from '../../service/bank.service';

@Component({
  selector: 'app-bank-create',
  standalone: false,
  templateUrl: './bank-create.component.html',
  styleUrl: './bank-create.component.css'
})
export class BankCreateComponent implements OnInit {

  bank: Bank = {
    id: 0,
    name: '',
    openingDate: new Date(),
    type: {} as BankType
  };

  bankTypes: BankType[] = []; // Array to store bank types

  constructor(
    private bankService: BankService
  ) {}

  ngOnInit() {
    // Load transaction types
    this.loadBankTypes();
  }

  onSubmit(): void {
    // You'd usually send this data to a backend via a service
    this.bankService.createBank(this.bank).subscribe({
      next: (createdBank) => {
        console.log('Bank created:', createdBank);
        alert('Bank created successfully!');  // Success alert
      },
      error: (err) => {
        console.error('Error creating bank:', err);
        alert('There was an error creating the bank. Please try again.');  // Error alert
      }
    });
    // Simulate navigation after submission
    this.goBack();
  }

  goBack(): void {
    window.location.href = '/main-menu';
  }

  // Load transaction types from the service
  loadBankTypes() {
    this.bankService.getBankTypes().subscribe(
      (types: BankType[]) => {
        console.log('Fetched bank types:', types); // Print fetched types
        this.bankTypes = types;
        console.log('Assigned transaction types:', this.bankTypes); // Print assigned transaction types
      },
      (error) => {
        console.error('Error fetching transaction types:', error);
      }
    );
  }
}