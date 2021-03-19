<?php
    include("conexio_bd.php");
?>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/css/index_registre.css">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Tragen(TravelAgency)</title>
</head>

<body>
       
        <div class="navbar">
          <a class="active" href="index.html"><i class="fa fa-fw fa-home"></i> Home</a>
          <a href="index_registre.php"><i class="fa fa-fw fa-user-plus"></i> Registre</a>
          <a href="index_login.php"><i class="fa fa-fw fa-user"></i> Login</a>
        </div>
        
        <header>
            <h1>TRAGEN</h1>
            <h2>-Travel Agency-</h2>
        </header>
        <?php

            function test_input($data){
                $data = trim($data);
                $data = stripslashes($data);
                $data = htmlspecialchars($data);
                return $data;
            }
    
            //definir variables
            $dni = $mail = $usuari = $password = "";
            $dniError = $mailError = $usuariError = $passwordError = "";
            //si el metodo form va amb post, marcar camps obligatoris i comprovar
            if ($_SERVER["REQUEST_METHOD"] == "POST"){

                if (empty($_POST["dni"])){
                    $dniError = "DNI obligatori";
                }else{
                    $dni = test_input($_POST["dni"]);
                }
                
                if (empty($_POST["mail"])){
                    $mailError = "E-mail obligatori";
                }else{
                    $mail = test_input($_POST["mail"]);
                    if (!filter_var($mail, FILTER_VALIDATE_EMAIL)){
                        $mailError = "Format incorrecte en el E-mail";
                    }
                }
                
                if (empty($_POST["usuari"])){
                    $usuariError = "Usuari obligatori";
                }else{
                    $usuari = test_input($_POST["usuari"]);
                }

                if (empty($_POST["password"])){
                    $passwordError = "Password obligatori";
                }else{
                    $password = test_input($_POST["password"]);
                }
            }
                  
        ?> 
        <div class="foto">
                <form method="POST" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
                      <h3>NO ETS USUARI? REGISTRA'T</h3>
                       <?php
                            $remarks = isset($_GET['remarks']) ? $_GET['remarks'] : '';
                            if ($remarks=='success') {
                            echo ' <div id="reg-head" class="headrg">&nbsp;&nbsp;Registrat amb èxit</div> ';
                            }
                            if ($remarks=='failed') {
                            echo ' <div id="reg-head-fail" class="headrg">&nbsp;&nbsp;Registre incorrecte. Aquest usuari ja està registrat</div> ';
                            }
                            if ($remarks=='error') {
                            echo ' <div id="reg-head-fail" class="headrg">&nbsp;&nbsp;Registre erroni! </div> ';
                            }
                        ?>
                        <p class="avis"><span class="error">&nbsp;&nbsp;* camps obligatoris</span></p>
                         <div class="caixa">

                              <p class="tipo">
                                  <label for="dni">DNI:</label><br>
                                  <input type="text" name="dni" id="dni" placeholders="dni" required>
                                  <span class="error">* <?php echo $dniError ?></span>
                              </p><br>

                              <p class="tipo">
                                  <label for="mail">Mail:</label><br>
                                  <input type="text" name="mail" id="mail" placeholders="gmail" required>
                                  <span class="error">* <?php echo $mailError; ?></span>
                              </p><br>
                              
                              <p class="tipo">
                                  <label for="usuari">Usuari:</label><br>
                                  <input type="text" name="usuari" id="usuari" placeholders="usuari" required>
                                  <span class="error">* <?php echo $usuariError; ?></span>
                              </p><br>

                              <p class="tipo">
                                  <label for="password">Password:</label><br>
                                  <input type="text" name="password" id="password" placeholders="password" required>
                                  <span class="error">* <?php echo $usuariError; ?></span>
                              </p><br>

                              <p class="tipos">
                                 <input type="submit" name="btn1" value="Registra't">
                                 <input type="reset" value="Cancelar">
                              </p>
                        </div>
            
            <?php
                if($_SERVER["REQUEST_METHOD"] == "POST") {
                    $username = $_POST['usuari'];

                    $result = mysqli_query($conexio,"SELECT * FROM clients WHERE usuari='$username'");

                    $num_rows = mysqli_num_rows($result);

                    if ($num_rows) {
                     header("location: index_registre.php?remarks=failed"); 
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