$(document).ready(function() {

	// Jump Year

	$("#getchoiceyear").click(function() {
		alertify.alert("Back To The Year Of " + choiceyear.value);
		$("#content").load("orderStatus.do?choiceyear=" + choiceyear.value);
	});
});