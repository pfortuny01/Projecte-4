<?php
 session_start();
 include('conexio_bd.php');
?>
<?php

$username = $_POST['usuari'];
$password = $_POST['password'];

$sql = "UPDATE clients SET usuari='$username', password='$password'";

if (mysqli_query($conexio, $sql) === TRUE) {
    
    header("location: perfil.php");
    
} else {
	echo "Error.<br>".$conexio->error;
}

?>