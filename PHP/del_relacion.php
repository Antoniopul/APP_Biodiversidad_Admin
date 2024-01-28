<?php   
$hostname = 'localhost';
$database = 'id19695682_biodiversidad';
$username = 'id19695682_administrador';
$password = 'Biodivcontraseña123';

$conexion=mysqli_connect($hostname,$username,$password,$database);
if(!$conexion){
    echo "error conec";
}

$idpar=$_POST["idpar"];
$iddiv=$_POST["iddiv"];

$query = "DELETE FROM Parque_Biodiv WHERE IDparq='$idpar' AND IDdiv='$iddiv'";
$resultado=mysqli_query($conexion,$query);

if($resultado){
    echo "succes";
}else{
    echo "error";
}

mysqli_close($conexion);
?>
