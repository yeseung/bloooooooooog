<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ include file="../layout/header.jsp" %>

<div class="container">

    
    
    
    <form>
      <input value="${principal.user.id}" id="id" type="hidden">
      <div class="form-group">
        <label for="username">Username</label>
        <input value="${principal.user.username}" type="text" class="form-control" placeholder="Enter username" id="username" readonly>
      </div>
        
      <c:if test="${empty principal.user.oauth}">
          <div class="form-group">
            <label for="pwd">Password</label>
            <input type="password" class="form-control" placeholder="Enter password" id="pwd">
          </div>
          <div class="form-group">
            <label for="email">Email</label>
            <input value="${principal.user.email}" type="email" class="form-control" placeholder="Enter email" id="email">
          </div>
      </c:if>
      
      
      

      <button id="btn-update" type="button" class="btn btn-primary">회원수정완료</button>
    </form>

    
</div>
    

<script>
    
let index = {
    init: function(){
        $("#btn-update").on("click", ()=>{
            this.update();
        });
    },
    
    update: function(){
        let params = {
            id: $("#id").val(),
            username: $("#username").val(),
            password: $("#pwd").val(),
            email: $("#email").val()
        };
        console.log(params);
        
        $.ajax({
            type: "PUT",
            url: "/user",
            data: JSON.stringify(params),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp) {
            alert("회원수정이 완료되었습니다.");
            //alert(resp);
            console.log(resp);
            location.href = "/";
        }).fail(function(err) {
            alert(JSON.stringify(err));
        });  
    },
    
}

index.init();
</script> 

<%@ include file="../layout/footer.jsp" %>

