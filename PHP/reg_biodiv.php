<?php
$hostname = 'localhost';
$database = 'id19695682_biodiversidad';
$username = 'id19695682_administrador';
$password = 'Biodivcontraseña123';

$conexion=mysqli_connect($hostname,$username,$password,$database);

if(!$conexion){
    echo "error conec";
}

$cat=$_POST['categoria'];
$tit=$_POST['titulo'];
$desc=$_POST['descri'];
$disgeo=$_POST['dis_geo'];
$estat=$_POST['estatus'];
$hist=$_POST['historia'];
$aut=$_POST['autores'];

$query = "INSERT INTO Bio_diversidad (Categoria, Nombre_especie, Descrip, Dis_geo, Estatus, Historia, Autores) VALUES ('$cat','$tit','$desc','$disgeo','$estat','$hist','$aut')";
$resultado=mysqli_query($conexion,$query);

if($resultado){
    echo "datos insertados";
}else{
    echo "datos error";
}

mysqli_close($conexion);
?>