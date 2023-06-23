export class Item {
  constructor(
    public description: string,
    public quantity: number,
    public price: number,
    public total: number,
    public removing: boolean,
    public adding: boolean
  ) {}
}
