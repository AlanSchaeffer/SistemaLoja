import { Component, OnInit } from '@angular/core';
import { OrderService } from '../order-service';

@Component({
  selector: 'app-customer-orders',
  templateUrl: './customer-orders.component.html',
  styleUrls: ['./customer-orders.component.css']
})
export class CustomerOrdersComponent implements OnInit {
  loading: boolean = true;
  orders: any[];

  constructor(private orderService: OrderService) { }

  ngOnInit() {
    this.loadOrders();
  }

  cancelOrder(id){
    this.orderService.cancelOrder(id).subscribe(result => {
      if (result === true) {
        alert("Pedido Cancelado Com Sucesso!");
        this.loading = true;
        this.loadOrders();
      }
    });
  }

  loadOrders(){
    this.orderService.getOrders().subscribe(result => {
      this.orders = result;
      this.loading = false;
    });
  }

}
