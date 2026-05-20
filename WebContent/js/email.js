/**
 *Email Javascript 
 */
window.addEventListener
(
    "load", 
    function()
    {
        document.getElementById("textbox1").style.visibility='hidden';
        document.getElementById("textbox2").style.visibility='hidden';
        document.getElementById('place1').disabled = true;
        document.getElementById('place2').disabled = true;
        
    }, false
);

function toggle(value){	
	
	if(value=='show1'){
		document.getElementById('textbox1').style.visibility='visible';
		document.getElementById('place1').disabled = true;
		
	}else if(value=='hide1'){
		document.getElementById('textbox1').style.visibility='hidden';
		document.getElementById('place1').disabled = false;
		
	}else if(value=='show2'){
		 document.getElementById('textbox2').style.visibility='visible';
		 document.getElementById('place2').disabled = true;
		 
	}else if(value=='hide2'){
		document.getElementById('textbox2').style.visibility='hidden';
		document.getElementById('place2').disabled = false;
		
	}
}


