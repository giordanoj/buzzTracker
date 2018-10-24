<?php

$servername = "localhost:3306";
$username = "root";
$password = "password";
$db_name = "dt";
$table_name = "item";

$input_location = $_REQUEST["location"];

// Create connection
$conn = new mysqli($servername, $username, $password, $db_name);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT * FROM $table_name WHERE currentLocation = '$input_location'";

$result = $conn->query($sql);

if ($result->num_rows > 0) {
	while($row = $result->fetch_assoc()) {
        echo "|Id:" . $row["id"] . "~ Name:" . $row["name"] . "~ Description:" . $row["description"] . "~ Value:" . $row["value"] . "~ Category:" . $row["category"] . "~ Donation Time:" . $row["donationTime"] . "~ Sale Time:" . $row["saleTime"] . "~ Entered By:" . $row["enteredBy"] . "~ Sold By:" . $row["soldBy"] . "~ Origin:" . $row["origin"] . "~ Picture:" . $row["picture"] . "~ Comments:" . $row["comments"] . "~ Current Location:" . $row["currentLocation"] . "~";
    }
    echo "|";
} else {
    echo "no items found";
}

$conn->close();

?>

