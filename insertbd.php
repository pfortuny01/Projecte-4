<?php
session_start();
include("conexio_bd.php");

$result = mysqli_query($conexio,"INSERT INTO pack VALUES   WHERE usuari='$username'");

$num_rows = mysqli_num_rows($result);


?>