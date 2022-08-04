let index = {
    //let _this = this;
    init: function(){
        $("#btn-save").on("click", ()=>{
            this.save();
        });
        /*$("#btn-save").on("click", function(){
            _this.save();
        });*/
    },
    
    save: function(){
        //alert("111");
        let data = {
            username: $("#username").val(),
            password: $("#pwd").val(),
            email: $("#email").val()
        };
        //console.log(data);
        
        $.ajax({
            type: "POST",
            url: "/api/user",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp) {
            alert("회원가입이 완료되었습니다.");
            alert(resp);
            //location.href = "/";
        }).fail(function(err) {
            alert(JSON.stringify(err));
        });
        
        
        /*$.ajax({
            type: "POST",
            url: "/api/user",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            alert("회원가입이 완료되었습니다.");
            location.href = "/";
            
        }).fail(function(err){
            alert(JSON.stringify(err));
        });*/
        
    }
}

index.init();