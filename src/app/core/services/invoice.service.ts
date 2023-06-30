import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Invoice } from '../models';

@Injectable()
export class InvoiceService {
  baseURL: string = 'localhost:8080/';

  constructor(private http: HttpClient) {}

  addInvoice(userId: string, invoice: Invoice): Observable<Invoice> {
    return this.http.post<Invoice>(
      this.baseURL + 'user/' + userId + '/invoice',
      invoice
    );
  }

  getInvoice(userId: string, invoiceId: string): Observable<Invoice> {
    return this.http.get<Invoice>(
      this.baseURL + '/user' + userId + '/invoice' + invoiceId
    );
  }

  updateInvoice(
    userId: string,
    invoiceId: string,
    invoice: Invoice
  ): Observable<Invoice> {
    return this.http.put<Invoice>(
      this.baseURL + '/user' + userId + '/invoice' + invoiceId,
      invoice
    );
  }

  deleteInvoice(userId: string, invoiceId: string): void {
    this.http.delete<Invoice>(
      this.baseURL + 'user/' + userId + '/invoice' + invoiceId
    );
  }
}
