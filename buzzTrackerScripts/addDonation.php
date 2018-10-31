<?php

$servername = "localhost:3306";
$username = "root";
$password = "password";
$db_name = "dt";
$table_name = "item";

$input_name = $_REQUEST["name"];
$input_description = $_REQUEST["description"];
$input_value = $_REQUEST["value"];
$input_category = $_REQUEST["category"];
$input_donationTime = $_REQUEST["donationTime"];
$input_enteredBy = $_REQUEST["enteredBy"];
$input_origin = $_REQUEST["origin"];
$input_currentLocation = $_REQUEST["currentLocation"];

$input_name = str_replace("~1~", "''", $input_name );
$input_name = str_replace("~2~", "\"", $input_name );
$input_description = str_replace("~1~", "''", $input_description );
$input_description = str_replace("~2~", "\"", $input_description );
$input_category = str_replace("~1~", "''", $input_category );
$input_category = str_replace("~2~", "\"", $input_category );

// Create connection
$conn = new mysqli($servername, $username, $password, $db_name);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "INSERT INTO $table_name (name, description, value, category, donationTime, enteredBy, origin, currentLocation) VALUES ('$input_name','$input_description','$input_value','$input_category','$input_donationTime','$input_enteredBy','$input_origin','$input_currentLocation')";

$result = $conn->query($sql);

$sql2 = "SELECT * FROM $table_name WHERE name = '$input_name' AND description = '$input_description' AND value = '$input_value' AND category = '$input_category' AND donationTime = '$input_donationTime' AND enteredBy = '$input_enteredBy' AND origin = '$input_origin' AND currentLocation = '$input_currentLocation'";

$result2 = $conn->query($sql2);

if ($result2->num_rows > 0) {
	while($row = $result2->fetch_assoc()) {
        echo $row["id"];
    }
} else {
    echo "item not added";
}

$conn->close();

?>

