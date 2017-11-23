import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrderService } from '../order-service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  loading: boolean = true;
  private sub:any;
  order:any;
  newStatus:string;

  constructor(private route: ActivatedRoute, private orderService: OrderService) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.orderService.getOrder(params['id']).subscribe((result) => {
        this.order = result;
        this.loading = false;
      });
    });
  }

  changeStatus(){
    this.orderService.changeOrderStatus(this.order.id, this.newStatus).subscribe(result => {
      if (result === true) {
        alert("Pedido alterado com sucesso!");
        this.loading = true;
        this.orderService.getOrder(this.order.id).subscribe((result) => {
          this.order = result;
          this.loading = false;
        });
      }
    });
  }

}
