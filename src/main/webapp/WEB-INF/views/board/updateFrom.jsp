<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ include file="../layout/header.jsp" %>

<div class="container">

    <form>
      <input type="hidden" id="id" value="${board.id}" />
      <div class="form-group">
        <input value="${board.title}" type="text" class="form-control" placeholder="Enter title" id="title">
      </div>
        
      <div class="form-group">
        <textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
      </div>
        
      <button id="btn-update" type="button" class="btn btn-primary">글수정완료</button>
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
        
        let id = $("#id").val();
        
        let params = {
            title: $("#title").val(),
            content: $("#content").val()
        };
        console.log(params);
        
        $.ajax({
            type: "PUT",
            url: "/api/board/" + id,
            data: JSON.stringify(params),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp) {
            alert("글수정이 완료되었습니다.");
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

