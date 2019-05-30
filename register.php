<?php
require "connect.php";
$Val_AdSoyad=$_POST["user_AdSoyad"];
$Val_Tc=$_POST["user_Tc"];
$Val_Tel=$_POST["user_Tel"];
$Val_Adres=$_POST["user_Adres"];
$Val_Password=$_POST["user_Password"];

$mysql_qry="insert into users (AdSoyad,Tc,Tel,Adres,Password) values ('$Val_AdSoyad','$Val_Tc','$Val_Tel','$Val_Adres','$Val_Password')";

if ($conn->query($mysql_qry) === TRUE){
    echo "Insert Successful";
} else{
    echo "Error:".$mysql_qry."<br>".$conn->error;
}
$conn->close();

