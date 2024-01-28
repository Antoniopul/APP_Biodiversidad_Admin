<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

$id=$_POST['id'];
$imagen= $_POST['foto'];
$nombre = $_POST['nombre'];
$autor= $_POST['autores'];

$hostname = 'localhost';
$database = 'id19695682_biodiversidad';
$username = 'id19695682_administrador';
$password = 'Biodivcontraseña123';

$conexion=mysqli_connect($hostname,$username,$password,$database);

$path = "uploads/$nombre.png";

$actualpath = "https://biodivparques.000webhostapp.com/$path";

$sql = "INSERT INTO Fotografias (ID_asociada,Categoria,Foto,NombreF,Autores) VALUES ('$id','Parque','$actualpath','$nombre','$autor')";

if(mysqli_query($conexion,$sql)){
    file_put_contents($path,base64_decode($imagen));
    echo "Subio imagen Correctamente";
}

mysqli_close($conexion);
}else{
    echo "Error";
}
?>