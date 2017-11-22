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

  constructor(private route: ActivatedRoute, private orderService: OrderService) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.orderService.getOrder(params['id']).subscribe((result) => {
        console.log(result);
        this.loading = false;
      });
    });
  }

}
