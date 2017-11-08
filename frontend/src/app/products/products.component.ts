import { Component, OnInit, Input } from '@angular/core';
import { Product } from '../product';
import { ProductsService } from '../products.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  @Input() product: Product;
  constructor(private productsService: ProductsService) { }

  ngOnInit() {
  }

  save() {
    this.productsService.save(this.product).subscribe(result => {
      if (result == true) {
        alert("Produto salvo com sucesso");
      } else {
        alert("Ocorreu um erro ao salvar o produto");
      }      
    });
  }
}
