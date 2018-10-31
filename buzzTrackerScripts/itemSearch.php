<?php

$servername = "localhost:3306";
$username = "root";
$password = "password";
$db_name = "dt";
$table_name = "item";

$input_query = $_REQUEST["query"];
$input_location = $_REQUEST["location"];
$input_category = $_REQUEST["category"];

$input_query = str_replace("~1~", "''", $input_query );
$input_query = str_replace("~2~", "\"", $input_query );

// Create connection
$conn = new mysqli($servername, $username, $password, $db_name);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$i = 0;

$sql = "SELECT * FROM $table_name";

if (!($input_query == "~blank~")) {
    $sql = $sql . " WHERE name = '$input_query'";
    $i++;
}

if (!($input_location == "0")) {
    if ($i == 0) {
        $sql = $sql . " WHERE currentLocation = '$input_location'";
    } else {
        $sql = $sql . " AND currentLocation = '$input_location'";
    }
    $i++;
}

if (!($input_category == "All Categories")) {
    if ($i == 0) {
        $sql = $sql . " WHERE category = '$input_category'";
    } else {
        $sql = $sql . " AND category = '$input_category'";
    }
    $i++;
}

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

