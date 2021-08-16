import { Timestamp } from "rxjs";

export interface ExpenseItem {
    expenseID: number;
    submittedBy:number;
    approvedBy:number;
    approved:boolean;
    expenseAmount:number;
    reimbersementType:string;
    details:string;
    dateSubmitted:string;
}
