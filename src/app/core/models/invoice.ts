import { Item } from './item';

export class Invoice {
  constructor(
    public id: string,
    public number: number,
    public date: string,
    public companyId: string,
    public clientId: string,
    public items: Item[],
    public isPublished: boolean
  ) {}
}
