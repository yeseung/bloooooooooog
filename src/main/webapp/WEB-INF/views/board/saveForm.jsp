<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ include file="../layout/header.jsp" %>

<div class="container">

    <form>
      <div class="form-group">
        <input type="text" class="form-control" placeholder="Enter title" id="title">
      </div>
        
      <div class="form-group">
        <textarea class="form-control summernote" rows="5" id="content"></textarea>
      </div>
        
      <button id="btn-board-save" type="button" class="btn btn-primary">글쓰기완료</button>
    </form>

    
</div>
    

    
<script>
    

let index = {
    init: function(){
        $("#btn-board-save").on("click", ()=>{
            this.save();
        });
    },
    
    save: function(){
        //alert("111");
        let params = {
            title: $("#title").val(),
            content: $("#content").val()
        };
        console.log(params);
        
        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(params),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp) {
            alert("글쓰기가 완료되었습니다.");
            //alert(resp);
            console.log(resp);
            location.href = "/";
        }).fail(function(err) {
            alert(JSON.stringify(err));
        });
    }

}

index.init();
    

    
$('.summernote').summernote({
    placeholder: '',
    tabsize: 2,
    height: 300
});
</script>    

<%@ include file="../layout/footer.jsp" %>

