<?php
$hostname = 'localhost';
$database = 'id19695682_biodiversidad';
$username = 'id19695682_administrador';
$password = 'Biodivcontraseña123';

$conexion=mysqli_connect($hostname,$username,$password,$database);

$result=array();
$result['datos']=array();
$query = "SELECT * FROM Administrador";
$responce = mysqli_query($conexion,$query);

while($row = mysqli_fetch_array($responce)){
    $index['id']=$row['0'];
    $index['nombre']=$row['1'];
    $index['passw']=$row['2'];

    array_push($result['datos'],$index);
}
$result ["succes"]="1";
echo json_encode($result);
?>