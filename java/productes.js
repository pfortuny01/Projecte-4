window.addEventListener("load", inici, false);

function inici() {

    producte1();
    producte2();
    producte3();
    producte4();    
    mostrardades();
}

/*********************************************************/

function producte1() {

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            lecturaXML1(this);
        }
    };
    xhttp.open("GET", "xml/vols.xml", true);
    xhttp.send();
}


function producte2(){
    
        var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            lecturaXML2(this);
        }
    };
    xhttp.open("GET", "xml/hotels.xml", true);
    xhttp.send();
}
    
function producte3(){
    
        var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            lecturaXML3(this);
        }
    };
    xhttp.open("GET", "xml/restaurants.xml", true);
    xhttp.send();
}
    
function producte4(){
    
        var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            lecturaXML4(this);
        }
    };
    xhttp.open("GET", "xml/excursions.xml", true);
    xhttp.send();
}

/*************************************************************/

function lecturaXML1(xml) {

    var xmlDoc = xml.responseXML;
    var seccio = document.getElementsByTagName("section");

    for (var i = 0; i < xmlDoc.getElementsByTagName("vol").length; i++) {

        var id = xmlDoc.getElementsByTagName("id")[i].childNodes[0].nodeValue;


        var categoria = xmlDoc.getElementsByTagName("categoria")[i].childNodes[0].nodeValue;

        var nom = xmlDoc.getElementsByTagName("nom")[i].childNodes[0].nodeValue;

        var lloc = xmlDoc.getElementsByTagName("lloc")[i].childNodes[0].nodeValue;

        var preu = xmlDoc.getElementsByTagName("preu")[i].childNodes[0].nodeValue;

        var imatge = xmlDoc.getElementsByTagName("imatge")[i].childNodes[0].nodeValue;

        seccio[0].innerHTML += "<div class='itemsBox'>" +
            "<div class='item'>" +
            "<img class='foto' src='imatges/" + imatge + "'>" +
            "<div class='itemInfo'>" +
            "<p class='id'>" + id + "</p>" +
            "<p class='name'>" + nom + "</p>" +
            "<p class='lloc'>" + lloc + "</p>" +
            "<p class='categoria'>" + categoria + "</p>" +
            "<p class='preu'>" + preu + "</p>" +
            "</div>" +
            "<a href='#' title='add to cart' class='attToCart'>Afegir Carrito</a>" +
            "</div>" +
            "</div>";
    }
    var registre = document.getElementsByClassName("attToCart");

    for (i = 0; i < registre.length; i++) {

        registre[i].addEventListener("click", function (e) {

            e.preventDefault();

            imatge = e.target.parentElement.parentElement.getElementsByClassName("item")[0].getElementsByClassName("foto")[0].src;  

            iden = e.target.parentElement.getElementsByClassName("itemInfo")[0].getElementsByClassName("id")[0].textContent;

            nombre = e.target.parentElement.getElementsByClassName("itemInfo")[0].getElementsByClassName("name")[0].textContent;

            lugar = e.target.parentElement.getElementsByClassName("itemInfo")[0].getElementsByClassName("lloc")[0].textContent;

            cate = e.target.parentElement.getElementsByClassName("itemInfo")[0].getElementsByClassName("categoria")[0].textContent;

            precio = e.target.parentElement.getElementsByClassName("itemInfo")[0].getElementsByClassName("preu")[0].textContent;

            grabardades(imatge, iden, nombre, lugar, cate, precio);

        });
    }

}

function lecturaXML2(xml) {
    
    var xmlDoc = xml.responseXML;
    var seccio = document.getElementsByTagName("section");
    
    for (var i = 0; i < xmlDoc.getElementsByTagName("hotel").length; i++){
        
        var id = xmlDoc.getElementsByTagName("id")[i].childNodes[0].nodeValue;
        
        var categoria = xmlDoc.getElementsByTagName("categoria")[i].childNodes[0].nodeValue;
        
        var nom = xmlDoc.getElementsByTagName("nom")[i].childNodes[0].nodeValue;
        
        var lloc = xmlDoc.getElementsByTagName("lloc")[i].childNodes[0].nodeValue;
        
        var preu = xmlDoc.getElementsByTagName("preu")[i].childNodes[0].nodeValue;
        
        var imatge = xmlDoc.getElementsByTagName("imatge")[i].childNodes[0].nodeValue;
        
    seccio[1].innerHTML +="<div class='itemsBox'>" +
            "<div class='item'>" +
            "<img class='foto' src='imatges/" + imatge + "'>" +
            "<div class='itemInfo'>" +
            "<p class='id'>" + id + "</p>" +
            "<p class='name'>" + nom + "</p>" +
            "<p class='lloc'>" + lloc + "</p>" +
            "<p class='categoria'>" + categoria + "</p>" +
            "<p class='preu'>" + preu + "</p>" +
            "</div>" +
            "<a href='#' title='add to cart' class='attTo'>Afegir Carrito</a>" +
            "</div>" +
            "</div>";

    }
    
    var regis = document.getElementsByClassName("attTo");

    for (i = 0; i < regis.length; i++) {

        regis[i].addEventListener("click", function (e) {

            e.preventDefault();

            image = e.target.parentElement.parentElement.getElementsByClassName("item")[0].getElementsByClassName("foto")[0].src;  

            ident = e.target.parentElement.getElementsByClassName("itemInfo")[0].getElementsByClassName("id")[0].textContent;

            nom = e.target.parentElement.getElementsByClassName("itemInfo")[0].getElementsByClassName("name")[0].textContent;

            lloc = e.target.parentElement.getElementsByClassName("itemInfo")[0].getElementsByClassName("lloc")[0].textContent;

            categor = e.target.parentElement.getElementsByClassName("itemInfo")[0].getElementsByClassName("categoria")[0].textContent;

            price = e.target.parentElement.getElementsByClassName("itemInfo")[0].getElementsByClassName("preu")[0].textContent;

            grabardades(image, ident, nom, lloc, categor, price);

        });
    }

}


function lecturaXML3(xml) {
    
    var xmlDoc = xml.responseXML;
    var seccio = document.getElementsByTagName("section");
    
    for (var i = 0; i < xmlDoc.getElementsByTagName("restaurant").length; i++){
        
        var id = xmlDoc.getElementsByTagName("id")[i].childNodes[0].nodeValue;
        
        var categoria = xmlDoc.getElementsByTagName("categoria")[i].childNodes[0].nodeValue;
        
        var nom = xmlDoc.getElementsByTagName("nom")[i].childNodes[0].nodeValue;
        
        var lloc = xmlDoc.getElementsByTagName("lloc")[i].childNodes[0].nodeValue;
        
        var preu = xmlDoc.getElementsByTagName("preu")[i].childNodes[0].nodeValue;
        
        var imatge = xmlDoc.getElementsByTagName("imatge")[i].childNodes[0].nodeValue;
        
    seccio[2].innerHTML +="<div class='itemsBox'>" +
            "<div class='item'>" +
            "<img class='foto' src='imatges/" + imatge + "'>" +
            "<div class='itemInfo'>" +
            "<p class='id'>" + id + "</p>" +
            "<p class='name'>" + nom + "</p>" +
            "<p class='lloc'>" + lloc + "</p>" +
            "<p class='categoria'>" + categoria + "</p>" +
            "<p class='preu'>" + preu + "</p>" +
            "</div>" +
            "<a href='#' title='add to cart' class='att'>Afegir Carrito</a>" +
            "</div>" +
            "</div>";

    }
    
    var registre = document.getElementsByClassName("att");

    for (i = 0; i < registre.length; i++) {

        registre[i].addEventListener("click", function (e) {

            e.preventDefault();

            imatge = e.target.parentElement.parentElement.getElementsByClassName("item")[0].getElementsByClassName("foto")[0].src;  

            iden = e.target.parentElement.getElementsByClassName("itemInfo")[0].getElementsByClassName("id")[0].textContent;

            nombre = e.target.parentElement.getElementsByClassName("itemInfo")[0].getElementsByClassName("name")[0].textContent;

            lugar = e.target.parentElement.getElementsByClassName("itemInfo")[0].getElementsByClassName("lloc")[0].textContent;

            cate = e.target.parentElement.getElementsByClassName("itemInfo")[0].getElementsByClassName("categoria")[0].textContent;

            precio = e.target.parentElement.getElementsByClassName("itemInfo")[0].getElementsByClassName("preu")[0].textContent;

            grabardades(imatge, iden, nombre, lugar, cate, precio);

        });
    }

}

function lecturaXML4(xml) {
    
    var xmlDoc = xml.responseXML;
    var seccio = document.getElementsByTagName("section");
    
    for (var i = 0; i < xmlDoc.getElementsByTagName("excursio").length; i++){
        
        var id = xmlDoc.getElementsByTagName("id")[i].childNodes[0].nodeValue;
        
        var categoria = xmlDoc.getElementsByTagName("categoria")[i].childNodes[0].nodeValue;
        
        var nom = xmlDoc.getElementsByTagName("nom")[i].childNodes[0].nodeValue;
        
        var lloc = xmlDoc.getElementsByTagName("lloc")[i].childNodes[0].nodeValue;
        
        var preu = xmlDoc.getElementsByTagName("preu")[i].childNodes[0].nodeValue;
        
        var imatge = xmlDoc.getElementsByTagName("imatge")[i].childNodes[0].nodeValue;
        
    seccio[3].innerHTML +="<div class='itemsBox'>" +
            "<div class='item'>" +
            "<img class='foto' src='imatges/" + imatge + "'>" +
            "<div class='itemInfo'>" +
            "<p class='id'>" + id + "</p>" +
            "<p class='name'>" + nom + "</p>" +
            "<p class='lloc'>" + lloc + "</p>" +
            "<p class='categoria'>" + categoria + "</p>" +
            "<p class='preu'>" + preu + "</p>" +
            "</div>" +
            "<a href='#' title='add to cart' class='afeg'>Afegir Carrito</a>" +
            "</div>" +
            "</div>";

    }
    
    var registre = document.getElementsByClassName("afeg");

    for (i = 0; i < registre.length; i++) {

        registre[i].addEventListener("click", function (e) {

            e.preventDefault();

            imatge = e.target.parentElement.parentElement.getElementsByClassName("item")[0].getElementsByClassName("foto")[0].src;  

            iden = e.target.parentElement.getElementsByClassName("itemInfo")[0].getElementsByClassName("id")[0].textContent;

            nombre = e.target.parentElement.getElementsByClassName("itemInfo")[0].getElementsByClassName("name")[0].textContent;

            lugar = e.target.parentElement.getElementsByClassName("itemInfo")[0].getElementsByClassName("lloc")[0].textContent;

            cate = e.target.parentElement.getElementsByClassName("itemInfo")[0].getElementsByClassName("categoria")[0].textContent;

            precio = e.target.parentElement.getElementsByClassName("itemInfo")[0].getElementsByClassName("preu")[0].textContent;

            grabardades(imatge, iden, nombre, lugar, cate, precio);

        });
    }

}


function grabardades(imatge, iden, nombre, lugar, cate, precio) {

    var clau = localStorage.length + 1;

    myObj = {
        imatge: imatge,
        id: iden,
        nom: nombre,
        lloc: lugar,
        categoria: cate,
        preu: precio
    };

    myJSON = JSON.stringify(myObj);
    localStorage.setItem(clau, myJSON);
    
    mostrardades();
}

function mostrardades() {
    document.getElementsByClassName("contingut")[0].innerHTML="";
    
    for (i = 0; i < localStorage.length; i++) {


        clau = localStorage.key(i);
        text = localStorage.getItem(clau);
        obj = JSON.parse(text);
        

        document.getElementsByClassName("contingut")[0].innerHTML += "<div class='producte'><img src='" + obj.imatge + "' alt='imatge'>" +
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
