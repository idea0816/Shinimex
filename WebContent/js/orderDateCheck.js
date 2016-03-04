$(document).ready(function() {

	// checkbox checkALL
	$("#checkAll").click(function() {
		$('input:checkbox').prop('checked', this.checked);
	});
	
	alertify.alert("出現此畫面代表訂單資料中的日期格式有問題<br>請修正日期或刪除資料<br>也可以連絡 IT 協助解決這個問題!");

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
				// OK! 送出Delete資料
				$.ajax({
					type : "POST",
					url : "deleteData.do",
					data : "orderStatusDel=" + checkBox
				}).done(function(msg) {
					$("#content").load("orderStatus.do");
				});
			} else {
				// cancel!!
				//alert("Cancel")
			}
		});

	});

	// update button
	$("#update").click(function() {
		var updateDate = [];
		var x = 0;// 改寫 date 的序列號、避免有空值傳出
		$('input[name="updateDate"]').each(function(i) {
			if (this.value != '') {
				//this.id -> 加入資料名稱及key,傳到SQL才有條件可以找. +":"+ 是為了分別出條件和日期
				updateDate[x] = this.id+":"+this.value;
				x += 1;
			} else {
			}
		});

		// Confirm視窗
		alertify.confirm("Sure to UPDATE items ?", function(e) {
			if (e) {
				// OK! 送出Update資料
				$.ajax({
					type : "POST",
					url : "deleteData.do",
					data : "orderStatusUpdate=" + updateDate
				}).done(function(msg) {
					$("#content").load("orderStatus.do");
				});
			} else {
				// cancel!!
				//alert("Cancel")
			}
		});

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
