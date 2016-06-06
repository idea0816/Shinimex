//ba_MoldControl 模具管理程式  Ver.1605

$(document).ready(
		function() {

			// 設定確認視窗中的按鈕文字
			alertify.set({
				labels : {
					ok : "Accept",
					cancel : "Deny"
				}
			});
			
			//Get Datas
			$("#getDatas").click(function(){
					var mjbh_choice = $("#mjbh_choice").val();
					var KHDH_choice = $("#KHDH_choice").val();
					
					//Get mjbh_choice or KHDH_choice DataList
					$.ajax({
						type : "get",
						url : "ba_MoldControl.do?mjbh_choice="
								+ mjbh_choice+"&KHDH_choice="+KHDH_choice,
						dataType : "json",
						success : function(SendData) {
							
							//Get Mold List
							$("#moldList td").parent().remove();/*加上parent()避免出現空白格*/
							$.each(SendData[0], function(key, value) {
								$("#moldList").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
										"<td class='td_center'><input type='radio' name='getMold' value='"+value.mjbh+"' /></td>" +
										"<td class='td_center'>"+value.mjbh+"</td>" +
										"<td>"+value.lbdh+"</td>" +
										"<td>"+value.kfjc+"</td>" +
										"<td>"+value.kfjc1+"</td>" +
										"<td>"+value.mjsl+"</td>" +
										"<td>"+value.gbbh+"</td>" +
												"</tr>");
							});
							
							
							//Get summjsl
							$("#sum_mjsl").val(SendData[1]);
							
							$("#mjbh_choice").val("");
							$("#KHDH_choice").val("");
						
							
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
			
			
			
			// Mold Data頁面取得radio選取值.並送資料到Mold List頁面
			$("input[name='getMold']").click(
					function() {
						var mjbh = $("input:radio[name='getMold']:checked")
								.val();
						$("#MJZL_mjbh").val(mjbh);
						
						//Get Mold Detail & Mold Size
						$.ajax({
							type : "post",
							url : "ba_MoldControl_getData.do?mjbh="
									+ encodeURI(encodeURI(mjbh)),
							dataType : "json",
							success : function(SendData) {
								//Get Mold Detail 
								$.each(SendData[0], function(key, value) {
									$("#lbzls_zwsm").val(value.lbdh);
									$("#kfzl_kfjc").val(value.kfjc);
									$("#kfzl_kfjc1").val(value.kfjc1);
									$("#MJZL_gbbh").val(value.gbbh);
									$("#MJZL_bz1").val(value.bz1);
									$("#MJZL_bz2").val(value.bz2);

								});
								//Get Mold Size
								$("#MoldsizeList td").parent().remove();/*加上parent()避免出現空白格*/
								$.each(SendData[1], function(key, value) {
									$("#MoldsizeList").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
											"<td class='td_center'><input id='getSize' name='getSize' value='"+value.lbdh+"' type='radio'></td>" +
											"<td class='td_left'>"+value.lbdh+"</td>" +
											"<td>"+value.mjsl+"</td>" +
													"</tr>");
								});
								
								$("#moldInOut").hide();
																
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
			
			//Mold Data頁面取得radio選取值.並送資料到Mold List頁面(append頁面需要用ON、程式碼是拷貝上面的)
			$("body").on("click", "#moldList", function(){//Dynamically add need to "ON"
				
					var mjbh = $("input:radio[name='getMold']:checked")
							.val();
					$("#MJZL_mjbh").val(mjbh);
					
					//Get Mold Detail & Mold Size
					$.ajax({
						type : "post",
						url : "ba_MoldControl_getData.do?mjbh="
								+ encodeURI(encodeURI(mjbh)),
						dataType : "json",
						success : function(SendData) {
							//Get Mold Detail 
							$.each(SendData[0], function(key, value) {
								$("#lbzls_zwsm").val(value.lbdh);
								$("#kfzl_kfjc").val(value.kfjc);
								$("#kfzl_kfjc1").val(value.kfjc1);
								$("#MJZL_gbbh").val(value.gbbh);
								$("#MJZL_bz1").val(value.bz1);
								$("#MJZL_bz2").val(value.bz2);

							});
							//Get Mold Size
							$("#MoldsizeList td").parent().remove();/*加上parent()避免出現空白格*/
							$.each(SendData[1], function(key, value) {
								$("#MoldsizeList").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
										"<td class='td_center'><input id='getSize' name='getSize' value='"+value.lbdh+"' type='radio'></td>" +
										"<td class='td_left'>"+value.lbdh+"</td>" +
										"<td>"+value.mjsl+"</td>" +
												"</tr>");
							});
							
							$("#moldInOut").hide();
															
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
			
			
			//MoldsizeList Radio Select
			$("body").on("click", "#MoldsizeList", function(){//Dynamically add need to "ON"
				$("#moldInOut").show(1000);
				
				var mjbh = $("input:radio[name='getMold']:checked").val();//mjbh
				var size = $("input:radio[name='getSize']:checked").val();//size
				//Get Mold InOut
				$.ajax({
					type : "post",
					url : "ba_MoldControl_sizeInOut.do?mjbh="+mjbh+"&size="+size,
					dataType : "json",
					success : function(moldlist_InOut) {
						//Get Mold InOut
						$("#moldlist_InOut td").parent().remove();/*加上parent()避免出現空白格*/
						var moldCode;
						var BZ;
						$.each(moldlist_InOut, function(key, value) {
							//判斷模具碼是否為NULL
							if(value.SH == "NULL"){
								moldCode = "";
							}else{
								moldCode = value.SH;
							}
							//判斷備註是否為NULL
							if(value.MSBZ == "NULL"){
								BZ = "";
							}else{
								BZ = value.MSBZ;
							}
							
							$("#moldlist_InOut").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
									"<td class='td_center'>"+value.DGLB+"</td>" +
									"<td class='td_center'>"+value.KSRQ+"</td>" +
									"<td class='td_center'>"+value.LYDH+"</td>" +
									"<td class='td_center'>"+moldCode+"</td>" +
									"<td>"+value.SL+"</td>" +
									"<td>"+value.SL1+"</td>" +
									"<td class='td_center'>"+BZ+"</td>" +
									"<td class='td_center'>"+value.KSDH+"</td>" +
											"</tr>");
						})
					
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
			
			
			// Insert Button - Add Mold InOut
			$("#Insert").click(function(){
				
				$("#insertInOut").show(1000);
			});
			
			
			// Cancel Button
			$("#Cancel").click(function(){
				$("#insertInOut").hide(1000);
				$("#DGLB").val("");
				$("#date_inOut").val("");
				$("#zszl").val("");
				$("#SL").val("");
				$("#BZ").val("");
			});
			
			
			//Get zszllist 廠商資料
			$("#date_inOut").change(
				function(){
					$.ajax({
						type : "post",
						url : "ba_MoldControl_getzszl.do",
						dataType : "json",
						success : function(data) {	
							$("#zszllist option").remove();
							$.each(data, function(key, value){
								$("#zszllist").append(
										"<option value='"+ value +"'>"
										+ key + "</option>"
								);
							});
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
			);
			
			
			// SAVE Button - add Data to KSYD
			$("#saveInOut").click(function(){
				//避免空值寫入
				var DGLB = $("#DGLB").val(); 
				var date_inOut = $("#date_inOut").val();
				var zszl = $("#zszl").val();
				var SL = $("#SL").val();
				
				if(DGLB == ""){
					alertify.success("異動類別不得為空!!!");
				}else if (date_inOut == ""){
					alertify.success("異動日期不得為空!!!");
				}else if(zszl == ""){
					alertify.success("廠商名稱不得為空!!!");
				}else if(SL == ""){
					alertify.success("數量不得為0!!!");
				}else{
					//Confirm
					alertify.confirm("Sure to INSERT this item ?", function(e){
						if(e){
							//OK! Send Insert Data
							
							var select = [];
							select[0] = $("input:radio[name='getMold']:checked").val();//mjbh
							select[1] = $("input:radio[name='getSize']:checked").val();//size
							select[2] = DGLB;
							select[3] = date_inOut;
							select[4] = zszl;
							select[5] = SL;
							if($("#BZ").val() == ""){
								select[6] = "NULL";
							}else{
								select[6] = $("#BZ").val();
							}
							select[7] = $("#moldCode").val();
							if($("#moldCode").val() == ""){
								select[7] = "NULL";
							}else{
								select[7] = $("#moldCode").val();
							}
							
							//Get Mold InOut
							$.ajax({
								type : "post",
								//url : "ba_MoldControl_sizeInOut.do?select="+ encodeURI(encodeURI(select)),
								//2016-06-01 何時用encodeURI、何時不用要搞清楚
								url : "ba_MoldControl_sizeInOut.do?select="+ select,
								dataType : "json",
								success : function(getMoldSize) {
									
									if(getMoldSize == "error"){
										//新增資料成功提示
										alertify.success("模具數量 < 0、請檢查資料是否正確!!或連絡 IT!!!");
									}else{
										//取得回傳數值並刷新SIZE Round頁面
										//*****資料寫入結束後、將新的資料再一次送入Size Round以便更新、這段程式碼是拷貝getMold.click的要注意*****
										//Get Mold Size
										$("#MoldsizeList td").parent().remove();/*加上parent()避免出現空白格*/
										$.each(getMoldSize, function(key, value) {
											$("#MoldsizeList").append("<tr onMouseOut='this.style.backgroundColor=\"\"' onMouseOver='this.style.backgroundColor=\"#B2C67F\"';>" +
													"<td class='td_center'><input id='getSize' name='getSize' value='"+value.lbdh+"' type='radio'></td>" +
													"<td class='td_left'>"+value.lbdh+"</td>" +
													"<td>"+value.mjsl+"</td>" +
															"</tr>");
										})
										
										$("#insertInOut").hide(1000);
										$("#moldInOut").hide(1000);
										$("#DGLB").val("");
										$("#date_inOut").val("");
										$("#zszl").val("");
										$("#SL").val("1");
										$("#BZ").val("");
										
										//新增資料成功提示
										alertify.success("Add Data Success");
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
						}else{
							//cancel!!
						}
					});
				}
				
			});

});