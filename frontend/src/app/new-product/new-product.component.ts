import { Component, OnInit } from '@angular/core';
import { Product } from '../product';

@Component({
  selector: 'app-new-product',
  templateUrl: './new-product.component.html',
  styleUrls: ['./new-product.component.css']
})
export class NewProductComponent implements OnInit {
  product: Product = {
    available: false,
    description: "",
    id: null,
    isNew: true,
    name: "",
    price: null,
    quantity: 0,
    quantity_buy: 0
  }
  
  constructor() { }

  ngOnInit() {
  }

}
