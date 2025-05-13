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