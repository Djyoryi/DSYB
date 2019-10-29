<?php

$tema = $_COOKIE["temaElegido"];
include './funciones.php';
//Llamo a la funcion conectaBBDD para conectarme a mi BBDD
$mysqli = conectaBBDD();

//Ejecuto la consulta para traer los temas
$query = "SELECT * FROM preguntas WHERE tema = '$tema';";
$consulta = $mysqli->query($query);

//Comprobamos que hay resultado
$num_filas = $consulta->num_rows;

echo 'El tema: '.$tema.' tiene '.$num_filas.' preguntas';

$preguntas = array();

for ($i = 0; $i < $num_filas; $i++) {

    $resultado = $consulta->fetch_row();
    $preguntas[$i][0] = $resultado['numero'];
    $preguntas[$i][1] = $resultado['nivel'];
    $preguntas[$i][2] = $resultado['enunciado'];
    $preguntas[$i][3] = $resultado['r1'];
    $preguntas[$i][4] = $resultado['r2'];
    $preguntas[$i][5] = $resultado['r3'];
    $preguntas[$i][6] = $resultado['r4'];
    $preguntas[$i][7] = $resultado['correcta'];
    $preguntas[$i][8] = $resultado['tema'];
}


?>
