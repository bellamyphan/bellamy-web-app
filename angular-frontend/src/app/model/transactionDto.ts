export interface TransactionDto {
  id: number;
  date: Date;
  amount: number;
  notes: string;
  typeName: string;
  bankName: string;
  username: string;
}
