<?php
/*DB_QUERY realiza consultas al servidor de BD. Devuelve tabla en caso de operacion select. ID en caso de operacion insert.
	0 si un update fue exitoso. 1 caso contrario*/
	include_once ("./funciones/config.php");//debo indicar el directorio relativo al working directory!!
	function db_query($sql) {
		$con=mysqli_connect(SQL_SERVER,SQL_USER,SQL_PASS,SQL_DB);//or return "Error de conexion al servidor de BD";
		// Check connection
		if (mysqli_connect_errno()) {
		  //return "Fallo la conexion al servidor MySQL: " . mysqli_connect_error();
		  return "Error al conectar";
		}
		
		mysqli_set_charset( $con, 'utf8');/*Establecemos el juego de caracteres para el intercambio con el server*/
		$fecha=date('Y-m-d H:i:s');
		file_put_contents('mysql.log', "$fecha\n$sql\n", FILE_APPEND);  		//Creamos un log de querys
		
		$res=mysqli_query($con,$sql);//or die( "Fallo al realizar la consulta!!!! Consultar el log del servidor");
		
		if (mysqli_error($con)) {
			//die($sql);
		  //return "Fallo la consulta al servidor MySQL: " . mysqli_error($con);
			return "Error al consultar";
		}
		if(gettype($res) == 'boolean') {
			if(stripos('' . $sql,'INSERT') >=0 )
				$res=mysqli_insert_id($con);
			/* Si la consulta fue INSERT, devolvemos el ID autogenerado. Sino devolvemos 0 o 1 si hubo erron en operacion*/
			mysqli_close($con);//
			return $res;
		
		} else {
			$arr=array();
			while($row=mysqli_fetch_assoc($res)){
				$arr[]=$row;
			}
			mysqli_close($con);
			return $arr;
		}
	}
?>