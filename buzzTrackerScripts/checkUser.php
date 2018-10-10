<?php

$servername = "localhost:3306";
$username = "root";
$password = "password";
$db_name = "dt";
$table_name = "user";

$input_username = $_REQUEST["username"];
$input_password = $_REQUEST["password"];

// Create connection
$conn = new mysqli($servername, $username, $password, $db_name);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT * FROM $table_name WHERE username = '$input_username' AND password = '$input_password'";

$result = $conn->query($sql);

if ($result->num_rows == 1) {
    while($row = $result->fetch_assoc()) {
        echo $row["id"] . "_" . $row["name"] . "_" . $row["accountType"];
    }
} else {
    echo "user not found";
}

$conn->close();

?>

