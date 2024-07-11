var val=document.getElementById("val");
var deli=document.getElementById("del");
var add=document.getElementById("add");
var amt=document.getElementById('amt');
var rate=document.getElementById('rate');
var qty=document.getElementById('qty');
var sell=document.getElementById('sell');
var btn=document.getElementsByClassName("btn");
var mrp2 = document.getElementById('mrp');
var inv=document.getElementById("invoice");
var num=Number(val.innerHTML);
var i=2;
function getprint(){
        inv.innerHTML="BILL INVOICE AMOUNT";
        for(var l=0;l<btn.lenght;l++){
			val.innerHTML=i;
		}
        val.innerHTML='#';
         window.print();
        inv.innerHTML="BILLING INVOICE";
        num=1;
     }
function addrow(){
                  var v=$("#Trow").clone().appendTo("#Tbody");
                  $(v).find("input").val('');
                  $(v).removeClass("d-none");  
                  //console.log(v.length);      
                  val.innerHTML='#';
	              i++;
	              num++;
	              mrp2.style.display='none';
	             // deli.style.display='none';
	              sell.style.display='none';
	              amt.style.display='none';
	              qty.style.display='none';
}
function btndel(v){
$(v).parent().parent().remove();
GetTotal();
}
function clac(v){
	var index=$(v).parent().parent().index();
	var qty=document.getElementsByName("qty")[index].value;
	var rate=document.getElementsByName("rate")[index].value;
	var amt=qty*rate;
	document.getElementsByName("amt")[index].value=amt;
	GetTotal();
}
function GetTotal(){
	var sum=0;
	var amts=document.getElementsByName("amt");
	for(let index=0;index<amts.length;index++){
		var amt=amts[index].value;
		sum=+(sum)+ +(amt);
	}
	document.getElementById("total").value=sum;
	var gst=document.getElementById("gst").value;
	var net=+(sum)+ +(gst);
	document.getElementById("netamount").value=net;
}

