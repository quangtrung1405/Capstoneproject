import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'date' })
export class DatePipe implements PipeTransform {

  transform(value: any[], filterString: Date,propName:Date): any[] {
    const result:any=[];
      if(!value || filterString || propName){
  return value;
}
value.forEach((a:any)=>{
  if(a[propName].trim().toLowerCase()){
    result.push(a);
  }
});
return result;
  }
}