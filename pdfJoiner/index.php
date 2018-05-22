<?php

	require_once '../file_upload_scripts/fileUloadUtils.php';

	$baseDir = "C:\uploads\\";

	if($_SEVER["REQUEST_METHOD"] == "POST") {

		foreach ($_FILES["pdfs"]["error"] as $key => $error) {

			if($error == UPLOAD_ERR_OK) {

				$completePath = $baseDir . basename($_FILES["pdfs"]["name"][$key]);

				move_uploaded_file($_FILES["pdfs"]["tmp_name"][$key], $completePath);
			}
			else {
				throw new FileUploadException($key);
			}
		}
	}

?>

<html>

<head>
	<meta charset="utf-8">
	<title> multipleFileUploader </title>
</head>

<body lang="it">

	<h1> carica più file </h1>

	<form id = "inputForm" method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" enctype="multipart/form-data">

		Carica più file:<br/>
		<input type="file" name="pdfs[]" /><br/>
		<input type="file" name="pdfs[]" /><br/>
		<input type="file" name="pdfs[]" /><br/>
		<input type="file" name="pdfs[]" /><br/><br/>

		<input type="submit" value="carica file" /><br/><br/>

		<button onclick="updateInputs()"> Add... </button>

	</form>

	<script type="text/javascript">

		console.log("ciao");

		var updateInputs = function() {

			console.log("called");

			var inputForm = document.getElementById("inputForm");

			var newInput = document.createElement("input");
			//newInput.class = "inputItem";
			newInput.type = "file";
			newInput.name = "pdfs[]";

			//inputForm.appendChild(newInput);
			inputForm.appendChild(newInput);
			inputForm.appendChild(document.createElement("br"));
		}

	</script>
</body>

</html>
