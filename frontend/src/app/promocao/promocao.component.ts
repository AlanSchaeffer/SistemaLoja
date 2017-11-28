import { Component, OnInit } from '@angular/core';
import { ProductsService } from '../products.service';
import { PromocaoService } from '../promocao-service';
import { Product } from '../product';
import { Promocao, PromocaoProduct } from '../promocao';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Component({
  selector: 'app-promocao',
  templateUrl: './promocao.component.html',
  styleUrls: ['./promocao.component.css']
})
export class PromocaoComponent implements OnInit {
  loading: boolean = true;
  promocao: Promocao = new Promocao();

  constructor(private productsService: ProductsService,
    private promocaoService: PromocaoService,
    private router: Router) { }

  ngOnInit() {
    this.productsService.getProducts("").subscribe(result => {
      this.promocao.produtos = result.map((item, index, array) =>{
        var promocaoProduct = new PromocaoProduct();
        promocaoProduct.checked = false;
        promocaoProduct.id = item.id;
        promocaoProduct.name = item.name;
        this.loading = false;
        return promocaoProduct;
      });      
    });
  }

  save(){
    this.promocaoService.criarPromocao(this.promocao).subscribe(result => {
      if (result === true){
        alert("Promoção salva com sucesso!");
        this.router.navigate(['/admin/promocoes']);
      } else {
        alert("Houve um erro ao criar promoção");  
      }
    }, (error) => {
      alert("Houve um erro ao criar promoção");
    });
  }
}
