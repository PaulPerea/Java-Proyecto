



let a="let";
 a = "hola";
console.log(a);




// if (true) {
//     var b = "ya";
// }
// console.log(b);

let c=1;

let ciclo=null; //almacena el TimeOut

function carrusel(){

    $("#imgheader").attr("src","imagenes/img"+c+".jpg");

    c++;

    if(c>=6) c=1;



    ciclo=setTimeout("carrusel()",1000);

}
//Notaciones de eventos incompletos
function java(frm){

    let dni=frm.txtdni.value;


    let expresion=/^\d{8}$/;
    if(expresion.test(dni)==false){
        window.event.preventDefault();
        alert("DNI tiene 8 digitos");
        return;
    }

    let nombre=frm.txtnombre.value;
    expresion=/^\s*$/;
    if(expresion.test(nombre)){
        window.event.preventDefault();
        alert("Nombre no debe estar vacio");
        return;
    }

    let apellido=frm.txtapellido.value;
    expresion=/^\s*$/;
    if(expresion.test(apellido)){
        window.event.preventDefault();
        alert("Apellidos no debe estar vacio");
        return;
    }

    let email = frm.txtemail.value;
    expresion = /^\w+[@]\w+[.][a-zA-Z]{2,3}([.][a-zA-Z]{2,3})*$/;
    if(!expresion.test(email)) {
        window.event.preventDefault();
        alert("Formato de email incorrecto");
        return;
    }

     let dirección=frm.txtdireccion.value;
     expresion=/^\w{2,}$/;
     if(!expresion.test(dirección)){
         window.event.preventDefault();
         alert("Dirección vacío");
         return;
     }
  
     let fono=frm.txtfono.value;
     expresion=/^[2-9]{1}[0-9]{6,8}$/;
     if(!expresion.test(fono)){
         window.event.preventDefault();
         alert("Ingrese numero telefonico entre 7 a 9 dígitos");
         return;
     }
     
    alert("Datos Ingresados correctamente");
}

//Funcion de coloreo para h1
function colorear(){

    let r = Math.random() * 255;

    let g = Math.random() * 255;

    let b = Math.random() * 255;

    //el primer h1 de la pagina

    $('h1').first().attr("style", "color: rgb(" + r +","+ g + "," + b + ")");



    setTimeout("colorear()",1000);

}
$(document).ready(function(){ 
    $('.social').mouseenter(function() {
        $(this).attr("style","transform:rotateX(360deg); transition: all 0.5s ease");
    });

    $('.social').mouseleave(function() {
        $(this).attr("style","transform:rotateX(0deg); transition: all 0.2s ease");
    });

    carrusel();
    colorear();
});



