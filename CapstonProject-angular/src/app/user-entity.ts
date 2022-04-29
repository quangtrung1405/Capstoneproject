export class UserEntity {
    constructor(
        public id:number,
    public username:string,
    public email:string,
    public password:string,
    public contact:number,
    public gender:string,
    public address:string,
    public city:string,
    public state:string
    ){ }
}
