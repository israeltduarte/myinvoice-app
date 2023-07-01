import { Company } from './company';
import { Item } from './item';

export function newEmptyCompany(): Company {
  return new Company('', '', '', '', '', '', '', '', '', '', '', '');
}

export function newEmptyItem(): Item {
  return new Item('', '', '', 0, 0, 0, false, false);
}
