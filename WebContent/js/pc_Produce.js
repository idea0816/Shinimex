//pc_Produce 訂單生產入庫  Ver.1510

$(document).ready(function() {
	
	//Scan Code Enter Event
	$(this).keydown(function(event){ 
		if(event.keyCode==13){  //如果按 enter     
			var scanCode = $("#enterData").val();
//			alert(scanCode);
			
			$.ajax({
				type : "post",
				url : "pc_produceList.do?scanCode="
						+ encodeURI(encodeURI(scanCode)),
				dataType : "json",
				success : function(produceList) {
//					alert(produceList.length);
					
					if(produceList.length != 0){
						$("#produceTable td").parent().remove();/*加上parent()避免出現空白格*/
						
						$.each(produceList, function(key, value){
							
							$("#produceTable").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
									"<td>"+value.Xiexing+"</td>" +
									"<td>"+value.SheHao+"</td>" +
									"<td>"+value.Size+"</td>" +
									"<td>"+value.Qty+"</td>" +
											"</tr>");
						});
						
						$("#produceTable").show(1000);
						//清掉掃描輸入框
						$("#enterData").val("");
						scanCode = "";
						
					}else{
						alertify.alert("問題條碼、禁止入庫！");
						//清掉掃描輸入框
						$("#enterData").val("");
						scanCode = "";
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
			
		}	
	});
});