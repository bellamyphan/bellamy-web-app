// transaction.model.ts

export class Transaction {
  id!: number;
  date!: Date;
  amount!: number;
  type?: TransactionType;  // Optional, Reference to TransactionType object
  notes?: string;           // Optional notes field
  bank?: Bank;              // Optional Bank object
}

export class Bank {
  id!: number;
  name!: string;
  openingDate!: Date;
  closingDate?: Date;  // Optional closingDate
  type!: BankType;     // Reference to BankType object
}

export class BankType {
  id!: number;
  type!: string;
}

export class TransactionType {
  id!: number;
  type!: string;
}
