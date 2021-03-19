<?php
include('conexio_bd.php');
session_start();
$user_check=$_SESSION['login_user'];
$ses_sql=mysqli_query($conexio,"SELECT usuari FROM clients where usuari='$user_check'");
$row=mysqli_fetch_array($ses_sql,MYSQLI_ASSOC);
$loggedin_session=$row['usuari'];
if(!isset($loggedin_session) || $loggedin_session==NULL) {
	echo "Go back";
	header("Location: index_login.php");
}else{
    header("Location: index.php");
}
?>