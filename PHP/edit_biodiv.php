<?php   
$hostname = 'localhost';
$database = 'id19695682_biodiversidad';
$username = 'id19695682_administrador';
$password = 'Biodivcontraseña123';

$conexion=mysqli_connect($hostname,$username,$password,$database);
if(!$conexion){
    echo "error conec";
}

$id=$_POST['id'];
$cat=$_POST['categoria'];
$tit=$_POST['titulo'];
$decrip=$_POST['descri'];
$geo=$_POST['dis_geo'];
$stat=$_POST['estatus'];
$hist=$_POST['historia'];
$aut=$_POST['autores'];

$query = "UPDATE Bio_diversidad SET Categoria='$cat', Nombre_especie='$tit', Descrip='$decrip', Dis_geo='$geo', Estatus='$stat', Historia='$hist', Autores='$aut' WHERE IDbiodiv='$id'";
$resultado=mysqli_query($conexion,$query);

if($resultado){
    echo "succes";
}else{
    echo "error";
}

mysqli_close($conexion);
?>