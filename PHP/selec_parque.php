<?php
$hostname = 'localhost';
$database = 'id19695682_biodiversidad';
$username = 'id19695682_administrador';
$password = 'Biodivcontraseña123';

$conexion=mysqli_connect($hostname,$username,$password,$database);

$result=array();
$result['datos']=array();
$query = "SELECT * FROM Parques";
$responce = mysqli_query($conexion,$query);

while($row = mysqli_fetch_array($responce)){
    $index['id']=$row['0'];
    $index['titulo']=$row['1'];
    $index['historia']=$row['2'];
    $index['area']=$row['3'];
    $index['perim']=$row['4'];
    $index['calle']=$row['5'];
    $index['col']=$row['6'];
    $index['municip']=$row['7'];
    $index['estado']=$row['8'];
    $index['latit']=$row['9'];
    $index['long']=$row['10'];
    $index['colind']=$row['11'];
    $index['recreo']=$row['12'];

    array_push($result['datos'],$index);
}
$result ["succes"]="1";
echo json_encode($result);
?>