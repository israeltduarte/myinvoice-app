import { Component } from '@angular/core';
import * as converter from 'number-to-words';
import { trigger, style, animate, transition } from '@angular/animations';

import { Item } from 'src/app/core/models/item';

@Component({
  selector: 'app-items-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
  animations: [
    trigger('fadeOut', [
      transition(':leave', [
        style({ opacity: 1 }),
        animate('300ms', style({ opacity: 0 })),
      ]),
    ]),
  ],
})
export class TableComponent {
  items: Item[] = [
    {
      description: 'software development services',
      quantity: 64,
      price: 19,
      total: 0,
      removing: false,
      adding: false,
    },
    {
      description: 'software development extra hours',
      quantity: 12,
      price: 22,
      total: 0,
      removing: false,
      adding: false,
    },
  ];
  subtotal: string = '';
  wordsSubtotal: string = '';
  item: Item = new Item('', 0, 0, 0, false, false);
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

    setTimeout(() => {
      const newItemIndex = this.items.length - 1;
      this.items[newItemIndex].adding = true;
      setTimeout(() => {
        this.items[newItemIndex].adding = false;
      }, 300);
    });
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
      this.items[index].removing = true;
      setTimeout(() => {
        this.items.splice(index, 1);
        this.calculateSubTotal();
      }, 300);
    }
  }

  resetItem(): void {
    this.item = new Item('', 0, 0, 0, false, false);
  }
}
