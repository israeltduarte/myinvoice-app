import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import data from '../../../api/data.json';
// import { Observable } from 'rxjs';
import { Company } from '../models/company';

@Injectable()
export class CompanyService {
  baseURL: string = 'localhost:8080/';

  constructor(private http: HttpClient) {}

  getCompany(companyId: string): Company {
    return data.company;
    // return this.http.get<Company>(this.baseURL + 'user/' + userId + '/company');
  }
}
