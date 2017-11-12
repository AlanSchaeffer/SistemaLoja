import { Component, OnInit } from '@angular/core';
import { ProductsService } from '../products.service';
import { Product } from '../product';
import { CartService } from '../cart-service';

@Component({
  selector: 'app-customer-home',
  templateUrl: './customer-home.component.html',
  styleUrls: ['./customer-home.component.css']
})
export class CustomerHomeComponent implements OnInit {
  loading: boolean = true;
  totalCart: number = 0;
  products: Product[];

  constructor(private productsService: ProductsService, private cartService: CartService) { }

  ngOnInit() {
    this.productsService.getProducts("").subscribe(result => {
      this.products = result;
      this.loading = false;            
    });

    this.totalCart = this.cartService.getTotalValue();
  }

  addToCart(product: Product) {
    if (!this.cartService.mergeItemToCart(product)){
      alert("Não é possível adicionar quantidade maior que a disponível em estoque");
      return;
    }

    this.totalCart = this.cartService.getTotalValue();
  }

  removeFromCart(product: Product) {    
    this.cartService.removeItemFromCart(product);
    this.totalCart = this.cartService.getTotalValue();
  }
}
