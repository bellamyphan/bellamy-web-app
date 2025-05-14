import { Injectable } from '@angular/core';
import { AppConfig } from '../app-config';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Bank, BankType } from '../model/bank';

@Injectable({
  providedIn: 'root'
})
export class BankService {

  private baseUrl = AppConfig.springApiUrl;
  private bankUrl = `${this.baseUrl}/api/banks`;
  private bankTypeUrl = `${this.baseUrl}/api/banks/types`;

  constructor(private httpClient: HttpClient) { }

  getBankTypes(): Observable<BankType[]> {
    return this.httpClient.get<BankType[]>(`${this.bankTypeUrl}`);
  }

  createBank(bank: Bank): Observable<Bank> { // Create a new bank
      return this.httpClient.post<Bank>(this.bankUrl, bank);
    }
}
