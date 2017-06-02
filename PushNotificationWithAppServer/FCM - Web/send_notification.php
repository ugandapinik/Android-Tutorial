<?php 

require "init.php";
$message = $_POST["message"];
$title = $_POST["title"];
$path_to_from = "https://fcm.googleapis.com/fcm/send";
$server_key = "AIzaSyAC5zkVBOmcVVe46xdEPDQkY-3Xg3iNg9s";
$sql = "SELECT fcm_token from fcm_info";
$result = mysqli_query($connection, $sql);
$row = mysqli_fetch_row($result);
$key = $row[0];


$headers = array(
	'Authorization:key=' .$server_key,
	'Content-Type:application/json'
);

$fields = array(
	'to' =>$key,
	'notification' => array('title' => $title, 'body' => $message)

);

print_r($fields);

$payload = json_encode($fields);

$curl_session = curl_init();
curl_setopt($curl_session, CURLOPT_URL, $path_to_from);
curl_setopt($curl_session, CURLOPT_POST, true);
curl_setopt($curl_session, CURLOPT_HTTPHEADER, $headers);
curl_setopt($curl_session, CURLOPT_RETURNTRANSFER, true);
curl_setopt($curl_session, CURLOPT_SSL_VERIFYPEER, false);
curl_setopt($curl_session, CURLOPT_IPRESOLVE, CURL_IPRESOLVE_V4);
curl_setopt($curl_session, CURLOPT_POSTFIELDS, $payload);


$result = curl_exec($curl_session);
curl_close($curl_session);

?>

