//forJsp-jsp_Frame Menu Load  Ver.1505
//2016-06-06 新增點選之後先鎖定畫面

$(document).ready(function() {

	//Basic
//	$("#moldControl").click(function() {
//		$("#content").load("ba_MoldControl.do");
//	});
	
	$("#moldControl").click(function() {
		$.ajax({
			type : "GET",
			url:"ba_MoldControl.jsp",
			dataType : "html",
			success : function() {
				$("#content").load("ba_MoldControl.do");
			},
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
	

	//Sales
	$("#orderStatus").click(function() {
		$.ajax({
			type : "GET",
			url:"orderStatus.jsp",
			dataType : "html",
			success : function() {
				$("#content").load("orderStatus.do");
			},
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
	$("#quotation").click(function() {
		$("#content").load("quotation.html");
	});

	$("#sales_Datalist").click(function() {
		$("#content").load("sales_Datalist.jsp");
	});

	//Roll-Mixing Machine
	$("#editFormula").click(function() {
		$.ajax({
			type : "GET",
			url:"editFormula.jsp",
			dataType : "html",
			success : function() {
				$("#content").load("editFormula.do");
			},
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