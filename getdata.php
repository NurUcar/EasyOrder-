<?php
require "connect.php";

$ValTc=$_POST["user_Tc"];


$mysql_qry= "SELECT * FROM users where Tc = '$ValTc'" ;


$result = $conn->query($mysql_qry);
	if ($result->num_rows >0) {
 
 
		while($row[] = $result->fetch_assoc()) {
 
			$tem = $row;
 
			$json = json_encode($tem);
 
		}
 
	} 
	else {
		echo "No Results Found.";
	}
	echo $json;
	
	$conn->close();
 
