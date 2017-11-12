import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart-service';
import { OrderService } from '../order-service';
import { Order } from '../order';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {
  order:Order;
  constructor(private cartService: CartService) { }

  ngOnInit() {
    this.order = this.cartService.getOrder();
  }

  confirmOrder(){

  }

}
