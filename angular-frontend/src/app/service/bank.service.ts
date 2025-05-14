import { Injectable } from '@angular/core';
import { AppConfig } from '../app-config';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BankType } from '../model/bank';

@Injectable({
  providedIn: 'root'
})
export class BankService {

  private baseUrl = AppConfig.springApiUrl; // Base URL for the API
  private bankTypeUrl = `${this.baseUrl}/api/banks/types`; // URL for bank types endpoint

  constructor(private http: HttpClient) { }

  getBankTypes(): Observable<BankType[]> {
    return this.http.get<BankType[]>(`${this.bankTypeUrl}`);
  }
}
