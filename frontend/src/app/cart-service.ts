import { Injectable } from '@angular/core';
import { Product } from './product';
import { Order } from './order';
import { OrderItem } from './order-item';

@Injectable()
export class CartService {
  constructor() {
    if (localStorage.getItem('order') == null ||
      localStorage.getItem('order') == undefined) {
        let order:Order = {
          status: 'CRIADO',
          orderItems: new Array() as Array<OrderItem>
        };
        
        this.setOrder(order);
      }
  }

  mergeItemToCart(product: Product): boolean {
    var order = this.getOrder();
    var orderItem = new OrderItem();
    orderItem.product = product;
    orderItem.quantity = product.quantity_buy;
    
    if (product.quantity_buy > product.quantity) {
      return false;
    }

    if (this.orderContainsProduct(order, product)) 
    {
      order = this.removeItemFromOrder(order, product);
    }

    order.orderItems.push(orderItem);    
    this.setOrder(order);
    return true;
  }
  
  removeItemFromCart(product: Product){
    var order = this.removeItemFromOrder(this.getOrder(), product);
    this.setOrder(order);
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
