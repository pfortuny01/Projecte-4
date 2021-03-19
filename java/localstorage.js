window.addEventListener("load", inici, false);

function inici() {    
    mostrardades();
}

function mostrardades() {
    
    document.getElementsByClassName("contingut")[0].innerHTML="";
    
    for (i = 0; i < localStorage.length; i++) {


        clau = localStorage.key(i);
        text = localStorage.getItem(clau);
        obj = JSON.parse(text);
        

        document.getElementsByClassName("contingut")[0].innerHTML +="<div class='producte'><img src='" + obj.imatge + "' alt='imatge'>" +
            "<div class='objectes'><p class='id_prod'>" + obj.id + "</p>" +
            "<p class='nombre'>" + obj.nom + "</p>" +
            "<p class='precio'>" + obj.preu + "</p></div>" +
            "<button onclick='Delete(\""+clau+"\")' class='botonet'><i class='fa fa-fw fa-remove' style= 'font-size: 25px; color: red'></i></button></div>";
        
    }
}
function Delete(clau) {
    if(confirm("Estas segur que vols borrar aquest producte?")){
        localStorage.removeItem(clau);
        mostrardades();
    }
}

function netejarcarrito(){
    if(confirm("Estas segur que vols netejar el carrito?")){
        localStorage.clear();
        mostrardades();
    }
    
}
