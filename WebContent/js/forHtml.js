$(document).ready(function() {

	$("#history").click(function() {
		$("#content").load("history.html");
	});

	$("#organization").click(function() {
		$("#content").load("organization.html");
	});

	$("#rf").click(function() {
		$("#content").load("rf.html");
	});

	$("#vp").click(function() {
		$("#content").load("vp.html");
	});

	$("#cu").click(function() {
		$("#content").load("cu.html");
	});

	// $("p").hide();

});