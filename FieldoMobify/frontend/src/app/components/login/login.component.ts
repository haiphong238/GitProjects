import { Component, OnInit, HostListener } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AccountService } from 'src/app/services/account.service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  validMessage: string = "";
  height: string = "800px";

  model: any = {};

  constructor(private router: Router, private titleService: Title, private accountService: AccountService, private http: HttpClient) { }

  ngOnInit() {
    this.titleService.setTitle('Fieldomobify | Admin Login dashboard');
    this.loginForm = new FormGroup({
      email: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });

    sessionStorage.setItem('token', '');

    this.height = `${window.innerHeight}px`;
  }

  @HostListener('window:resize', ['$event'])
  resize(event: any) {
    this.height = `${event.target.innerHeight}px`;
  }

  submitLogin() {
    let url = '/server/login';
    this.http.post<Observable<boolean>>(url, {
      userName: this.model.username,
      password: this.model.password
    }).subscribe(isValid => {
      if (isValid) {
        sessionStorage.setItem('token', btoa(this.model.username + ':' + this.model.password));
        this.router.navigate(['/home']);
      } else {
        alert("Authentication failed.")
      }
    });

  }

}
