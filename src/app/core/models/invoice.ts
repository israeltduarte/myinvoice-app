import { Item } from './item';

export class Invoice {
  constructor(
    public id: string,
    public date: string,
    public items: Item[]
  ) {}
}
