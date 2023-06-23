import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { invoice } from '../models';

@Injectable()
export class InvoiceService {
  baseURL: string = 'localhost:8080/';

  constructor(private http: HttpClient) {}

  getRepos(userId: string): Observable<invoice[]> {
    return this.http.get<invoice[]>(
      this.baseURL + 'user/' + userId + '/invoice'
    );
  }
}
