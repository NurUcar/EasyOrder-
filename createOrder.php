<?php
require "connect.php";
$Val_Tc=$_POST["user_Tc"];
$Val_Tarih=$_POST["order_Tarih"];
$Val_Saat=$_POST["order_Saat"];

$mysql_qry="insert into orders (Tc,Tarih,Saat) values ('$Val_Tc','$Val_Tarih','$Val_Saat')";

if ($conn->query($mysql_qry) === TRUE){
    echo "Insert Successful";
} else{
    echo "Error:".$mysql_qry."<br>".$conn->error;
}
$conn->close();

