import { Component } from '@angular/core';
import * as converter from 'number-to-words';

import { Item } from './item';

@Component({
  selector: 'app-items-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
})
export class TableComponent {
  items: Item[] = [
    {
      description: 'software development services',
      quantity: 64,
      price: 19,
      total: 0,
    },
    {
      description: 'software development extra hours',
      quantity: 12,
      price: 22,
      total: 0,
    },
  ];
  subtotal: string = '';
  wordsSubtotal: string = '';
  item: Item = new Item('', 0, 0, 0);
  show: boolean = false;

  ngOnInit() {
    this.show = this.items.length > 0;
    this.calculateSubTotal();
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
    this.wordsSubtotal = converter.toWords(this.subtotal);
  }

  removeItem(item: Item): void {
    const index = this.items.indexOf(item);
    if (index !== -1) {
      this.items.splice(index, 1);
      this.calculateSubTotal();
    }
  }

  resetItem(): void {
    this.item = new Item('', 0, 0, 0);
  }
}
