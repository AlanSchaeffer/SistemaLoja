import { Component, OnInit } from '@angular/core';
import { ProductsService } from '../products.service';
import { Product } from '../product';
import { CartService } from '../cart-service';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

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
    this.cartService.mergeItemToCart(product).subscribe(response => {
      if (!(response === true)){
        alert("Não é possível adicionar quantidade maior que a disponível em estoque");
      } else {
        this.totalCart = this.cartService.getTotalValue();
      }
    });
  }

  removeFromCart(product: Product) {
    this.cartService.removeItemFromCart(product).subscribe(response => {
      if (response === true){
        this.cartService.removeItemFromCart(product);
        this.totalCart = this.cartService.getTotalValue();
      } else {
        alert("Não foi possível excluir o item do carrinho");
      }
    });
  }
}
