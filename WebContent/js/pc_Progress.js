$(document).ready(
		function() {

			$("#date1").change(
					function() {
						var date1 = $("#date1").val();
						$.ajax({
							type : "post",
							url : "pc_Progressxiexing.do?date1="
									+ encodeURI(encodeURI(date1)),
							dataType : "json",
							success : function(data) {
								$("#xiexinglist option").remove();
								$.each(data, function(key, value) {
									$("#xiexinglist").append(
											"<option value='" + key + "'>"
													+ value + "</option");
								});
							},
							beforeSend : function() {
								$.blockUI();
							},
							complete : function() {
								$.unblockUI();
							},
							error : function() {
								alert("error")
							}
						});
					});

			$("#xiexing").change(
					function() {
						var date1 = $("#date1").val();
						var date2 = $("#date2").val();
						var xiexing = $("#xiexing").val();
						$.ajax({
							type : "post",
							url : "pc_ProgressKHDH.do?xiexing="
									+ encodeURI(encodeURI(xiexing)) + "&date1="
									+ encodeURI(encodeURI(date1)),
							dataType : "json",
							success : function(data) {
								$("#KHDHlist option").remove();
								$.each(data, function(key, value) {
									if(value == 'VN%')
										(alert(value));
									$("#KHDHlist").append(
											"<option value='" + value + "'>"
													+ key + "</option");
								});
							},
							beforeSend : function() {
								$.blockUI();
							},
							complete : function() {
								$.unblockUI();
							},
							error : function() {
								alert("error")
							}
						});
					});

			$("#getOrders")
					.click(
							function() {
								var date1 = $("#date1").val();
								var date2 = $("#date2").val();
								var xiexing = $("#xiexing").val();
								var KHDH = $("#KHDH").val();

//								alert(date1 + "," + date2 + "," + xiexing + ","
//										+ KHDH);

								$.ajax({
									type : "post",
									url : "pc_ProgressgetOrders.do?xiexing="
											+ encodeURI(encodeURI(xiexing))
											+ "&date1="
											+ encodeURI(encodeURI(date1))
											+ "&date2="
											+ encodeURI(encodeURI(date2))
											+ "&KHDH="
											+ encodeURI(encodeURI(KHDH)),
									dataType : "json",
									success : function(data) {
										// $("#KHDHlist option").remove();
										// $.each(data, function(key, value) {
										// $("#KHDHlist").append(
										// "<option value='" + value + "'>"
										// + key + "</option");
										// });
									},
									beforeSend : function() {
										$.blockUI();
									},
									complete : function() {
										$.unblockUI();
									},
									error : function() {
										alert("error")
									}
								});
							});

			// $("p").click(function() {
			// $(this).hide();
			//	});
		});