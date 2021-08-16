import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http"
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
  }
  
  onSubmit(form:NgForm){
    console.log(form);
    this.http.post('http://localhost:9080/expense-app/login',
      JSON.stringify({userName:form.value.userName, password:form.value.password}))
      .subscribe({
        next: (data:any) =>{
          if(data.status === 'success'){
            localStorage.setItem("username", form.value.userName);
          }
          this.router.navigate([''])
        }
      })
  }

}
