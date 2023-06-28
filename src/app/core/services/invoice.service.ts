import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Invoice } from '../models';

@Injectable()
export class InvoiceService {
  baseURL: string = 'localhost:8080/';

  constructor(private http: HttpClient) {}

  addInvoice(invoice: Invoice, userId: string): Observable<Invoice> {
    return this.http.post<Invoice>(
      this.baseURL + 'user/' + userId + '/invoice',
      invoice
    );
  }
}
