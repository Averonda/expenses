import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  username:string = '';

  constructor(private http:HttpClient, private router:Router) {
    if (localStorage.getItem('username')!=null) {
      this.username = localStorage.getItem('username')!;
    }else{
      this.router.navigate(['login']);
    }
   }

  

  ngOnInit(): void {
  }

}
