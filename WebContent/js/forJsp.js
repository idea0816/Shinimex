$(document).ready(function() {

	$("#pc_Progress").click(function() {
		$("#content").load("pc_Progress.jsp");
	});

	$("#f_list_for_Customs").click(function() {
		$("#content").load("f_list_for_Customs.jsp");
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

});