import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Invoice } from '../models';

@Injectable()
export class InvoiceService {
  baseURL: string = 'localhost:8080/';

  constructor(private http: HttpClient) {}

  getAllInvoices(userId: string) {
    return this.http.get<Invoice>(this.baseURL + '/invoice');
  }

  addInvoice(userId: string, invoice: Invoice): Observable<Invoice> {
    return this.http.post<Invoice>(this.baseURL + '/invoice/', invoice);
  }

  getInvoice(userId: string, invoiceId: string): Observable<Invoice> {
    return this.http.get<Invoice>(this.baseURL + '/invoice/' + invoiceId);
  }

  updateInvoice(
    userId: string,
    invoiceId: string,
    invoice: Invoice
  ): Observable<Invoice> {
    return this.http.put<Invoice>(
      this.baseURL + '/invoice/' + invoiceId,
      invoice
    );
  }

  deleteInvoice(userId: string, invoiceId: string): void {
    this.http.delete<Invoice>(this.baseURL + '/invoice/' + invoiceId);
  }

  getNextInvoiceNumber(): number {
    // return this.http.get<number>(this.baseURL + '/invoice/number').pipe(
    //   map((response: number) => {
    //     return 10;
    //   })
    // );
    return 10;
  }
}
