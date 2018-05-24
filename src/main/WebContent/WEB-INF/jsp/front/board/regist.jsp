<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/header.jsp"%>
	<div class="container content">
		<div class="line"></div>
		<h3 class="no-margin">글쓰기</h3>
		
		<form action="/front/board/regist.do" method="post" class="form-horizontal" onsubmit="moveData();">
			<input type="hidden" id="boardCategoryId" name="boardCategoryId" value="${param.boardCategoryId}">
			<input type="hidden" id="boardContent" name="boardContent" value="">
			<div class="form-group">
				<label for="boardSubject" class="col-sm-1 control-label">제목</label>
				<div class="col-sm-10">
					 <input type="text" id="boardTitle" name="boardTitle" value="" class="form-control" placeholder="title">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">내용</label>
			</div>
			<div class="form-group">
				<div class="col-sm-10">
					 <textarea id="content" name="content" class="form-control" placeholder="content" rows="10"></textarea>
				</div>
			</div>
			<div id="tagWrapper" class="tagWrapper">
				<label class="col-sm-1 control-label">태그</label>
				<div id="hiddenTextSize" style="display:none;"></div>
				<a class="plus" href="javascript:void(0);" onclick="addTag(); return false;"><i class="far fa-plus-square"></i></a>
			</div>
			
			<div class="margin_top_30">
				<input type="submit" value="등록" class="btn btn-primary">
				<input type="reset" value="취소" class="btn btn-danger">
			</div>
		</form>
	</div>
	
	<script>
		/***********************************************************
		 * tag
		 *********************************************************/
		var tagCount = 0;
		
		function addTag() {
			tagCount = tagCount + 1;
			
			var html="";
			
			html += "<div id=\"tagSet"+tagCount+"\" class=\"tagSet\">";
			html += "	<input type=\"text\" id=\"tag\" name=\"tag\" class=\"btn btn-primary tag\">";
			html += "	<a class=\"minus\" href=\"javascript:void(0);\" onclick=\"removeTag('tagSet"+tagCount+"'); return false;\"><i class=\"far fa-minus-square minus\"></i></a>";
			html += "</div>";
			
			$("#tagWrapper").append(html);
			
			$("#tagWrapper #tagSet" + tagCount).find(".tag").focus();
			
			$("input.tag").not(".on").keyup(function(e) {
				var value = $(this).val();
				$("#hiddenTextSize").text(value);
				$(this).parent(".tagSet").css('width',($('#hiddenTextSize').width() + 70));
			}).keypress(function(e) {
				var code = e.keyCode || e.which;
				
				if (code == 32) { //Enter keycode
					var value = $(this).val();
					if(value != "")
						addTag();
					return false;
				}
				
			}).addClass("on");
		}
		
		function removeTag(selector) {
			$("#" + selector).empty();
		}
		
		/***********************************************************
		 * // tag
		 *********************************************************/
		
		var editor = null;

		$(document).ready(function() {
			if ($("textarea").length > 0) {
				editor = CodeMirror.fromTextArea($("textarea")[0], {
					lineNumbers : true
				});
			}
		});

		function moveData() {
			$("#boardContent").val(editor.getValue());
		}
	</script>
<%@ include file="/WEB-INF/jsp/common/include/footer.jsp"%>