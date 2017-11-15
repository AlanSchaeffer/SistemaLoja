import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart-service';
import { OrderService } from '../order-service';
import { Order } from '../order';
import { Router } from '@angular/router';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {
  order: Order;
  endereco: string = "";
  constructor(private cartService: CartService,
    private orderService: OrderService,
    private router: Router) { }

  ngOnInit() {
    this.order = this.cartService.getOrder();
  }

  confirmOrder(){
    this.orderService.confirmOrder(this.endereco).subscribe(response => {
      if (response === true){
        alert("Pedido confirmado com sucesso!");
        this.router.navigate(['customer-orders']);
      } else {
        alert("Houve um erro ao confirmar o pedido");
      }
    });
  }

}
