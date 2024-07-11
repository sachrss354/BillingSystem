var code1=document.getElementById("code-1");
var dis=document.getElementById("code1");
var code2=document.getElementById("code-2");
var dis2=document.getElementById("code2");
var code3=document.getElementById("code-3");
var dis3=document.getElementById("code3");
var code4=document.getElementById("code-4");
var dis4=document.getElementById("code4");
var display=0;
dis.addEventListener('click',function(){
	if(display==1){
		code1.style.display='none';
	display=0;
	}
	else{
		code1.style.display='block';
		code1.style.background='white';
		display=1;
	}
});
dis2.addEventListener('click',function(){
	if(display==1){
		code2.style.display='none';
	display=0;
	}
	else{
		code2.style.display='block';
		code2.style.background='white';
		display=1;
	}
});
dis3.addEventListener('click',function(){
	if(display==1){
		code3.style.display='none';
	display=0;
	}
	else{
		code3.style.display='block';
		code3.style.background='white';
		display=1;
	}
});
dis4.addEventListener('click',function(){
	if(display==1){
		code4.style.display='none';
	display=0;
	}
	else{
		code4.style.display='block';
		code4.style.background='white';
		display=1;
	}
});