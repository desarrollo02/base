//            
//            TODO DEScomentar lo de abajo para habilitar applet de impresion
//            deployQZ();

function cUpper(cObj) {
    cObj.value = cObj.value.toUpperCase();
}
function esNumero(cObj)
{

    if (isNaN(cObj.value))
    {
        alert("El valor introducido debe ser numerico");
        form.cObj.focus();
        return false;
    }
}

function cerrarVentana(){
    this.windows.close();
}

function montoFPfocus() {
    //input is the input element
    document.getElementById('form:montoPago2').focus();
//input.focus(); //sets focus to element
    var val = document.getElementById('form:montoPago2').value; //store the value of the element
    document.getElementById('form:montoPago2').value = ''; //clear the value of the element
    document.getElementById('form:montoPago2').value = val; //set that value back. 

}


function isAlphabet(elem, helperMsg) {
    var alphaExp = /^[a-zA-Z]+$/;
    if (elem.value.match(alphaExp)) {
        cUpper(elem);
        return true;
    } else {
        alert(helperMsg);
        elem.focus();
        return false;
    }
}
function cUpper(cObj) {
    cObj.value = cObj.value.toUpperCase();
}

function openDialog(e, win) {
    if (e.keyCode == 120) ////F9
        win.show();
}
function ejecutaAgregar(e, obj) {
    alert('sdf');
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 13) { //ENTER
   
        ejecutar('form:tab:add');
      
    } 
}

  $(document).ready(function() {
   /* Aquí podría filtrar que controles necesitará manejar,
    * en el caso de incluir un dropbox $('input, select');
    */
   tb = $('input');
   
   if ($.browser.mozilla) {
       $(tb).keypress(enter2tab);
   } else {
       $(tb).keydown(enter2tab);
   }
   });
function enter2tab(e) {
       if (e.keyCode == 13) {
              ejecutar('tab2:add');
          
       }
   }
function ejecutarEnterDialogo(nombreGrilla,e){
   
   tecla = (document.all) ? e.keyCode : e.which;
   
   if (document.getElementById(nombreGrilla+":0:botonSeleccionar") && ((tecla == 13))) {
        ejecutar(nombreGrilla+":0:botonSeleccionar");
        focus();
        return false;
    }
    
}
function pulsarInput(e){
    //alert("hola");
   tecla = (document.all) ? e.keyCode : e.which;
   if (document.getElementById("fp:tab2:add") && ((tecla == 13) || (tecla == 9))) {
        //alert("hola");
        ejecutar('fp:tab2:add');
        focus();
        return false;
    }
   if (document.getElementById("tab2:add") && ((tecla == 13) || (tecla == 9))) {
        ejecutar('tab2:add');
        focus();
        return false;
    }
    
}
//function pulsarInput(e, o, nombre){
//    alert("nombre");
//   tecla = (document.all) ? e.keyCode : e.which;
//   
//   if (document.getElementById(nombre) && ((tecla == 13) || (tecla == 9))) {
//       
//        ejecutar(nombre);
//        focus();
//        return false;
//    }
//    
//}
function pulsar(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    //alert(e.id);
    if (tecla == 27) {//Escape
        ejecutar('form:inicio_modulo');
    }
    else if (tecla == 13) { //ENTER
return false;
        if (document.getElementById("tab2:add")) {
        ejecutar('tab2:add');
        focus();
        return false;
    }else{
            tabularEnter(e);
        
    }
    } else if (tecla == 119) { //F8
        ejecutarGuardar();
    } else if (tecla == 118) { //F7
        ejecutar('botonBusqueda');
    } else if (tecla == 121) { //F10
        ejecutar('botonBuscar');
    }
}

function ejecutarGuardar() {
    var obj = document.getElementById('form:botonGuardar');
    if (obj) {
        obj.click();
    }

}


function focus() {
    if (document.getElementById("tab2:inputCantCod")) {
        document.getElementById("tab2:inputCantCod").focus();
    }
    if (document.getElementById("form:cantidadCompra")) {
        document.getElementById("form:cantidadCompra").focus();
    }
    
    
    else if (document.getElementById("form:codArticulo")) {
        document.getElementById("form:codArticulo").focus();
    }
}
function ejecutar(boton) {
    var obj = document.getElementById(boton);
    if (obj) {
        obj.click();
    }
}

function print64(pdf) {
    
  //  alert("ksdf");
  var iframeName = "print_frame" + getId();

  var iframe = document.createElement('iframe');
  iframe.name = iframeName;
  iframe.src = pdfb64toBlob(pdf);
  iframe.height = '0%';
  iframe.width = '0%';


  document.body.appendChild(iframe);

  setTimeout(function () {
    // window.frames[iframeName].focus();
    // window.frames[iframeName].print();

  }, 300);
}
/*    function print64(file) {
 
 //if (notReady()) {return;}
 useDefaultPrinter();
 // Send base64 encoded characters/raw commands to qz using "append64"
 // This will automatically convert provided base64 encoded text into 
 // text/ascii/bytes, etc.  This example is for EPL and contains an 
 // embedded image.  Please adapt to your printer language
 qz.append64(file);
 
 // Tell the apple to print.
 
 qz.print();
 } */

function getId() {
  return parseInt(Math.random() * 1000)
}

function pdfb64toBlob(b64pdf) {
  return URL.createObjectURL(b64toBlob(b64pdf, 'application/pdf'))
}

function b64toBlob(b64Data, contentType) {
  var byteCharacters = atob(b64Data);

  var byteArrays = [];

  for (var offset = 0; offset < byteCharacters.length; offset += 512) {
    var slice = byteCharacters.slice(offset, offset + 512),
      byteNumbers = new Array(slice.length);
    for (var i = 0; i < slice.length; i++) {
      byteNumbers[i] = slice.charCodeAt(i)
    }
    var byteArray = new Uint8Array(byteNumbers);

    byteArrays.push(byteArray)
  }

  return new Blob(byteArrays, {type: contentType})
}


function tabularEnter(e, obj) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla != 13)
        return;
    frm = obj.form;
    for (i = 0; i < frm.elements.length; i++)
        if (frm.elements[i] == obj) {
            if (i == frm.elements.length - 1)
                i = -1;
            break
        }

    frm.elements[i + 1].focus();
    return false;
}


function tabular(e, obj) {
    //  alert(document.getElementById('form:codArticulo').value);
    tecla = (document.all) ? e.keyCode : e.which;

    if (tecla != 107)
        return;
    /* frm = obj.form;
     for (i = 0; i < frm.elements.length; i++)
     if (frm.elements[i] == obj) {
     if (i == frm.elements.length - 1)
     i = -1;
     break
     }
     frm.elements[i + 1].focus();*/
    var str = document.getElementById('form:codArticulo').value;

    var newStr = str.substring(0, str.length);
    document.getElementById('form:codArticulo').value = newStr;
    document.getElementById('form:cantidad').focus();

    return true;

}

function verificaSigno(e, obj) {
    tecla = (document.all) ? e.keyCode : e.which;
    //Reemplazamos todas las "a" por "o"
    if (tecla != 107)
        return;
    var texto = document.getElementById('form:cantidad').value;
    texto = texto.replace("+", "");
    document.getElementById('form:cantidad').value = texto;

}



    function handleComplete(xhr, status, args) {
      //  alert(args.user);
        if(args.user==="00")
            PF('seccionABMFormaPago').select(1);
        else
             PF('seccionABMFormaPago').select(0);
//        if(args.validationFailed) {
//            PrimeFaces.debug("Validation Failed");
//        } 
//        else {
//            PrimeFaces.debug("Save:" + args.saved);
//            PrimeFaces.debug("FirstName: " + args.user.firstname + ", Lastname: " + args.user.lastname);
//        }
    }
