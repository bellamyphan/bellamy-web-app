import { Component } from '@angular/core';
import { Transaction } from '../../model/transaction';
import { TransactionService } from '../../service/transaction.service';
import { firstValueFrom, Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-transaction-list',
  standalone: false,
  templateUrl: './transaction-list.component.html',
   styleUrls: ['./transaction-list.component.css']
})
export class TransactionListComponent {

  transactions$!: Observable<Transaction[]>; // Declare an Observable for transactions

  constructor(
    private transactionService: TransactionService, 
    private router: Router) { }

  ngOnInit(): void {
    this.transactions$ = this.transactionService.getTransactions(); // Assign Observable to the component property
  }

  updateTransaction(id: number) {
    this.router.navigate(['transactions', id, 'update']); // Navigate to the update route with the transaction ID
  }

  viewTransactionDetails(id: number) {
    this.router.navigate(['transactions', id, 'details']); // Navigate to the details route with the transaction ID
  }

  async deleteTransaction(id: number): Promise<void> {
    try {
      // Call the deleteTransaction method from the service and wait for the response
      const response = await firstValueFrom(this.transactionService.deleteTransaction(id));
      
      // Check if the response contains 'success' property
      if (response.deleted) {
        console.log('Transaction deleted successfully');
        // Reload the transactions list or update the view after deletion
        this.transactions$ = this.transactionService.getTransactions();
      } else {
        console.error('Failed to delete transaction: Unknown error');
      }
    } catch (err) {
      console.error('Error deleting transaction', err);
    }
  }
}
