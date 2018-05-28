<?php

	$userName = "";
	$password = "";

	$canShowAlert = FALSE;

	// prints JS code to show alert message (soluzione di ripiego)
	/*function showAlert() {

		echo "<script type=\"text/javascript\">

  	  	var showAlert = function () {

  			var form = document.getElementById(\"loginForm\");

  			var alert = document.createElement(\"div\");
  			alert.class = \"alert alert-danger\";
  			alert.role = \"alert\";
  			alert.innerHTML = \"Dati non validi\";

  			form.appendChild(document.createElement(\"br\"));
  			form.appendChild(alert);
  		}

  		showAlert();

  	  </script>";
  }*/

	/*$dbHost = "localhost";
	$dbUserName = "root";
	$dbPassword = "nicolas21";
	$dbName = "test";*/

	if($_SERVER["REQUEST_METHOD"] == "POST") {

		/*$conn = new mysqli($dbHost, $dbUserName, $dbPassword, $dbName);

		if($conn == NULL)
			die("cannot connect to db");*/

		$userName = trim($_POST["username"]);
		$password = trim($_POST["password"]);

		/*$query = "SELECT name FROM test WHERE username = '" . $userName . "' AND password = '" . $password . "'";

		if($conn->query($query) == FALSE)
			die("cannot perform query");

		if($conn->affected_rows != 0) {

			echo "dati validi!<br>";
		}
		else {

			//SHOW ALERT
		}*/

		if($userName == "root" && $password == "root") {

			echo "<h1> dati validi</h1>";

			$canShowAlert = FALSE;
		}
		else {

			$canShowAlert = TRUE;
		}
	}

?>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">
  </head>

  <body class="text-center">
    <form class="form-signin" id="loginForm" method="post">
      <!--<img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">-->
      <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
      <label for="usernameInput" class="sr-only">Email address</label>
      <input name="username" type="text" id="usernameInput" class="form-control" placeholder="Username" required autofocus>
      <label for="inputPassword" class="sr-only">Password</label>
      <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Remember me
        </label>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>

	  <!--<br><div class="alert alert-danger" role="alert"> Dati non validi </div>-->

	  <script type="text/javascript">

	  	var showAlert = function() {

			var form = document.getElementById("loginForm");

			var alert = document.createElement("div");
			alert.classList.add("alert");
			alert.classList.add("alert-danger");
			alert.role = "alert";
			alert.innerHTML = "Dati non validi";

			form.appendChild(document.createElement("br"));
			form.appendChild(alert);
		}

		// soluzione migliore, uso le variabili PHP dal JS
		if(<?php echo $canShowAlert; ?>)
			showAlert();

	</script>

    </form>
  </body>
</html>
