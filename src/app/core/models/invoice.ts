import { Item } from './item';

export class Invoice {
  constructor(
    public id: string,
    public number: number,
    public date: string,
    public companyId: string,
    public clientId: string,
    public total: number,
    public currency: string,
    public isPublished: boolean,
    public items: Item[]
  ) {}
}
