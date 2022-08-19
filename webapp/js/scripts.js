// $(".qna-comment").on("click", ".answerWrite input[type=submit]", addAnswer);
$(".answerWrite input[type=submit]").click(addAnswer);
$(".article-util button[type=submit]").click(deleteAnswer);

function addAnswer(e) {
  e.preventDefault();

  var queryString = $("form[name=answer]").serialize();

  $.ajax({
    type : 'post',
    url : '/api/qna/addAnswer',
    data : queryString,
    dataType : 'json',
    error: onError,
    success : onSuccess,
  });
}

function onSuccess(json, status){
  var answer = json.answer;
  var answerCount = json.answerCount;
  console.log("왜 응답을 안함?",json);
  var answerTemplate = $("#answerTemplate").html();
  var template = answerTemplate.format(answer.writer, new Date(answer.createdDate), answer.contents, answer.answerId, answer.answerId);
  $(".qna-comment-slipp-articles").prepend(template);
  $(".answer-Count").text(answerCount);
}

function onError(xhr, status) {
  alert("error");
}

String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

function deleteAnswer(e){
	
	e.preventDefault();
	console.log("지우는 버튼을 누름");
	var answerId = $(this).val();
	console.log("지우려는 답변 아이디 : ",answerId);
	console.log("지우려는 답변 아이디의 타입 : ",typeof(answerId));
	
	$.ajax({
	    type : 'post',
	    url : '/api/qna/deleteAnswer',
	    data : answerId,
	    dataType : 'json',
	    error: onError,
	    success : onDeleteSuccess,
	  });
};

function onDeleteSuccess(json, status){
	  var className = ".article_"+json.answerId;
	  $(className).remove();
}