<?php
require "connect.php";

$Val_Tarih=$_POST["order_date"];

$response=array();

$mysql_qry= "SELECT * FROM orders WHERE Tarih = '$Val_Tarih' " ;


$result = $conn->query($mysql_qry);

if(mysqli_num_rows($result)>0){
	$response['success']=1;
	$orders=array();
	while($row=mysqli_fetch_assoc($result)){
		array_push($orders,$row);
	}
	$response['orders']=$orders;
	
}
else{
	$response['success']=0;
	$response['message']='randevu yok';
}
echo json_encode($response);
	
	$conn->close();