<?php
    session_start();
    include('conexio_bd.php');
?>
<?php
    error_reporting(E_ALL);

    ini_set('display_errors', '1');

    $sql = "SELECT * FROM clients WHERE usuari='".$_SESSION['login_user']."'";

    $result = mysqli_query($conexio, $sql);

    $num_rows = mysqli_num_rows($result);

    if ($num_rows > 0){
        
        $row = mysqli_fetch_assoc($result);

        $dni = $row["dni"];
        $mail = $row["mail"];
        $username = $row["usuari"];
        $password = $row["password"];
    }
?>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/css/editar.css">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="java/localstorage.js"></script>
    <title>Tragen(TravelAgency)</title>
</head>

<body>
    <div class="navbar">
          <a class="active" href="index.php"><i class="fa fa-fw fa-home"></i> Home</a>
          <a href="perfil.php"><i class="fa fa-fw fa-user"></i><?php echo $_SESSION['login_user'] ?></a>
          <a class="active" href="productes.php"><i class="fa fa-fw fa-shopping-bag"></i> Productes</a>
          <div class="despegable">
              <a class="active1" href="#"><i class="fa fa-fw fa-shopping-cart"></i> Carrito</a>
              <div class="carrito">
                  <div class="titols">
                      <p class="title">Imatge</p>
                      <p class="title">ID_Prod</p>
                      <p class="title">Nom producte</p>
                      <p class="title">Preu</p>
                      <p class="title">Eliminar</p>
                      <p></p>
                  </div>
                  <div class="contingut">

                  </div>  
                  <div class="buidar">
                      <button class="vaciar" onclick="netejarcarrito();">Netejar carrito</button>
                      <button class="vaciar">Comprar</button>
                  </div>
              </div>
          </div>             
          <a href="logout.php"><i class="fa fa-fw fa-power-off"></i>Exit </a>
        </div>
        
    <header>
        <h1>TRAGEN</h1>
        <h2>-Travel Agency-</h2>
    </header>
    
    <div class="bloc">
    <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>" method="post">

      <div class="formulari"> 
        <h3>Usuari:&nbsp;" <?php echo $_SESSION['login_user'] ?> "</h3><br>
        <p class="espe">*Dades que pot canviar*  </p><br>
        <label for="usuari">Usuari: </label><br>
        <input type="text" name="usuari" value="<?php echo $username ?>"><br>
        <br>
        <label for="Contrasenya">Password: </label><br>
        <input type="text" name="password" value="<?php echo $password ?>"><br>
        <br>
        <input id="boto" type ="submit" value="Realitzar canvis">

      </div>
        <?php
            if($_SERVER["REQUEST_METHOD"] == "POST") {
                
                $username = $_POST['usuari'];
                $password = $_POST['password'];

                $sql = "UPDATE clients SET usuari='$username', password='$password' WHERE usuari='".$_SESSION['login_user']."'";
                
                $accio = mysqli_query($conexio, $sql);

                if (mysqli_query($conexio, $sql)) {
                    $_SESSION['login_user'] = $username;
                    header("location: perfil.php");
                } else {
                    echo "Error.<br>".$conexio->error;
                }
            }
         ?>
     </form>
     </div>
    
    <footer class="footer">
           <div class="uno">
               <h5>Gràcies per la vostra visita, esperem tornar-te a veure aviat!</h5>
               <h6>Correu: tragen@gmail.com</h6>
               <div class="ATC"><h6>At. Client: 973 39 23 45</h6></div>
           </div>
           <div class="dos">
               <section>
                <h4>Nostres polítiques</h4>
                <ul class="alt">
                    <li><a href="#" data-toggle="modal" data-target="#exampleModal">Avís Legal</a></li>
                    <li><a href="#" data-toggle="modal" data-target="#exampleModal2">Política de Cookies</a></li>
                    <li><a href="#" data-toggle="modal" data-target="#exampleModal3">Política de Privacitat</a></li>
                </ul>
              </section>
              <section>
                <h4>Segueix-nos</h4>
                <ul class="plain">
                    <li><a href="#"><i class="fa fa-twitter">&nbsp;</i>Twitter</a></li>
                    <li><a href="#"><i class="fa fa-facebook">&nbsp;</i>Facebook</a></li>
                    <li><a href="#"><i class="fa fa-instagram">&nbsp;</i>Instagram</a></li>
                </ul>
              </section>
           </div>
           <div class="tres">
               <h6>-Travel Agency-</h6>
           </div> 
        </footer>
    
</body>
</html>
