<?php
$hostname = 'localhost';
$database = 'id19695682_biodiversidad';
$username = 'id19695682_administrador';
$password = 'Biodivcontraseña123';

$json=array();
if(isset($_GET["user"])){                               
    $user=$_GET["user"];
    
    $conexion=mysqli_connect($hostname,$username,$password,$database);
    $consulta="SELECT Pasword FROM Administrador WHERE Nombre='{$user}'";
    $resultado=mysqli_query($conexion,$consulta);

    while($row = mysqli_fetch_row($resultado)){
        $json[]=$row[0];
    }
    mysqli_close($conexion);
    echo json_encode($json);

}else{
    $resulta["success"]=0;
    $resulta["message"]='Ws no Retorna';
    $json['user'][]=$resulta;
    echo json_encode($json);
}
?>