//pc_Produce 訂單生產入庫  Ver.1510

$(document).ready(function() {
	
	//Check Out
	$("#checkout").click(function(){
		alertify.success("CheckOut Success");
		$(this).hide(1000);
		$("#shipmentTable").hide(1000);
		//清掉掃描輸入框
		$("#enterData2").val("");
	});
	
	$(this).keydown(function(event){
		if(event.keyCode==13){  //如果按 enter
			var scanCode2 = $("#enterData2").val();
			if(scanCode2 == "checkout"){
				$("#checkout").click();
				$("#enterData2").val("");
			}else{
				
//				alert(scanCode);
				
				//只求顯示的作弊程式碼 以下
//				$("#shipmentTable td").parent().remove();/*加上parent()避免出現空白格*/
				$.ajax({
					type : "post",
					url : "pc_shipmentList.do?scanCode2="
							+ encodeURI(encodeURI(scanCode2)),
					dataType : "json",
					success : function(shipmentList2) {
//						alert(shipmentList.length);
						
						if(shipmentList2.length != 0){
							
							$("#checkout").show(1000);
							
							$.each(shipmentList2, function(key, value){
								
								$("#shipmentTable").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
										"<td>"+value.Xiexing+"</td>" +
										"<td>"+value.SheHao+"</td>" +
										"<td>"+value.Size+"</td>" +
										"<td>"+value.Qty+"</td>" +
												"</tr>");
							});
							
							$("#shipmentTable").show(1000);
							//清掉掃描輸入框
							$("#enterData2").val("");
							scanCode2 = "";
						}else{
							alertify.alert("問題條碼、禁止出庫！");
							//清掉掃描輸入框
							$("#enterData2").val("");
							scanCode2 = "";
						}
						
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
				//只求顯示的作弊程式碼 以上
			}
		}
	});
});