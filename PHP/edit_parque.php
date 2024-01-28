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

$query = "UPDATE Parques SET Nombre_parque='$tit', Historia='$hist', Area='$area', Perimetro='$perim', Calle='$calle', Colonia='$col', Municipio='$muni', Estado='$estad', Latitud='$lat', Longitud='$long', Colindacias='$colind', Areas_recreo='$recre'  WHERE IDparque='$id'";
$resultado=mysqli_query($conexion,$query);

if($resultado){
    echo "succes";
}else{
    echo "error";
}

mysqli_close($conexion);
?>