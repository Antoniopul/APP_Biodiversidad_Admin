<?php
$hostname = 'localhost';
$database = 'id19695682_biodiversidad';
$username = 'id19695682_administrador';
$password = 'Biodivcontraseña123';

$conexion=mysqli_connect($hostname,$username,$password,$database);

if(!$conexion){
    echo "error conec";
}

$idpar=$_POST['id_parq'];
$idbio=$_POST['id_div'];

// $idpar='1';
// $idbio='1';

$query = "INSERT INTO Parque_Biodiv(IDparq, IDdiv) VALUES ('$idpar','$idbio')";
$resultado=mysqli_query($conexion,$query);

if($resultado){
    echo "datos insertados";
}else{
    echo "datos error";
}

mysqli_close($conexion);
?>