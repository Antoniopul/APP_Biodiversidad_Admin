<?php
$hostname = 'localhost';
$database = 'id19695682_biodiversidad';
$username = 'id19695682_administrador';
$password = 'Biodivcontraseña123';

$conexion=mysqli_connect($hostname,$username,$password,$database);

$id=$_POST['id_parq'];

$result=array();
$result['datos']=array();
$query = "SELECT * FROM Bio_diversidad WHERE IDbiodiv NOT IN(SELECT IDdiv From Parque_Biodiv WHERE Parque_Biodiv.IDparq='$id')";
$responce = mysqli_query($conexion,$query);

while($row = mysqli_fetch_array($responce)){
    $index['id']=$row['0'];
    $index['categoria']=$row['1'];
    $index['titulo']=$row['2'];
    $index['descri']=$row['3'];
    $index['dis_geo']=$row['4'];
    $index['estatus']=$row['5'];
    $index['historia']=$row['6'];
    $index['autores']=$row['7'];

    array_push($result['datos'],$index);
}
$result ["succes"]="1";
echo json_encode($result);
?>