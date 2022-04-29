import { Product } from "./product";
export class ProductOrder {
    constructor(p:Product,q:number){
    this.product=p;
    this.quantity=q;
    }
    name:string;
    product: Product;
    quantity: number;
    dateCreated:Date;
}
