<?php

$servername = "localhost:3306";
$username = "root";
$password = "password";
$db_name = "dt";
$table_name = "user";

$input_email= $_REQUEST["email"];
$input_password = $_REQUEST["password"];

// Create connection
$conn = new mysqli($servername, $username, $password, $db_name);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT * FROM $table_name WHERE email = '$input_email' AND password = '$input_password'";

$result = $conn->query($sql);

if ($result->num_rows == 1) {
    while($row = $result->fetch_assoc()) {
        echo "|Id:" . $row["id"] . ", Name:" . $row["name"] . ", Email:" . $row["email"] . ", Password:" . $row["password"] . ", Locked:" . $row["locked"] . ", Type:" . $row["type"] . ", Location:" . $row["location"] . ",";
    }
    echo "|";
} else {
    echo "user not found";
}

$conn->close();

?>

