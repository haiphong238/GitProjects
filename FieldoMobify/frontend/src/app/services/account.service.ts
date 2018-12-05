import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IAccount } from '../components/login/account';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': 'Basic d2ViLWNsaWVudDp3ZWItY2xpZW50LXNlY3JldA=='})
};

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http: HttpClient) { }

  getBikes() {
    return this.http.get('/server/bikes');
  }

  getBike(id: number) {
    return this.http.get('/server/bikes/' + id);
  }

  createAccount(account) {
    let body = JSON.stringify(account);
    try{
    return this.http.post('/server/accounts', body, httpOptions);
    }catch{
      ((e: any) => Observable.throw(this.errorHandler(e)));
    }
  }
  errorHandler(error:any) : void {
    console.log(error);
  }
  
  login(account){
    let body = JSON.stringify(account);
    const url = 'http://localhost:8081/auth-service/oauth/token?grant_type=password&username=user&password=user';
    return this.http
    .post(url, body, httpOptions)
    .subscribe(res => {
      console.log(res);
      console.log(typeof res);
      // let result = res.json();
      // if (result && result.token) {
          // localStorage.setItem('token', result.token);
         // return true;
      // }
      // return false;
    })
    ;
  }

  getAccountsByEmail(email:string)
  {
    return this.http.get('localhost:8082/resource-service/api/v1/accounts');
    //return this.http.get('/server/email/'+ email +'/accounts');
  }
}
