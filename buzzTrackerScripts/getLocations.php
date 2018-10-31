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
	while($row = $result->fetch_assoc()) {
        echo "|Id:" . $row["id"] . ", Name:" . $row["name"] . ", Latitude:" . $row["latitude"] . ", Longitude:" . $row["longitude"] . ", Street Address:" . $row["streetAddress"] . ", City:" . $row["city"] . ", State:" . $row["state"] . ", Zip:" . $row["zip"] . ", Type:" . $row["type"] . ", Phone:" . $row["phone"] . ", Website:" . $row["website"] . ",";
    }
    echo "|";
} else {
    echo "no locations found";
}

$conn->close();

?>

