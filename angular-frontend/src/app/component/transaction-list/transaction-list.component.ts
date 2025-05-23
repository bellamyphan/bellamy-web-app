import { Component } from '@angular/core';
import { TransactionService } from '../../service/transaction.service';
import { firstValueFrom, Observable } from 'rxjs';
import { Router } from '@angular/router';
import { TransactionDto } from '../../model/transactionDto';

@Component({
  selector: 'app-transaction-list',
  standalone: false,
  templateUrl: './transaction-list.component.html',
   styleUrls: ['./transaction-list.component.css']
})
export class TransactionListComponent {

  transactionDtos$!: Observable<TransactionDto[]>; // Declare an Observable for transactions

  constructor(
    private transactionService: TransactionService, 
    private router: Router) { }

  ngOnInit(): void {
    this.transactionDtos$ = this.transactionService.getTransactions(); // Assign Observable to the component property
  }

  loadSampleTransaction() {
    this.transactionService.loadSampleTransaction().subscribe({
      next: (data) => {
        console.log('Sample transactions loaded:', data);
        this.transactionDtos$ = this.transactionService.getTransactions();
      },
      error: (err) => {
        console.error('Failed to load sample transactions', err);
      }
    });
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
        this.transactionDtos$ = this.transactionService.getTransactions();
      } else {
        console.error('Failed to delete transaction: Unknown error');
      }
    } catch (err) {
      console.error('Error deleting transaction', err);
    }
  }
}
