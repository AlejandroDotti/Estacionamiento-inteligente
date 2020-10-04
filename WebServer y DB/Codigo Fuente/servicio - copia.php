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
						array('pat' => 'xsd:string', 'parsela' => 'xsd:string', 'id' => 'xsd:string'), 
						array('return' => 'xsd:string'), 
						$ns 
					);

				
//Codificamos la funcion  | metodo registrada
function BuscarPatente($pat,$parsela,$id){


	
 

 $resultadoPat = db_query("select patente, telefono from auto where patente = '".$pat."'");
 
 if(count($resultadoPat)){
 
 $resultado= $resultadoPat[0]['patente'];
 
 
 return $resultado;
 }
 
 return "NO";
 
}

$HTTP_RAW_POST_DATA = isset($HTTP_RAW_POST_DATA) ? $HTTP_RAW_POST_DATA : '';//Evaluamos la info enviada por POST

$servicio->service(file_get_contents("php://input")); 
//Lanzamos finalmente el servicio. PHP >= v5.6.
//$servicio->service($HTTP_RAW_POST_DATA);//Lanzamos finalmente el servicio. PHP < v5.6
?>