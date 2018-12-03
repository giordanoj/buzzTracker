<?php

$servername = "localhost:3306";
$username = "root";
$password = "password";
$db_name = "dt";
$table_name = "location";

$input_name = str_replace("+", " ", $_REQUEST["name"]);
$input_latitude = str_replace("+", " ", $_REQUEST["latitude"]);
$input_longitude = str_replace("+", " ", $_REQUEST["longitude"]);
$input_streetAddress = str_replace("+", " ", $_REQUEST["streetAddress"]);
$input_city = str_replace("+", " ", $_REQUEST["city"]);
$input_state = str_replace("+", " ", $_REQUEST["state"]);
$input_zip = str_replace("+", " ", $_REQUEST["zip"]);
$input_type = str_replace("+", " ", $_REQUEST["type"]);
$input_phone = str_replace("+", " ", $_REQUEST["phone"]);
$input_website = str_replace("+", " ", $_REQUEST["website"]);

$input_name = str_replace("%26", "&", $input_name);
$input_latitude = str_replace("%26", "&", $input_latitude);
$input_longitude = str_replace("%26", "&", $input_longitude);
$input_streetAddress = str_replace("%26", "&", $input_streetAddress);
$input_city = str_replace("%26", "&", $input_city);
$input_state = str_replace("%26", "&", $input_state);
$input_zip = str_replace("%26", "&", $input_zip);
$input_type = str_replace("%26", "&", $input_type);
$input_phone = str_replace("%26", "&", $input_phone);
$input_website = str_replace("%26", "&", $input_website);

// Create connection
$conn = new mysqli($servername, $username, $password, $db_name);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "INSERT INTO $table_name (name, latitude, longitude, streetAddress, city, state, zip, type, phone, website) VALUES ('$input_name','$input_latitude','$input_longitude','$input_streetAddress','$input_city','$input_state','$input_zip','$input_type','$input_phone','$input_website')";

$result = $conn->query($sql);

$sql2 = "SELECT * FROM $table_name WHERE name = '$input_name' AND latitude = '$input_latitude' AND longitude = '$input_longitude' AND streetAddress = '$input_streetAddress' AND city = '$input_city' AND state = '$input_state' AND zip = '$input_zip' AND type = '$input_type' AND phone = '$input_phone' AND website = '$input_website'";

$result2 = $conn->query($sql2);

if ($result2->num_rows > 0) {
	while($row = $result2->fetch_assoc()) {
        echo "Row with name " . $row["name"] . " successfully added!";
    }
} else {
    echo "Row with name " . $input_name . " not added.";
}

$conn->close();

?>

