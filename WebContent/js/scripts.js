
function readURL(input) {
    var ext = input.value.split('.');
    ext = ext[ext.length-1].toLowerCase();      
    var arrayExtensions = ['jpg' , 'jpeg', 'png'];
    if (arrayExtensions.lastIndexOf(ext) == -1) {
        alert('無効なファイルタイプ。');
        input.value = '';
        }
   else {

       if(input.files && input.files[0]){
         var reader=new FileReader();
         reader.onload=function(e){
           $('#blah')
               .attr('src',e.target.result)
             };
             reader.readAsDataURL(input.files[0]);
           }
       if(input.files[0].size/1024/1024 > 1) {
           alert("ファイルサイズが 1 MBを超えています。");
           input.value = '';
		   return false;
           }
       document.getElementById('e1').innerHTML="";
    }
}

function searchValidate() {
	var returnValue;
	if (document.form.ddlJobFairYear.selectedIndex == "0") {
		document.getElementById('demo').innerHTML = "JobFair年を選択してください。";
		returnValue = false;
	} else {
		document.getElementById('demo').innerHTML = "";
		returnValue = true;
	}
	if (document.form.ddlExamPlace.selectedIndex == "0") {
		document.getElementById('examPlace').innerHTML = "受験場所を選択してください。";
		returnValue = false;
	} else {
		document.getElementById('examPlace').innerHTML = "";
		returnValue = true;
	}
	return returnValue;
}
function JFYearValidate() {
	var returnValue;
	if (document.form.ddlJobFairYear.selectedIndex == "0") {
		document.getElementById("btnSearch").disabled=true;
		document.getElementById('demo').innerHTML = "JobFair年を選択してください。";
		returnValue= false;
	} else {
		if(document.form.ddlExamPlace.selectedIndex == "0"){
			document.getElementById("btnSearch").disabled=true;
			document.getElementById('demo').innerHTML = "";
			returnValue= false;
		}
		else{
		    document.getElementById("btnSearch").disabled=false;
		    document.getElementById('demo').innerHTML = "";
		    returnValue= true;
		}
	}
	return returnValue;
}
function examPlaceValidate() {
	var returnValue;
	if (document.form.ddlExamPlace.selectedIndex == "0") {
		document.getElementById("btnSearch").disabled=true;
		document.getElementById('examPlace').innerHTML = "受験場所を選択してください。";
		returnValue= false;
	} else {
		if (document.form.ddlJobFairYear.selectedIndex == "0") {
			document.getElementById("btnSearch").disabled=true;
			document.getElementById('examPlace').innerHTML = "";
			returnValue= false;
		}
		else {
			document.getElementById("btnSearch").disabled=false;
			document.getElementById('examPlace').innerHTML = "";
			returnValue= true;
		}
	}
	return returnValue;
}

function myStartExamID() {
	var str = document.getElementById("sExamID").value;
	var eid=document.getElementById("eExamID").value;
	var patt = /JF\d{4}[Y,M]{1}\d{4}/;
	var result = str.match(patt);
	if (str.match(patt)) {
		document.getElementById("btnSearch").disabled=false;
		document.getElementById("startExamID").innerHTML = "";
		return true;
	} else {
		if(str == ""){
			if(eid != ""){
				document.getElementById("btnSearch").disabled=true;
				document.getElementById("startExamID").innerHTML = "";
				return false;
			}
			else{
			    document.getElementById("btnSearch").disabled=false;
			    document.getElementById("startExamID").innerHTML = "";
			    return true;
			}
		}else{
			document.getElementById("btnSearch").disabled=true;
			document.getElementById("startExamID").innerHTML = "フォーマットが間違っています。 例.JF0000X0000 !";
			return false;
		}
	}
}
function myEndExamID() {
	var str = document.getElementById("eExamID").value;
	var sid=document.getElementById("sExamID").value;
	var patt = /JF\d{4}[Y,M]{1}\d{4}/;
	var result = str.match(patt);
	if (str.match(patt)) {
		document.getElementById("endExamID").innerHTML = "";
		return true;
	} else {
		if(str==""){
			if(sid != ""){
				document.getElementById("btnSearch").disabled=true;
				document.getElementById("endExamID").innerHTML = "";
				return false;
			}
			else{
				document.getElementById("btnSearch").disabled=false;
				document.getElementById("endExamID").innerHTML = "";
				return true;
			}
		}
		else {
			document.getElementById("btnSearch").disabled=true;
			document.getElementById("endExamID").innerHTML = "フォーマットが間違っています。 例.JF0000X0000 !";
			return false;
		}
	}
}

function myChangeNRC(){
	   var nrcOne = $('#parent_city_selection').val();
	   var nrcTwo = $('#citizen_selection').val().trim();
	   var nrcThree = $('#nrcType').val();
	   var nrcFour =  $('#checkNRC').val();
	   var appnrc = nrcOne+'/'+nrcTwo+'('+nrcThree+')'+nrcFour;
	   if( /^([0-9]{1,2})\/[a-zA-Z\s-, ]+\([N,P,E,Y]\)[0-9]{6}$/.test(appnrc) == true){
	 	  $.ajax({
			    type : "GET",
			    url : "<s:url action='checkNRC'/>",
				data : {
					app_nrc : appnrc,
				    btn : $('#submitBtn').val()
				},
				success : function(responseText) {
					if (responseText == "OK"){
						 $('#checkNRC').val(null); 
						alert("個人番号がデータベースに重複しています。")
						document.getElementById("cssNRC").style.border="3px solid #a94442";	
						document.getElementById("checkNRC").style.borderColor="#a94442";
						document.getElementById("checkNRCErr").innerHTML = "個人番号をもう一度選択してください。";
						
					} else {
						document.getElementById("checkNRC").style.borderColor="#3c763d";
	                    document.getElementById("cssNRC").style.border="none";	                    
	                    document.getElementById("checkNRCErr").innerHTML = " ";
					}				
				}
		     });	       	  
	   }	
}

function myChangeNRCOne(){
	   var nrcOne = $('#parent_city_selection').val();
	   var nrcTwo = $('#citizen_selection').val().trim();
	   var nrcThree = $('#nrcType').val();
	   var nrcFour =  $('#checkNRC').val();
	   var appnrc = nrcOne+'/'+nrcTwo+'('+nrcThree+')'+nrcFour;
	   if( /^([0-9]{1,2})\/[a-zA-Z\s-, ]+\([N,P,E,Y]\)[0-9]{6}$/.test(appnrc) == true){
	 	  $.ajax({
			    type : "GET",
			    url : "<s:url action='checkNRC'/>",
				data : {
					app_nrc : appnrc,
				    btn : $('#submitBtn').val()
				},
				success : function(responseText) {
					if (responseText == "OK"){
						 $('#checkNRC').val(null); 
						alert("個人番号がデータベースに重複しています。")
						document.getElementById("cssNRC").style.border="3px solid #a94442";	
						document.getElementById("checkNRC").style.borderColor="#a94442";
						document.getElementById("checkNRCErr").innerHTML = "個人番号をもう一度選択してください。";
						
					} else {
						document.getElementById("checkNRC").style.borderColor="#3c763d";
	                    document.getElementById("cssNRC").style.border="none";	                    
	                    document.getElementById("checkNRCErr").innerHTML = " ";
					}				
				}
		     });	       	  
	   }	
}
