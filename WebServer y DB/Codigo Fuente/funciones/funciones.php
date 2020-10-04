<?
/*
	La funcion print_dump() recibe como argumentos la variable a presentar
	Puede recibir el modo a presentar raw (sin detalles) y dump (con detalles)
*/
	function print_dump($valor, $modo="raw"){
		switch($modo){
			case "raw":
				echo "<pre>";
				print_r($valor);
				echo "</pre>";
			break;
			case "dump":
				echo "<pre>";
				var_dump($valor);
				echo "</pre>";		
			break;
			default:
				echo "argumento incorrecto de la funcion <b>print_dump()</b>!!!<br>";
				echo "Uso <b>print_dump('objeto')</b> | <b>print_dump('objeto','modo')</b><br>";
				echo "<b>Objeto</b>: Es la variable a mostrar<br>";
				echo "<b>Modo</b>: Es un string que indica el detalle del objeto. raw | dump<br>";
		}
	}
?>