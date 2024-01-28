<?php
$hostname = 'localhost';
$database = 'id19695682_biodiversidad';
$username = 'id19695682_administrador';
$password = 'Biodivcontraseña123';

$conexion=mysqli_connect($hostname,$username,$password,$database);

if(!$conexion){
    echo "error conec";
}

$tit=$_POST['titulo'];
$hist=$_POST['historia'];
$area=$_POST['area'];
$perim=$_POST['perim'];
$calle=$_POST['calle'];
$col=$_POST['col'];
$muni=$_POST['municip'];
$estad=$_POST['estado'];
$lat=$_POST['latit'];
$long=$_POST['long'];
$colind=$_POST['colind'];
$recre=$_POST['recreo'];

$query = "INSERT INTO Parques (Nombre_parque, Historia, Area, Perimetro, Calle, Colonia, Municipio, Estado, Latitud, Longitud, Colindacias, Areas_recreo) VALUES ('$tit','$hist','$area','$perim','$calle','$col','$muni','$estad','$lat','$long','$colind','$recre')";
$resultado=mysqli_query($conexion,$query);

if($resultado){
    echo "succes";
}else{
    echo "datos error";
}

mysqli_close($conexion);
?>