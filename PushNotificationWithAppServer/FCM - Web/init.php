<?php 

$dbhost = "localhost";
$dbuser = "root";
$dbpass = "";
$dbname = "fcm_db";

$connection = mysqli_connect($dbhost, $dbuser, $dbpass, $dbname);
if($connection){
	echo "Connection Success...";
	
}else{
	echo "Connection Error...";
}

?>