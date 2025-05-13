// transaction.model.ts

import { Bank } from './bank';

export class Transaction {
  id!: number;
  date!: Date;
  amount!: number;
  type?: TransactionType;  // Optional, Reference to TransactionType object
  notes?: string;           // Optional notes field
  bank?: Bank;              // Optional Bank object
}

export class TransactionType {
  id!: number;
  type!: string;
}
