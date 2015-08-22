//

//var valuelist = '';
//
//function getId(clickedId){
//
//	valuelist += clickedId.id + ",";
//	alert(valuelist)
//}



$(document).ready(function() {
	$("#ddrqUpdate").click(function() {

		var valuelist = '';
		$("input[type=checkbox]").each(function() {
			if (this.checked) {
				valuelist += $(this).id() + ",";
				alert(valuelist)
			}
		});

				alert("testalert")
		alert(valueList)
	});

});

