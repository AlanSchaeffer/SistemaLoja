import { Injectable } from '@angular/core';
import { Order } from './order';
import { CartService } from './cart-service';
import { UserService } from './user.service';
import { Http, RequestOptions } from '@angular/http';

@Injectable()
export class OrderService {
  private urlConfirmOrder = "http://localhost:8080/services/pedidos/realizar/";
  private urlGetOrders = "http://localhost:8080/services/pedidos/listar/";
  private urlGetOrder = "http://localhost:8080/services/pedidos/";
  private urlChangeStatus = "http://localhost:8080/services/pedidos/alterarStatus/";
  private urlCancelOrder = "http://localhost:8080/services/pedidos/cancelar/";

  constructor(private userSerivce: UserService, private cartService: CartService, private http: Http){

  }

  confirmOrder(endereco: string) {
    return this.http
      .post(this.urlConfirmOrder, { endereco: endereco }, this.userSerivce.getHeaders())
      .map(response => {
        if (response.ok){
          localStorage.removeItem('order');
        }

        return response.ok === true;
      });
  }

  getOrders() {
    return this.http
      .get(this.urlGetOrders, this.userSerivce.getHeaders())
      .map(response => {        
        return response.json().pedidos;
      });
  }

  getOrder(id) {
    return this.http
      .get(this.urlGetOrder + id, this.userSerivce.getHeaders())
      .map(response => {        
        return response.json();
      });
  }

  cancelOrder(id){
    return this.http
      .get(this.urlCancelOrder + id, this.userSerivce.getHeaders())
      .map(response => {
        if (response.json().success === false)
          alert(response.json().message);        
        return response.json().success === true;
      });
  }

  changeOrderStatus(id, newStatus: string){
    return this.http
      .get(this.urlChangeStatus + id + "/" + newStatus, this.userSerivce.getHeaders())
      .map(response => {        
        return response.ok === true;
      });
  }
}
