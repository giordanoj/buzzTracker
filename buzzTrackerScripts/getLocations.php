<?php

$servername = "localhost:3306";
$username = "root";
$password = "password";
$db_name = "dt";
$table_name = "location";

// Create connection
$conn = new mysqli($servername, $username, $password, $db_name);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT * FROM $table_name";

$result = $conn->query($sql);

if ($result->num_rows > 0) {
    $a = 1;
	while($row = $result->fetch_assoc()) {
        if ($a > 1) {
            echo "~" . $row["id"] . "_" . $row["name"] . "_" . $row["latitude"] . "_" . $row["longitude"] . "_" . $row["streetAddress"] . "_" . $row["city"] . "_" . $row["state"] . "_" . $row["zip"] . "_" . $row["type"] . "_" . $row["phone"] . "_" . $row["website"];
        } else {
            echo $row["id"] . "_" . $row["name"] . "_" . $row["latitude"] . "_" . $row["longitude"] . "_" . $row["streetAddress"] . "_" . $row["city"] . "_" . $row["state"] . "_" . $row["zip"] . "_" . $row["type"] . "_" . $row["phone"] . "_" . $row["website"];
        }
       $a++;
    }
} else {
    echo "no locations found";
}

$conn->close();

?>

