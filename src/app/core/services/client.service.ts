import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import data from '../../../api/data.json';
// import { Observable } from 'rxjs';
import { Client } from '../models/client';

@Injectable()
export class ClientService {
  baseURL: string = 'localhost:8080/';

  constructor(private http: HttpClient) {}

  getClient(clientId: string): Client {
    return data.client;
    // return this.http.get<Company>(this.baseURL + 'user/' + userId + '/company');
  }
}
