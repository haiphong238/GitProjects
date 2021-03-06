import { Component, OnInit, HostListener } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AccountService } from 'src/app/services/account.service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { IAccount } from './account';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  validMessage: string = "";
  height: string = "800px";
  accounts: IAccount[];

  model: any = {};

  constructor(private router: Router, private titleService: Title, private accountService: AccountService, private http: HttpClient) { }

  ngOnInit() {
    this.titleService.setTitle('Fieldomobify | Admin Login dashboard');
    this.loginForm = new FormGroup({
      email: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      selectedAccount: new FormControl('',Validators.required)
    });

    sessionStorage.setItem('token', '');

    this.height = `${window.innerHeight}px`;
  }

  @HostListener('window:resize', ['$event'])
  resize(event: any) {
    this.height = `${event.target.innerHeight}px`;
  }



  submitLogin() {
    if(this.loginForm.controls['selectedAccount'].value == '')
    {
      this.accountService.getAccountsByEmail(this.loginForm.controls['email'].value).subscribe(
        data => {this.accounts = data as IAccount[]},
        err => console.error(err),
        () => console.log('accounts loaded')
      );
    }else{
      console.log('Login with account: ' + this.loginForm.controls['selectedAccount'].value);
      
    }


    /*let url = '/server/login';
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
    });*/

  }

}
