<?php
 require "connect.php";
 $user_tc=$_POST["user_tc"];
 $user_pass=$_POST["user_pass"];
 
 $mysql_qry="select * from users where Tc ='$user_tc' and Password = '$user_pass' ;";
 $result=mysqli_query($conn,$mysql_qry);
 if (mysqli_num_rows($result)>0){
     echo "login success";
 } else{
     echo "login not success";
 }