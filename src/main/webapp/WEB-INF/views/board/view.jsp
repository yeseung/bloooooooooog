<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layout/header.jsp" %>

<div class="container">

  <button type="button" class="btn btn-secondary" onclick="history.back();">돌아가기</button>
    
  <c:if test="${board.user.id == principal.user.id}">
    <button onclick="location.href='/board/${board.id}/updateForm'" type="button" id="btn-update" class="btn btn-warning">수정</button>
    <button type="button" id="btn-delete" class="btn btn-danger">삭제</button>  
  </c:if>
    
  <br><br>
    
     
    
  <div>
    글번호 : <span id="id"><i>${board.id}</i></span> /
    작성자 : <span>${board.user.username}</span> / 
    조회수 : <span>${board.count}</span> 
  </div>
    
  <br>
    
  <div>
    <h3>${board.title}</h3>
  </div>
  <hr />    
  <div>
    <div>${board.content}</div>
  </div>
    
    
    
  <hr />
    
  <div class="card">
      <form>
          <input type="hidden" id="userId" value="${principal.user.id}" />
          <input type="hidden" id="boardId" value="${board.id}" />
          <div class="card-body">
              <textarea id="reply-content" class="form-control" rows="2"></textarea>
          </div>
          <div class="card-footer">
              <button id="btn-reply-save" type="button" class="btn btn-primary">등록</button>
          </div>
      </form>
  </div>
  <br>
    
    
    
    
  <div class="card">
      <div class="card-header">댓글 리스트</div>
      <ul id="reply-box" class="list-group">
          <c:forEach var="reply" items="${board.replys}">
              <li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
                  <div>${reply.content}</div>
                  <div class="d-flex">
                      <div class="font-italic">작성자 : ${reply.user.username} &nbsp;</div>
                      <c:if test="${reply.user.id eq principal.user.id}">
                        <button onclick="index.replyDelete(${board.id}, ${reply.id})" class="badge">삭제</button>
                      </c:if>
                  </div>
              </li>
          </c:forEach>
      </ul>
  </div> 
    
    
  
</div>
    

    
<script>
    

let index = {
    init: function(){
        $("#btn-delete").on("click", ()=>{
            this.deleteById();
        });
        
        $("#btn-reply-save").on("click", ()=>{
            this.replySave();
        });
    },
    
    deleteById: function(){
        let id = $("#id").text();
        
        if (window.confirm("삭제하시겠습니까?")) {
            $.ajax({
                type: "DELETE",
                url: "/api/board/" + id,
                dataType: "json"
            }).done(function(resp) {
                //alert("삭제가 완료되었습니다.");
                //alert(resp);
                console.log(resp);
                location.href = "/";
            }).fail(function(err) {
                alert(JSON.stringify(err));
            });
        }else{
            return false;
        }
    },
    
    replySave: function(){
        let params = {
            userId: $("#userId").val(),
            boardId: $("#boardId").val(),
            content: $("#reply-content").val()
        };
        
        //let boardId = $("#boardId").val();
        
        console.log(params);
        
        $.ajax({
            type: "POST",
            url: "/api/board/" + params.boardId + "/reply",
            data: JSON.stringify(params),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp) {
            alert("댓글작성이 완료되었습니다.");
            //alert(resp);
            console.log(resp);
            location.href = "/board/" + params.boardId;
        }).fail(function(err) {
            alert(JSON.stringify(err));
        });
    }, 
    
    replyDelete: function(boardId, replyId){
        alert(boardId + " / " + replyId);
        $.ajax({ 
            type: "DELETE",
            url: "/api/board/" + boardId + "/reply/" + replyId,
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            alert("댓글삭제 성공");
            location.href = "/board/" + boardId;
        }).fail(function(error){
            alert(JSON.stringify(error));
        }); 
    }

}

index.init();
    

</script>  

<%@ include file="../layout/footer.jsp" %>

