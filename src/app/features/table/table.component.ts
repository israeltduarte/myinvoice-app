import { Component } from '@angular/core';

import { Item } from './item';

@Component({
  selector: 'app-items-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
})
export class TableComponent {
  items: Item[] = [
    // { description: 'consultancy services', quantity: 12, price: 12, total: 0 },
  ];
  subtotal: string = '';
  item: Item = new Item('', 0, 0, 0);
  show: boolean = false;

  ngOnInit() {
    this.show = this.items.length > 0;
  }

  addNewItem(): void {
    this.items.push(this.item);
    this.show = this.items.length > 0;
    this.calculateSubTotal();
    this.resetItem();
  }

  calculateSubTotal(): void {
    this.subtotal = this.items
      .reduce(
        (accumulator, item) => accumulator + item.quantity * item.price,
        0
      )
      .toFixed(2);
  }

  resetItem(): void {
    this.item = new Item('', 0, 0, 0);
  }
}
