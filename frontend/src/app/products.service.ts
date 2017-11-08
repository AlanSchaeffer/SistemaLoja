import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';
import { UserService } from './user.service';
import { Product } from './product';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
export class ProductsService {
  private getProductsUrl:string = "http://localhost:8080/services/produtos/listar";
  private getProductUrl:string = "http://localhost:8080/services/produtos/";
  private saveProductsUrl:string = "http://localhost:8080/services/produtos/salvar";

  constructor(private http:Http, private userService:UserService) { }

  getProducts(filter: string){    
    return this.http.post(this.getProductsUrl, { nome: filter }, this.userService.getHeaders())
      .map((result) => {
        return result.json().produtos.map(item => this.productFromJson(item));
      }).catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  getProduct(id:string){
    return this.http.get(this.getProductUrl + id, this.userService.getHeaders())
      .map((result) => {
        return this.productFromJson(result.json());        
      }).catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  save(product:Product){
    return this.http.post(
        this.saveProductsUrl, 
        { 
          id: product.id, 
          nome: product.name,
          preco: product.price,
          descricao: product.description,
          estoque: product.quantity
        }, 
        this.userService.getHeaders())
      .map((result) => {
        return result.ok;
      }).catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  private productFromJson(item: any) : Product {
    var product = new Product();
    product.id = item.id;
    product.name = item.nome;
    product.price = item.preco;
    product.available = item.temEstoque || false;
    product.description = item.descricao;
    product.quantity = item.estoque;
    
    return product;
  }  
}
