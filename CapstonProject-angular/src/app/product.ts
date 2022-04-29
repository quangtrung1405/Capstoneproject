import { ProductOrder } from "./product-order";

export class Product {
    dateCreated: any;
    constructor(
     public id:number,
     public name:string,
     public description:string,
     public image:string,
     public price:number,
     public stocks:number,
     public category:string,
     public product: ProductOrder
    ){}
}
