/**
 * 
 */


//					<input type="text" id="id" name="id">
//					<input type="button" id="id_check" name="" value="중복체크" onclick="return duplicate()" class="btn btn-sm btn-primary">
//				<td><input type="password" id="password" name="password" onBlur="return pwcheck()"></td>
//					<input type="password" id="rePassword" name="rePassword" onkeyup="passwd_keyup()">
//					<span id="pwmessage" style="display:none;"></span>
//				<td><input type="text" id="email" name="email"></td>
//				<td><input type="text" id="name" name="name"></td>
//				<td><input type="text" id="ssn1" name="ssn1"> - <input type="text" id="ssn2" name="ssn2"></td>
//					<select id="phone1" name="phone1">
//					- <input type="text" id="phone2" name="phone2">
//					- <input type="text" id="phone3" name="phone3">
//					<select id="hp1" name="hp1">
//					- <input type="text" id="hp2" name="hp2">
//					- <input type="text" id="hp3" name="hp3">
//				<td><input type="text" id="joindate" name="joindate"> 예) 2000/05/22</td>
// class="form-control"

var use = false;
var passForm = false;
var passMatch = false;
$(document).ready(function() {
	$("#id").keyup(function() {
		use = false;
		duplicate();
	});
})

function writeSave() {
	$id=$("#id");
	$password=$("#password");
	$rePassword=$("#rePassword");
	
	if($id.val() == "") {
		alert("아이디 입력하세요");
		return false;
	}
	
	if(!use) {
		alert("이미 사용중인 아이디입니다.");
		return false;
	}
	
	if($password.val() == "") {
		alert("비밀번호 입력하세요");
		return false;
	}
	
	if(!passForm) {
		alert("비밀번호 조합이 틀립니다.");
		return false;
	}
	
	if($rePassword.val() == "") {
		alert("비밀번호 확인 입력하세요");
		return false;
	}
	
	if(!passForm) {
		alert("비밀번호가 확인과 틀립니다.");
		return false;
	}
	
	
}

function duplicate() {
	$.ajax({
		url:"idCheck.jsp"
		,data:{
			id:$("#id").val()
		}
		,success:function(res) {
			if(res == "true") {
				$("#ide").html("가능합니다.").show().removeClass("label-danger").addClass("label-success");
				$("#ide").parent("td").removeClass("has-error").addClass("has-success");
				use = true;
			} else {
				$("#ide").html("중복입니다.").show().removeClass("label-success").addClass("label-danger");
				$("#ide").parent("td").removeClass("has-success").addClass("has-error");
				use = false;
			}
		}
	});
}

function pwcheck() {
	passForm = false;
	passMatch = false;
	if($("#password").val().match(/^(?=.*?[a-z])(?=.*?[0-9]).{3,8}$/) != null) {
		$("#passworde").html("가능합니다.").show().removeClass("label-danger").addClass("label-success");
		$("#passworde").parent("td").removeClass("has-error").addClass("has-success");
		passForm = true;
		return;
	} else {
		$("#passworde").html("비밀번호 조합이 틀립니다.").show().removeClass("label-success").addClass("label-danger");
		$("#passworde").parent("td").removeClass("has-success").addClass("has-error");
		return false;
	}
}

function passwd_keyup() {
	passMatch = false;
	if($("#rePassword").val() == $("#password").val()) {
		$("#rePassworde").html("비밀번호가 맞습니다.").show().removeClass("label-danger").addClass("label-success");
		$("#rePassworde").parent("td").removeClass("has-error").addClass("has-success");
		passMatch = true;
		return;
	} else {
		$("#rePassworde").html("비밀번호가 확인과 틀립니다.").show().removeClass("label-success").addClass("label-danger");
		$("#rePassworde").parent("td").removeClass("has-success").addClass("has-error");
		return;
	}
}

