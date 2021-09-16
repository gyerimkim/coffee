<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form name="frmForm" id="_frmForm" action="noticeupload.do" method="post"
									enctype="multipart/form-data">
 

<table style="background-color: #F5F5F5;">

<tr class="form-group">
	<th style="padding-top: 30px; padding-left: 10px;">
	  <select name="category" class="form-select" id="exampleSelect1" style="width: 250px; height: 30px; font-size: 13px;">
      <option value="공지">긴급공지 여부</option>
      <option value="긴급">긴급</option>
	  <option value="공지">설정안함</option>
   	</select>
	</th>
</tr>
<tr>
	<th>
	<input type="text" name="username" class="form-control" id="disabledInput" readonly="readonly" value=${login.userName} >
	</th>
</tr>
<tr>
	<th>
	<input type="text" name="userno" class="form-control" id="disabledInput" readonly="readonly" value=${login.userNo} >
	</th>
</tr>
<tr>
	<th>
		<input type="text" name="title" class="form-control form-control-lg" placeholder="제목을 입력하세요.">
	</th>
</tr>


<tr>
	<th>
		<textarea class="summernote" rows="10" cols="70" name="content" placeholder="내용을 입력하세요."></textarea>
	</th>
</tr>
<tr>
	<th>
		<input type="file" class="form-control" onchange="readURL(this);" name="fileload">
		  <img id="image_section" src="#" alt="" width="80px;"/>
	</th>
	
</tr>

<tr>
	<th>
	예약글 설정&nbsp;<input type="date">&nbsp;&nbsp;<input type="time"></th>
</tr>

<tr>
	<td colspan="2" style="height: 50px; text-align: center;">
		 <button type="submit" class="btn btn-primary">작성하기</button>
	</td>
</tr>
</table>

</form>


<script>
function readURL(input) {
	 if (input.files && input.files[0]) {
	  var reader = new FileReader();
	  
	  reader.onload = function (e) {
	   $('#image_section').attr('src', e.target.result);  
	  }
	  
	  reader.readAsDataURL(input.files[0]);
	  }
	}
	 
	// 이벤트를 바인딩해서 input에 파일이 올라올때 위의 함수를 this context로 실행합니다.
	$("#imgInput").change(function(){
	   readURL(this);
	});


$("#_btnNotice").click(function () {
	
	$("#_frmForm").submit();
});
</script>



<script type="text/javascript">

$(document).ready(function() {

   var toolbar = [
          // 글꼴 설정
          ['fontname', ['fontname']],
          // 글자 크기 설정
          ['fontsize', ['fontsize']],
          // 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
          ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
          // 글자색
          ['color', ['forecolor','color']],
          // 표만들기
          ['table', ['table']],
          // 글머리 기호, 번호매기기, 문단정렬
          ['para', ['ul', 'ol', 'paragraph']],
          // 줄간격
          ['height', ['height']],
          // 그림첨부, 링크만들기, 동영상첨부
          ['insert',['picture','link','video']],
          // 코드보기, 확대해서보기, 도움말
          ['view', ['codeview','fullscreen', 'help']]
        ];

   var setting = {
            height : 300,
            minHeight : null,
            maxHeight : null,
            focus : true,
            lang : 'ko-KR',
            placeholder: '내용을 입력해주세요',	
            toolbar : toolbar,
            callbacks : { //여기 부분이 이미지를 첨부하는 부분
            onImageUpload : function(files, editor, welEditable) {
                  for (var i = files.length - 1; i >= 0; i--) {
                  uploadSummernoteImageFile(files[i], this);
                  }
               }
            }
         };

    $('.summernote').summernote(setting);
    });
        
   function uploadSummernoteImageFile(file, el) {
      data = new FormData();
      data.append("file", file);
      $.ajax({
         data : data,
         type : "POST",
         url : "/uploadSummernoteImageFile",
         contentType : false,
         enctype : 'multipart/form-data',
         processData : false,
         success : function(data) {
            $(el).summernote('editor.insertImage', data.url);
         }
      });
}
   
   
</script>








