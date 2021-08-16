import { DataSource } from '@angular/cdk/collections';
import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { Router } from '@angular/router';
import { Timestamp } from 'rxjs';
import { ExpenseItem } from '../expense-item';

@Component({
  selector: 'app-expense-list',
  templateUrl: './expense-list.component.html',
  styleUrls: ['./expense-list.component.css']
})
export class ExpenseListComponent implements OnInit {

  displayedColumns: string[] = ['expenseID', 'approved', 'expenseAmount', 'dateSubmitted'];
  expenseList : ExpenseItem[] = [];
  expenseAmount!: number;
  
    
    dataSource: ExpenseItem[] = [];

    clickedRows = new Set<ExpenseItem>();

  

  constructor( private http:HttpClient, private router:Router ) {
    this.getExpenses;
   }

  ngOnInit(): void {
    this.getExpenses();
  }

  getExpenses(){
    this.http.get('http://localhost:9080/expense-app/expenses').subscribe({
      next: (data:any) =>{
         this.dataSource = data;
         console.log(data);
          }
        }
      )
  }

  submitAnExpense(){
    
  }

  // @ViewChild(MatSort) sort: MatSort;

  // ngAfterViewInit() {
  //   this.dataSource. = this.sort;
  // }

  
  /**
   * @title Binding event handlers and properties to the table rows.
   */
  // @Component({
  //   selector: 'table-row-binding-example',
  //   styleUrls: ['table-row-binding-example.css'],
  //   templateUrl: 'table-row-binding-example.html',
  // })
  // export class TableRowBindingExample {
  //   displayedColumns: string[] = ['position', 'name', 'weight', 'symbol'];
  //   dataSource = ELEMENT_DATA;
  //   clickedRows = new Set<PeriodicElement>();
  // }
  // next: (data:any) =>{
  //   for (let index = 0; index < data.length; index++) {
  //    this.expenseList[index] = data[index];
  //    console.log(data[index])
  // for (let index = 0; index < data.length; index++) {
  //   this.expenseList[index].expenseID = data[index].expenseID;
  //   this.expenseList[index].submittedBy = data[index].submittedBy;
  //   this.expenseList[index].approvedBy = data[index].approvedBy;
  //   this.expenseList[index].expenseAmount = data[index].expenseAmount;
  //   this.expenseList[index].reimbersementType = data[index].reimbersementType;
  //   this.expenseList[index].isApproved = data[index].isApproved;
  //   this.expenseList[index].details = data[index].details;
    
  //   console.log(data[index])

}
