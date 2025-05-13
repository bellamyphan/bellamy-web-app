import { Component } from '@angular/core';
import { Transaction } from '../../model/transaction';
import { TransactionService } from '../../service/transaction.service';
import { Router } from '@angular/router';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-transaction-create',
  standalone: false,
  templateUrl: './transaction-create.component.html',
  styleUrl: './transaction-create.component.css'
})
export class TransactionCreateComponent {

  transaction: Transaction = new Transaction();

  constructor(private transactionService: TransactionService, private router: Router) { }

  // Method to save the transaction
  async saveTransaction() {
    try {
      const data = await firstValueFrom(this.transactionService.createTransaction(this.transaction));
      console.log('Transaction created:', data);
      this.goToTransactionList();
    } catch (error) {
      console.error('Error creating transaction:', error);
    }
  }

  // Navigate to transaction list
  goToTransactionList() {
    this.router.navigate(['/transactions']);
  }

  // Method called when the form is submitted
  onSubmit() {
    console.log('Transaction submitted:', this.transaction);
    this.saveTransaction();
  }
}
