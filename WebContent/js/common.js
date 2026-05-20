function validateSearch() {
	var jfYear = document.getElementById("ddlJFYear").value;
	var examPlace = document.getElementById("ddlExamPlace").selectedIndex;
	var returnValue = true;
	if (jfYear == 0) {
		document.getElementById('jfYearError').innerHTML = "JobFair年を選択してください。";
		returnValue = false;
	} else {
		document.getElementById('jfYearError').innerHTML = "";
	}

	if (examPlace == 0) {
		document.getElementById('examPlaceError').innerHTML = "受験場所を選択してください。";
		returnValue = false;
	} else {
		document.getElementById('examPlaceError').innerHTML = "";
	}

	var patt = /JF\d{4}[Y,M]{1}\d{4}/;
	var sID = document.getElementById("sExamID").value;
	if (sID.match(patt) || sID == "") {
		document.getElementById("sExamIDError").innerHTML = "";
	} else {
		document.getElementById("sExamIDError").innerHTML = "フォーマットが間違っています。 例.JF0000X0000 !";
		returnValue = false;
	}

	var eID = document.getElementById("eExamID").value;
	if (eID.match(patt) || eID == "") {
		document.getElementById("eExamIDError").innerHTML = "";
	} else {
		document.getElementById("eExamIDError").innerHTML = "フォーマットが間違っています。 例.JF0000X0000 !";
		returnValue = false;
	}
	return returnValue;
}

window.onload = function() {
	var tableCont = document.querySelector('#table-cont')
	function scrollHandle(e) {
		var scrollTop = this.scrollTop;
		this.querySelector('thead').style.transform = 'translateY(' + scrollTop
				+ 'px)';
	}
	tableCont.addEventListener('scroll', scrollHandle)
}

function InvalidText(textbox) {
	if (textbox.value.trim() == '') {
		textbox.setCustomValidity(textbox.id + ' を入力してください。');
	} else if (textbox.value > 100) {
		textbox.setCustomValidity('100以下の値を入力してください。');
	} else {
		textbox.setCustomValidity('');
	}
	return true;
}

function InvalidSelect(select) {
	if (select.value == '') {
		select.setCustomValidity(select.id + ' を選択してください。');
	} else {
		select.setCustomValidity('');
	}
	return true;
}

function ModalClose() {
	var model = document.getElementById("basicExampleModal");
	model.className = "modal fade";
	model.style.display = "none";
}

function restrictAlphabets(e) {
	var x = e.which || e.keycode;
	if ((x >= 48 && x <= 57) || x == 8 || (x >= 35 && x <= 40) || x == 46)
		return true;
	else
		return false;
}

function set_checked() {
	$('input[name=CheckedIDList]').attr('checked', false);
	var count = 0;
	var favorite = new Array();
	$("b").html("0");
}

function Invalid(textbox) {
	if (textbox.value.trim() == '') {
		textbox.setCustomValidity(textbox.id + ' を入力してください。');
	} else {
		textbox.setCustomValidity('');
	}
	return true;
}