//forJsp-jsp_Frame Menu Load  Ver.1505
//2016-06-06 新增點選之後先鎖定畫面

$(document).ready(function() {

	/**Basic Start**/
	//moldControl
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
	
	/**Basic End**/

	/**Sales Start**/
	//orderStatus
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
	
	//quotation
	$("#quotation").click(function() {
		$("#content").load("quotation.html");
	});
	
	//sales_Datalist
	$("#sales_Datalist").click(function() {
		$("#content").load("sales_Datalist.jsp");
	});
	
	/**Sales End**/

	/**Roll-Mixing Machine Start**/
	//editFormula
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
	
	//Month Analysis
	$("#rmMonthAnalysis").click(function() {
		//$("#content").load("rm_MonthAnalysis.jsp");
		$.ajax({
			type : "GET",
			url:"rm_MonthAnalysis.jsp",
			dataType : "html",
			success : function() {
				$("#content").load("rmMonthAnalysis.do");
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
	
	//Month Analysis
	
	/**Roll-Mixing Machine End**/

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