export class Item {
  constructor(
    public id: string,
    public user_id: string,
    public description: string,
    public hours: number,
    public price: number,
    public total: number,
    public removing: boolean = false,
    public adding: boolean = false
  ) {}
}
