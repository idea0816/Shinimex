$(document).ready(function() {

	// $("table tr").click(function(){
	// alert(this.rowIndex);
	// });

//	$("table tr").click(function() {
//		$(this).css("background-color", "lightblue")
//	});
	
	
	
	//Formual頁面取得radio選取值.並送資料到List頁面
	$("input[name='getFormula']").click(function(){
//		 alert($("input:radio[name='getFormula']:checked").val());
		 var cldh = $("input:radio[name='getFormula']:checked").val();
		 $("#cldh").val(cldh);
			$.ajax({
				type : "post",
				url : "EditFormula_getcldh.do?cldh="
						+ encodeURI(encodeURI(cldh)),
				dataType : "json",
				success : function(data) {
//					$("#xiexinglist option").remove();
//					$.each(data, function(key, value) {
//						$("#xiexinglist").append(
//								"<option value='" + key + "'>"
//										+ value + "</option");
//					});
				},
				beforeSend : function() {
					$.blockUI();
				},
				complete : function() {
					$.unblockUI();
				},
				error : function() {
					$("#Formula_getcldh").append('<tr><td>column 1 value</td><td>column 2 value</td></tr>');
					alert("error");
				}
			});
	});
	
	//Delete Button
	$("#Delete").click(function(){
		if($("#Delete").val() == "Delete"){
			alert($("#Delete").val());
			$("#Delete").val("Confirm");
			$("#deleteCol").show(1000);
			$("#deleteCB").show(1000);
		}else{
			$("#deleteCol").hide(1000);
			$("#deleteCB").hide(1000);
			$("#Delete").val("Delete");
		}
	});
//	$("#Confirm").click(function(){
//		alert($("#Confirm").val());
//		
//	});
	
});