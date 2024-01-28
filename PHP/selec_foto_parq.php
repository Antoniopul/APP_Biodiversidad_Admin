<?php
$hostname = 'localhost';
$database = 'id19695682_biodiversidad';
$username = 'id19695682_administrador';
$password = 'Biodivcontraseña123';

$conexion=mysqli_connect($hostname,$username,$password,$database);

$id=$_POST['id_parq'];
// $id='2';

$result=array();
$result['datos']=array();
$query = "SELECT * FROM Fotografias WHERE ID_asociada='$id' AND Categoria='Parque' ";
$responce = mysqli_query($conexion,$query);

while($row = mysqli_fetch_array($responce)){
    $index['id']=$row['0'];
    $index['id_asos']=$row['1'];
    $index['cat']=$row['2'];
    $index['foto']=$row['3'];
    $index['nombre']=$row['4'];
    $index['autor']=$row['5'];

    array_push($result['datos'],$index);
}
$result ["succes"]="1";
echo json_encode($result);
?>