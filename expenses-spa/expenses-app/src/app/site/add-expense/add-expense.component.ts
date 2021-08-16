import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-expense',
  templateUrl: './add-expense.component.html',
  styleUrls: ['./add-expense.component.css']
})
export class AddExpenseComponent implements OnInit {

  details: string = '';
  expenseAmount!: number;
  expenseType: string = '';



  constructor(public fb:FormBuilder, private http:HttpClient,private router:Router) { }

  ngOnInit(): void {
  }

  onTitleChange(e:any){
    this.expenseAmount = e.target.value;
  }
  onTitleChange1(e:any){
    this.details = e.target.value;
  }
  onClick(e:any){
    this.expenseType=e.target.value;
  }

  onSubmit(){
    this.http.post('http://localhost:9080/expense-app/expenses', JSON.stringify({
      details:this.details, expenseAmount:this.expenseAmount, expenseType:this.expenseType
    })).subscribe({
      next: (data:any)=>{

        console.log(data);
        this.router.navigate(['expenses']);
      }
      
    })
    }  

}
