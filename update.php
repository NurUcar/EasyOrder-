<?php
require "connect.php";

$Val_AdSoyad=$_POST["user_AdSoyad"];
$Val_Tc=$_POST["user_Tc"];
$Val_Tel=$_POST["user_Tel"];
$Val_Adres=$_POST["user_Adres"];
$Val_Password=$_POST["user_Password"];
$Val_AboneNo=$_POST["user_AboneNo"];



$mysql_qry = "UPDATE users SET AdSoyad = '$Val_AdSoyad',AboneNo = '$Val_AboneNo', Tel = '$Val_Tel', Adres = '$Val_Adres', Password = '$Val_Password' WHERE Tc = '$Val_Tc'";

 if ($conn->query($mysql_qry) === TRUE){
    echo "Update Successful";
} else{
    echo "Error:".$mysql_qry."<br>".$conn->error;
}
 
 $conn->close();
