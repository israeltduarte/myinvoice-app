import { animate, style, transition, trigger } from '@angular/animations';
import { Component } from '@angular/core';
import * as converter from 'number-to-words';
import {
  Client,
  Company,
  Invoice,
  Item,
  newEmptyInvoice,
  newEmptyItem,
} from 'src/app/core/models';
import {
  ClientService,
  CompanyService,
  InvoiceService,
} from 'src/app/core/services';
import data from '../../../../api/data.json';

@Component({
  selector: 'app-new-invoice',
  providers: [CompanyService, InvoiceService, ClientService],
  templateUrl: './new-invoice.component.html',
  styleUrls: ['./new-invoice.component.css'],
  animations: [
    trigger('fadeOut', [
      transition(':leave', [
        style({ opacity: 1 }),
        animate('300ms', style({ opacity: 0 })),
      ]),
    ]),
  ],
})
export class NewInvoiceComponent {
  invoice: Invoice = newEmptyInvoice();
  companies!: Company[];
  clients!: Client[];

  selectedCompany!: Company;
  selectedClient!: Client;

  subtotal: string = '';
  wordsSubtotal: string = '';
  newItem: Item = newEmptyItem();
  showBalance: boolean = false;

  constructor(
    private invoiceService: InvoiceService,
    private companyService: CompanyService,
    private clientService: ClientService
  ) {}

  ngOnInit() {
    this.companies = data.companies;
    this.clients = data.clients;
    this.selectedCompany = this.companies[0];
    this.selectedClient = this.clients[0];

    this.invoice.number = this.invoiceService.getNextInvoiceNumber() + 1;

    // this.invoiceService.getNextInvoiceNumber().subscribe(
    //   (nextNumber: number) => {
    //     console.log(nextNumber);
    //     this.invoice.number = nextNumber;
    //   },
    //   (error: any) => {
    //     console.error('Error fetching next invoice number:', error);
    //   }
    // );
  }

  addNewItem(): void {
    this.invoice.items.push(this.newItem);
    this.showBalance = this.invoice.items.length > 0;
    this.calculateSubTotal();
    this.resetItem();

    setTimeout(() => {
      const newItemIndex = this.invoice.items.length - 1;
      this.invoice.items[newItemIndex].adding = true;
      setTimeout(() => {
        this.invoice.items[newItemIndex].adding = false;
      }, 300);
    });
  }

  calculateSubTotal(): void {
    this.subtotal = this.invoice.items
      .reduce((accumulator, item) => accumulator + item.hours * item.price, 0)
      .toFixed(2);
    this.wordsSubtotal = converter.toWords(this.subtotal);
  }

  removeItem(item: Item): void {
    const index = this.invoice.items.indexOf(item);
    if (index !== -1) {
      this.invoice.items[index].removing = true;
      setTimeout(() => {
        this.invoice.items.splice(index, 1);
        this.calculateSubTotal();
      }, 300);
    }
  }

  resetItem(): void {
    this.newItem = newEmptyItem();
  }

  saveInvoice(): void {
    const newInvoice: Invoice = {
      ...this.invoice,
      id: new Date().getTime().toString(),
      date: new Date(this.invoice.date).getTime(),
    };

    const savedInvoice = this.invoiceService
      .addInvoice('userId', newInvoice)
      .subscribe(
        (response) => {
          console.log('Invoice saved successfully:', response);
          this.invoice.id = response.id;
          data.invoices.push(newInvoice);
        },
        (error) => {
          console.error('Error saving invoice:', error);
        }
      );

    this.invoice = newEmptyInvoice();
    this.newItem = newEmptyItem();
    this.showBalance = false;
    this.subtotal = '';
    this.wordsSubtotal = '';
  }
}
