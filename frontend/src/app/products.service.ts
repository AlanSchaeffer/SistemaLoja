import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';
import { Product } from './product';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
export class ProductsService {
  private getProductsUrl:string = "http://localhost:8080/services/";
  private saveProductsUrl:string = "http://localhost:8080/services/";

  constructor(private http:Http) { }

  getProducts(): Product[]{
    return [
      {
        id: 1,
        description: "Blablablabla",
        name: "Mega Teste",
        price: 25.5,
        isNew: false,
        quantity: 10        
      },
      {
        id: 2,
        description: "Lorem Ipsum",
        name: "Mega Teste 2",
        price: 99.5,
        isNew: false,
        quantity: 5        
      },
    ];
  }
}
