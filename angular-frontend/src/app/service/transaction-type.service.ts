import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { AppConfig } from '../app-config';
import { TransactionType } from '../model/transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionTypeService {

  private baseUrl = AppConfig.springApiUrl; // Base URL for the API
  private transactionTypeUrl = `${this.baseUrl}/api/transactions/types`; // URL for transaction types endpoint

  constructor(private http: HttpClient) {}

  getTransactionTypes(): Observable<TransactionType[]> {
    return this.http.get<TransactionType[]>(`${this.transactionTypeUrl}`);
  }
}
