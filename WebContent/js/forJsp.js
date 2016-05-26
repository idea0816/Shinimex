$(document).ready(function() {

	//Basic
	$("#moldControl").click(function() {
		$("#content").load("ba_MoldControl.do");
	});

	//Sales
	$("#orderStatus").click(function() {
		$("#content").load("orderStatus.do");
	});
	/*
	$("#orderStatus").click(function() {
		$.ajax({
			//type : "get",
			//url:"",
			
			beforeSend : function() {
				$.blockUI();
			},
			complete : function() {
				$.unblockUI();
			},
			success :$("#content").load("orderStatus.do"),
			error : function() {
				alert("error");
			}
		});
		
	});
	 */
	$("#quotation").click(function() {
		$("#content").load("quotation.html");
	});

	$("#sales_Datalist").click(function() {
		$("#content").load("sales_Datalist.jsp");
	});

	//Roll-Mixing Machine
	/*
	$("#editFormula").click(function() {
		$("#content").load("editFormula.do");
	});
	 */
	$("#editFormula").click(function() {
		$.ajax({
			type : "get",
			url : "",
			success : $("#content").load("editFormula.do"),

			beforeSend : function() {
				$.blockUI();
			},
			complete : function() {
				$.unblockUI();
			},
			error : function() {
				alert("error");
			}
		});

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

});