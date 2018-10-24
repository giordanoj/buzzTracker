<?php

$servername = "localhost:3306";
$username = "root";
$password = "password";
$db_name = "dt";
$table_name = "user";

$input_name = $_REQUEST["name"];
$input_email = $_REQUEST["email"];
$input_password = $_REQUEST["password"];
$input_type = $_REQUEST["accountType"];

// Create connection
$conn = new mysqli($servername, $username, $password, $db_name);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "INSERT INTO $table_name (name, email, password, type) VALUES ('$input_name','$input_email','$input_password','$input_type')";

$result = $conn->query($sql);

$sql2 = "SELECT * FROM $table_name WHERE name = '$input_name' AND email = '$input_email' AND password = '$input_password' AND type = '$input_type'";

$result2 = $conn->query($sql2);

if ($result2->num_rows > 0) {
	while($row = $result2->fetch_assoc()) {
        echo $row["id"];
    }
} else {
    echo "user not added";
}

$conn->close();

?>

