$(document).ready(function() {

	// checkbox checkALL
	$("#checkAll").click(function() {
		$('input:checkbox').prop('checked', this.checked);
	});
	
	// 設定確認視窗中的按鈕文字
	alertify.set({
		labels : {
			ok : "Accept",
			cancel : "Deny"
		}
	});

	// delete button
	$("#delete").click(function() {
		var checkBox = [];
		$('input:checkbox:checked[name="checkBox"]').each(function(i) {
			checkBox[i] = this.value;
		});
		
		// Confirm視窗
		alertify.confirm("Sure to DELETE Selected items ?", function(e) {
			if (e) {
				// OK! 送出刪除資料
				$.ajax({
					type : "POST",
					url : "deleteData.do",
					data : "orderStatusDel=" + checkBox
				// type: "POST",
				//dataType : "html",
				// url: "deleteData.do?orderStatusDel="
				// + encodeURI(encodeURI(checkBox))
				}).done(function(msg) {
					// alert( "Data Saved: " + msg );
				});
			} else {
				// cancel!!
				alert("Cancel")
			}
		});

	});

	// update button
	$("#update").click(function() {
		
		var updateDate = [];
		$('input[name="updateDate"]').each(function(i) {
			if(this.value.length = 10){
				updateDate[i] = this.value;
			}			
		});
		
		 var aaa = '';
		 $.each(updateDate, function(index, value) {
			 aaa += index + ' : ' + value + '\n'
		 });
		 alert(aaa)

	});

});

// 警告視窗
// alertify.alert("Message")

// 陣列長度
// alert(checkBox.length)

// 取出陣列中的數值
// var aaa = '';
// $.each(checkBox, function(index, value) {
// aaa += index + ' : ' + value + '\n'
// });
