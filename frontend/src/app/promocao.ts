export class Promocao {
  produtos:PromocaoProduct[];	
	dtInicial: string;
	dtFinal: string;	
	peDesconto: number;
}

export class PromocaoProduct {
	id: number;
	checked: boolean;
	name: string;
}
