import { Injectable } from '@angular/core';

declare var toastr:any;

@Injectable({
  providedIn: 'root'
})
export class ToasterService {

  constructor() { 
    this.config();
  }

  infoMessages(title:string,message?:string)
  {
    toastr.info(title,message);
  }

  deleteMessages(title:string,message?:string)
  {
    toastr.error(title,message);
  }

  warningMessages(title:string,message?:string)
  {
    toastr.warning(title,message);
  }
  successMessages(title:string,message?:string)
  {
    toastr.success(title,message);
  }

config(){

  toastr.options = {
    "closeButton": true,
    "progressBar": true,
    "positionClass": "toast-top-full-width",
    "showDuration": "300",
    "hideDuration": "1000",
    "timeOut": "5000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
  }
  
  
  }
}
