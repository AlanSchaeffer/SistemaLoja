import { Injectable } from '@angular/core';
import { Order } from './order';

@Injectable()
export class OrderService {
  createOrder(order: Order){
    if (localStorage.getItem('orders') == null) {
      localStorage.setItem('orders', JSON.stringify(new Array<Order>()));
    }

    var orders = this.getOrders();
    orders.push(order);
    this.setOrders(orders);
  }

  getOrders():Order[] {
    return JSON.parse(localStorage.getItem('orders')) as Order[];
  }

  setOrders(orders: Order[]) {
    localStorage.setItem('orders', JSON.stringify(orders));
  }
}
