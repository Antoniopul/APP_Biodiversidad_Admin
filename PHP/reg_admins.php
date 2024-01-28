<?php
$hostname = 'localhost';
$database = 'id19695682_biodiversidad';
$username = 'id19695682_administrador';
$password = 'Biodivcontraseña123';

$conexion=mysqli_connect($hostname,$username,$password,$database);

if(!$conexion){
    echo "error conec";
}

$usu=$_POST['user'];
$pass=$_POST['pass'];

//$usu="tony";
//$pass="pass987";

$query = "INSERT INTO Administrador(Nombre, Pasword) VALUES ('$usu','$pass')";
$resultado=mysqli_query($conexion,$query);

if($resultado){
    echo "datos insertados";
}else{
    echo "datos error";
}

mysqli_close($conexion);
?>