$(document).ready(function() {

	$("#pc_Progress").click(function() {
		$("#content").load("pc_Progress.jsp");
	});

	$("#f_list_for_Customs").click(function() {
		$("#content").load("f_list_for_Customs.jsp");
	});

	$("#orderStatus").click(function() {
		$("#content").load("orderStatus.do");
	});

	$("#editFormula").click(function() {
		$("#content").load("editFormula.do");
	});

	$("#cu").click(function() {
		$("#content").load("cu.html");
	});

});