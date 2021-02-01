<?php

//Dades de la nostra BD (ubicació, usuari, contrassenya, nom)

    $servername = "localhost";
    $username = "root";
    $password = "pau";
    $dbname = "tragen";

//Creem la conexió amb la BD

    $conn = mysqli_connect($servername, $username, $password, $dbname);

//Comprovem la connexió

    if (!$conn) {
      die("Connection failed: " . mysqli_connect_error());
    }

//En aquest pas, li diem que les dades que entrem al formulari, les ha d'emmagatzemar cada una al su lloc
    
    $dni= $_POST ['dni'];
		$nom= $_POST ['nom'];
		$correu= $_POST ['correu'];
		$dataNaixament= $_POST ['dataNaixament'];
		$contrasenya= $_POST ['contrasenya'];

    $sql = "INSERT INTO clients (dni, nom, correu, dataNaixament, contrasenya)
    VALUES ('$dni', '$nom', '$correu', '$dataNaixament', '$contrasenya')";

//Una vegada fem clic al botó d'enviar, ens apareix un missatge que ens informa si s'ha fet correctament, ho hi ha agut algun error

    if (mysqli_query($conn, $sql)) {
      echo "Les dades s'han entrat correctamen a la BD";
    } else {
      echo "Error: " . $sql . "<br>" . mysqli_error($conn);
    }

    mysqli_close($conn);
 ?>