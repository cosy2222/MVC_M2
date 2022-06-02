var status = true;

$(document).ready(function(){
	$("#checkId").click(function(){
	  if($("#id").val()){
		var query = {id:$("#id").val()};
		
	    $.ajax({
	    	type:"post",
	    	url:"checkId.jsp",
	    	data:query,
	    	success:function(data){
	    		if(data == 1){
	    			alert("사용할 수 없는 아이디");
	    	    	$("#id").val("");
	    	     }else if(data == -1)
	    	  	    alert("사용할 수 있는 아이디");
	 	    }
	    });
	  }else{
		  alert("사용할 아이디를 입력");
		  $("#id").focus();
	  }
	});
	
	$("#process").click(function(){
	   checkIt(); 
	   
	   if(status){
		  var query = {u_id:$("#id").val(), 
				  u_pass:$("#passwd").val(),
			      u_name:$("#name").val(),
			      u_address:$("#address").val(),
			      u_tel:$("#tel").val(),
			      u_birthday:$("#birthday").val()
		};
			      
		  
		  $.ajax({
		      type:"post",
		      url:"registerInsert.jsp",
		      data:query,
		      success:function(data){
		    	  location.href = 'main.jsp';
		 	  }
		  });
	   }
	});
	
	$("#cancle").click(function(){
		location.href="main.jsp";
	});

 });

function checkIt() {
	status = true;
	
    if(!$("#id").val()) {
        alert("아이디를 입력하세요");
        $("#id").focus();
        status = false;
        return false;
    }
    
    if(!$("#passwd").val()) {
        alert("비밀번호를 입력하세요");
        $("#passwd").focus();
        status = false;
        return false;
    }
    if($("#passwd").val() != $("#repass").val()){
        alert("비밀번호를 동일하게 입력하세요");
        $("#repass").focus();
        status = false;
        return false;
    }
    
    if(!$("#name").val()) {
        alert("사용자 이름을 입력하세요");
        $("#name").focus();
        status = false;
        return false;
    }
    
    if(!$("#address").val()) {
        alert("주소를 입력하세요");
        $("#address").focus();
        status = false;
        return false;
    }
    
    if(!$("#tel").val()) {
        alert("전화번호를 입력하세요");
        $("#tel").focus();
        status = false;
        return false;
    }  
    
    if(!$("#birthday").val()) {
	    alert("생년월일를 입력하세요");
	    $("#birthday").focus();
	    status = false;
	    return false;
    }  
}