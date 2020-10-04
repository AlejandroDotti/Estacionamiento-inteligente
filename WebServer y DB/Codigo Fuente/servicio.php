<?php

include_once 'lib/nusoap.php'; //libreria nusoap

include_once "funciones/database.php";
include_once "funciones/funciones.php";

$servicio = new soap_server();//instanciamos el servicio.
$ns = "urn:miserviciowsdl";//Establecemos un espacio de nombres para el servicio.
$servicio->configureWSDL("MiPrimerServicioWeb",$ns);//Configuramos el nombre del servicio. Nombre de la clase a intanciar
$servicio->schemaTargetNamespace = $ns;//Registramos el espacio de nombres.



/*
	Registramos ahora la funcion | metodo,
	Parametros recibidos por el servicio,
	Respuesta provista el servicio,
	El espacio de nombres.
*/	



$servicio->register("BuscarPatente", 
						array('pat' => 'xsd:string'),  
						array('return' => 'xsd:string'), 
						$ns 
					);
					


$servicio->register("LiberarParsela", 
						array('id' => 'xsd:string'), 
						array('return' => 'xsd:string'), 
						$ns 
					);
					
$servicio->register("InsertarVehiculo", 
						array('pat' => 'xsd:string', 'parsela' => 'xsd:string'), 
						array('return' => 'xsd:string'), 
						$ns 
					);

				
//Codificamos la funcion  | metodo registrada
function BuscarPatente($pat){


	
 

 $resultadoPat = db_query("select patente, telefono from auto where patente = '".$pat."'");
 
 if(count($resultadoPat)){
 
 $resultado= $resultadoPat[0]['patente'];
 
 
 return $resultado;
 }
 
 return "NO";
 
}

function InsertarVehiculo($pat,$parsela){
	
	$resultadoPat = db_query("select id_auto from auto where patente = '".$pat."'");
	$resultado= $resultadoPat[0]['id_auto'];
	
	if(count($resultadoPat)){
	
	db_query("INSERT INTO estaciona_en (auto_id, slot_id, f_inicial) VALUES ('".$resultado."','".$parsela."', CURRENT_TIMESTAMP)");
	
	
	
	$resultadoPat = db_query("SELECT id FROM estaciona_en ORDER BY id DESC LIMIT 1");
	$resultado= $resultadoPat[0]['id'];
	
	return $resultado; //devuelve el ultimo id, osea el que se le asigno a ese estacionamiento
	
	}
	return "ERROR";
	
}

function LiberarParsela($id){

	$consulta="UPDATE estaciona_en SET f_final = NOW() WHERE id = '".$id."'";
	$resultado=db_query($consulta);// realizamos la consulta a la BD
	//return "OK";
	 switch($resultado){//muestro errores de conexion o en la sintaxis de consulta si los hay. Sino muestro resultados
		case 0: return "OK";break;
		case "Error al conectar": return "Error"; break;
		case "Error al consultar": return "Error"; break;
		default:
			return("Error");
	
	} 

	}

	$HTTP_RAW_POST_DATA = isset($HTTP_RAW_POST_DATA) ? $HTTP_RAW_POST_DATA : '';//Evaluamos la info enviada por POST

$servicio->service(file_get_contents("php://input")); 
//Lanzamos finalmente el servicio. PHP >= v5.6.
//$servicio->service($HTTP_RAW_POST_DATA);//Lanzamos finalmente el servicio. PHP < v5.6
?>