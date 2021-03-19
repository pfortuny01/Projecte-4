<?php
$servidor = "localhost";
$username = "root";
$password = "";
$base_dades = "agencia";

$conexio = new mysqli($servidor, $username, $password, $base_dades);

if ($conexio->connect_errno){
	die("Hi ha un errror en la connexió al servidor");
}

?>