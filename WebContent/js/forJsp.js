$(document).ready(function() {
	
	
	//Sales
	$("#orderStatus").click(function() {
		$("#content").load("orderStatus.do");
	});
	
	$("#sales_Datalist").click(function() {
		$("#content").load("sales_Datalist.jsp");
	});
	
	
	//Product Control
	$("#pc_Progress").click(function() {
		$("#content").load("pc_Progress.jsp");
	});

	$("#f_list_for_Customs").click(function() {
		$("#content").load("f_list_for_Customs.jsp");
	});
	
	$("#shipMent").click(function() {
		$("#content").load("pc_Shipment.jsp");
	});
	
	$("#product").click(function() {
		$("#content").load("pc_Produce.jsp");
	});
	

	
	//Roll-Mixing Machine
	$("#editFormula").click(function() {
		$("#content").load("editFormula.do");
	});

	

});