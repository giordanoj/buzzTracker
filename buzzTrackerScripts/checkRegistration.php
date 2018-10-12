<?php

$servername = "localhost:3306";
$username = "root";
$password = "password";
$db_name = "dt";
$table_name = "user";

$input_username = $_REQUEST["username"];

// Create connection
$conn = new mysqli($servername, $username, $password, $db_name);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT * FROM $table_name WHERE username = '$input_username'";

$result = $conn->query($sql);

if ($result->num_rows > 0) {
	echo "invalid";
} else {
    echo "valid";
}

$conn->close();

?>

