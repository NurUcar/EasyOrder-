<?php
require "connect.php";

$ValTarih=$_POST["order_tarih"];
$ValSaat=$_POST["order_saat"];

$mysql_qry= "DELETE FROM orders WHERE Tarih='$ValTarih' AND Saat='$ValSaat'";


$result = $conn->query($mysql_qry);



 if ($conn->query($mysql_qry) === TRUE){
    echo "Randevu Silindi.";
} else{
    echo "Error:".$mysql_qry."<br>".$conn->error;
}
 
 $conn->close();

