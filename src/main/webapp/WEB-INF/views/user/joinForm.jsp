<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ include file="../layout/header.jsp" %>

<div class="container">

    
    
    
    <form>
      <div class="form-group">
        <label for="username">Username</label>
        <input type="text" class="form-control" placeholder="Enter username" id="username">
      </div>
      <div class="form-group">
        <label for="pwd">Password</label>
        <input type="password" class="form-control" placeholder="Enter password" id="pwd">
      </div>
      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" class="form-control" placeholder="Enter email" id="email">
      </div>
      

      <button id="btn-save" type="button" class="btn btn-primary">회원가입</button>
    </form>

    
</div>
    

<script src="_/js/user.js"></script>
<script>
    
let index = {
    //let _this = this;
    init: function(){
        $("#btn-save").on("click", ()=>{
            this.save();
        });
        /*$("#btn-save").on("click", function(){
            _this.save();
        });*/
        
        /*$("#btn-login").on("click", ()=>{
            this.login();
        });*/
    },
    
    save: function(){
        //alert("111");
        let params = {
            username: $("#username").val(),
            password: $("#pwd").val(),
            email: $("#email").val()
        };
        //console.log(data);
        
        $.ajax({
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(params),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp) {
            console.log(resp);
            if (resp.status === 500){
                alert("회원가입에 실패하였습니다.");
            }else{
                alert("회원가입이 완료되었습니다.");
                location.href = "/";
            }
            
        }).fail(function(err) {
            alert(JSON.stringify(err));
        });  
    },
    
    /*login: function(){
        let data = {
            username: $("#username").val(),
            password: $("#pwd").val()
        };
        
        $.ajax({
            type: "POST",
            url: "/api/user/login",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp) {
            alert("로그인이 완료되었습니다.");
            location.href = "/";
        }).fail(function(err) {
            alert(JSON.stringify(err));
        });  
    }*/
}

index.init();
</script> 

<%@ include file="../layout/footer.jsp" %>

