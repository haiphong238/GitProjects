import { Component, OnInit, HostListener } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { AccountService } from '../../services/account.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupForm: FormGroup;
  validMessage: string = "";
  height:string = "800px";

  constructor(private router: Router, private titleService: Title, private accountService: AccountService) { }

  ngOnInit() {
    this.titleService.setTitle('Fieldomobify | Admin signup dashboard');
    this.signupForm = new FormGroup({
      company: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
    this.height = `${window.innerHeight}px`;
  }

  @HostListener('window:resize', ['$event'])
  resize(event: any) {
    this.height = `${event.target.innerHeight}px`;
  }

  submitSignup() {
    if (this.signupForm.valid) {
      this.validMessage = "Successful sign up. Thank you!";
      this.accountService.createAccount(this.signupForm.value).subscribe(
        data => {
          this.signupForm.reset();
          return true;
        },
        error => {
          return Observable.throw(error);
        }
      )
    } else {
      this.validMessage = "Please fill out the form before submitting!";
    }
  }

}
