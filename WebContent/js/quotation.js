// $(document).ready(
// 		function() {

// 			$("#date1").change(
// 					function() {

$(document).ready(function() {

	$("#choiceSeason").change(
		function(){
			// alert($("#choiceSeason").val());
			// window.open("", "", "width=300, height=500");

			var dataofchoiceSeason = $("#choiceSeason").val()
			// alert(dataofchoiceSeason);

			if (dataofchoiceSeason == "Add New...") {
				alert("test OK");
				$("#newSeason").show(1000);
				$("#choiceSeason").attr("disabled", true);
			}else{
				alert(dataofchoiceSeason);
			};
	});
});

