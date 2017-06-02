<?php 

require "init.php";
$fcm_token = $_POST["fcm_token"];
$sql = "INSERT INTO fcm_info VALUES(null,'". $fcm_token ."')";
mysqli_query($connection, $sql);
mysqli_close($connection);

?>