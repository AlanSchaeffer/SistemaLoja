import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Product } from './product';
import { Order } from './order';
import { OrderItem } from './order-item';
import { UserService } from './user.service';
import { Observable } from 'rxjs/Rx';

@Injectable()
export class CartService {
  private urlAddItem: string = "http://localhost:8080/services/carrinho/add";
  private urlUpdateItemQuantity: string = "http://localhost:8080/services/carrinho/alterarQuantidade";
  private urlRemoveItem: string = "http://localhost:8080/services/carrinho/remove";

  constructor(private http: Http, private userService: UserService) {
    if (localStorage.getItem('order') == null ||
      localStorage.getItem('order') == undefined) {
        let order:Order = {
          status: 'CRIADO',
          orderItems: new Array() as Array<OrderItem>
        };
        
        this.setOrder(order);
      }
  }

  mergeItemToCart(product: Product) {
    let updateQuantity: boolean = false;
    var order = this.getOrder();
    var orderItem = new OrderItem();
    orderItem.product = product;
    orderItem.quantity = product.quantity_buy;
        
    if (this.orderContainsProduct(order, product)) 
    {
      updateQuantity = true;
    }

    return this.http
      .post((updateQuantity ? this.urlUpdateItemQuantity : this.urlAddItem) + "/" 
            + orderItem.product.id + "/"
            + orderItem.quantity, null,
            this.userService.getHeaders())
      .map((response) => {
        if (response.ok === true){
          if (updateQuantity){
            order = this.removeItemFromOrder(order, product);
          }
          order.orderItems.push(orderItem);    
          this.setOrder(order);
          return true
        }
        return false;
      });
  }
  
  removeItemFromCart(product: Product){
    return this.http
      .post(this.urlRemoveItem + "/" + product.id, null, this.userService.getHeaders())
      .map(response => {
        if (response.ok === true){
          var order = this.removeItemFromOrder(this.getOrder(), product);
          this.setOrder(order);
          product.quantity_buy = 0;
          return true;
        } else {
          return false;    
        }
      });    
  }

  removeItemFromOrder(order: Order, product: Product): Order{
    var filteredItems = order.orderItems.filter((item, index, orderItems) => {
      return item.product.id != product.id;
    });

    order.orderItems = filteredItems;

    return order;
  }

  orderContainsProduct(order: Order, product: Product): boolean {
    return order.orderItems.some(this.searchItemCallback(product));
  }

  getOrderItem(order: Order, product: Product) {
    return order.orderItems.find(this.searchItemCallback(product));
  }

  getOrder(){
    return JSON.parse(localStorage.getItem('order')) as Order;
  }

  setOrder(order: Order){
    localStorage.setItem('order', JSON.stringify(order));
  }

  searchItemCallback(product) {    
      return function(item, index, orderItems): boolean {
        return item.product.id == product.id;
      };
  }

  getTotalValue(){
    var result = 0;
    var currentOrder = this.getOrder();
    
    if (currentOrder == null || currentOrder.orderItems == null) {
      return result;
    }    

    currentOrder.orderItems.forEach((item, index, orderItems) => {
      result += item.product.price * item.quantity;
    });

    return result;
  }
}
