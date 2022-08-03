String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

$(".answerWrite input[type=submit]").click(addAnswer);
$(".qna-comment-slipp-articles button[type=submit]").click(deleteAnswer);

function deleteAnswer(e){
	var answerId = $(this).attr('id');
	e.preventDefault();
	var queryString = $(".qna-comment-slipp-articles input[id="+answerId+"]").serialize();
	console.log("삭제할 아이디 : ",queryString);
	console.log("삭제 버튼 누르는 이벤트");
	
	$.ajax({
		type : 'post',
		url : '/api/qna/deleteAnswer',
		data : queryString,
		dataType : 'json',
		error : onDeleteError,
		success : onDeleteSuccess,
	});
}

function addAnswer(e){
	e.preventDefault();
	var queryString = $("form[name=answer]").serialize();
	
	$.ajax({
		type : 'post',
		url : '/api/qna/addAnswer',
		data : queryString,
		dataType : 'json',
		error : onError,
		success : onSuccess,
	});
}

function onSuccess(json){
	var answerTemplate = $("#answerTemplate").html();
	var template = answerTemplate.format(json.writer, new Date(json.createdDate), json.contents, json.answerId);
	
	$(".qna-comment-slipp-articles").prepend(template);
}

function onError(json, status){
	alert("잘못 입력 됌을 감지");
}

function onDeleteSuccess(){
	alert("삭제 완료 되었습니다");
}

function onDeleteError(){
	alert("삭제 실패 했습니다");
}

