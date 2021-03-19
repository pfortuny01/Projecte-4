<?php

include("conexio_bd.php");


    function grabar(){
        $id_prod = ;
        
        $num = 0;
        
        $busqueda = mysqli_query($conexio,"SELECT * FROM productes WHERE id_prod='$id_prod'");

        $num_filas = mysqli_num_rows($busqueda);

        if ($num_filas) {
         $num = 1; 
        }else {
         $dni = $_POST['dni'];
         $mail = $_POST['mail'];
         $user = $_POST['usuari'];
         $password = $_POST['password'];

            if(mysqli_query($conexio,"INSERT INTO clients (dni, mail, usuari, password) VALUES ('$dni', '$mail','$usuari', '$password')")){ 
                header("location: index_login.php?remarks=success");
             }else{
                 header("location: index_registre.php?remarks=error");	 
             }
        }    
    }
?>