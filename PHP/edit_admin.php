<?php   
$hostname = 'localhost';
$database = 'id19695682_biodiversidad';
$username = 'id19695682_administrador';
$password = 'Biodivcontraseña123';

$conexion=mysqli_connect($hostname,$username,$password,$database);
if(!$conexion){
    echo "error conec";
}

// $id="15";
// $user="holap";
// $pass="holap";

$id=$_POST['id'];
$user=$_POST['user'];
$pass=$_POST['pass'];

$query = "UPDATE Administrador SET Nombre='$user', Pasword='$pass' WHERE IDadmin='$id'";
$resultado=mysqli_query($conexion,$query);

if($resultado){
    echo "succes";
}else{
    echo "error";
}

mysqli_close($conexion);
?>
