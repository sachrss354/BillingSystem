var sel=document.getElementById('productname');
function select(s){
      
            
             console.log(s.value);
            console.log(s.value);
            $.ajax({
                url: "/api/product/mrp",
                type: "GET",
                data: { name: s.value},
                success: function(mrp) {
					alert('gdfg')
					console.log(mrp);
                    mrp2.innerHTML=mrp;
                    console.log(mrp2);
                  mrp2.style.display='block';
	              //deli.style.display='block';
	              sell.style.display='block';
	              amt.style.display='block';
	              qty.style.display='block';
                },
                error: function() {
                    $("#mrp").text("MRP not available.");
                }
            });
   };