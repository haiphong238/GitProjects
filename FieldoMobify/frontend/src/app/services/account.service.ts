import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
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
    return this.http.post('/server/accounts', body, httpOptions);
  }

  login(account){
    let body = JSON.stringify(account);
    return this.http.post('/server/login', body, httpOptions);
  }
}
