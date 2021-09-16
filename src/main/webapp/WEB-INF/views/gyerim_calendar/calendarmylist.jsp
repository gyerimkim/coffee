<%@page import="bit.com.a.dto.CalendarPlugDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link href="<%=request.getContextPath() %>/fcalLib/main.css" rel="stylesheet">
<script src="<%=request.getContextPath() %>/fcalLib/main.js"></script>

<style type="text/css">
.body {
  margin: 40px 5px;
  padding: 0;
  font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
  font-size: 15px;
}
#calendar{
	max-width: 900px;
	margin: 0 auto;
}
</style>


<script type="text/javascript">
let gl_calendar;

$(document).ready(function() {
	init();
	
	let eventSeq;
	
	$(document).on('click', '#saveBnt', function(event) {
		const title = $('#title').val();
		const start = $('#start').val();
		const end = $('#end').val();
		const color = $('#color').val();
		
		const requestData = { title, start, end, color };
		if (eventSeq) { // null, undefined, 0, '' 이면 false
			requestData.seq = eventSeq; 
		}
		
		$.ajax({
			url: 'caleventwrite.do',
			method: 'post',
			data: requestData,
			type: 'json',
			success: function(res) {
				getlist();
			},
			error: function() {
				alert('오류!');
			}
		});
	});
	
	$(document).on('click', '#removeBtn', function(event) {
		if (!eventSeq) {
			return false;
		}
		
		$.ajax({
			url: 'caleventremove.do',
			method: 'post',
			data: { seq: eventSeq },
			type: 'json',
			success: function(res) {
				getlist();
			},
			error: function() {
				alert('오류!');
			}
		});
	});
	
	function init() {
		let calendarEl = document.getElementById("calendar");	
		let calendar = new FullCalendar.Calendar(calendarEl, {		
			headerToolbar: {
				left: "prev today",
				center: "title",
				//right: "dayGridMonth,timeGridWeek,timeGridDay,listMonth"
				right: "next"
			},
			initialDate: new Date(),	// 처음 실행시 설정 날짜 오늘인 경우 '2021-08-21'
			locale: 'ko',				// 한글설정
			//navLinks: true,
			businessHours: true,
			events:[],
			dayClick: function(seldate,jsEvent,view) {
				console.log('dayClick');
                return false;
            },
			eventClick: function(info) { // 일정을 클릭 했을때
				eventSeq = info.event.id;
				$('#title').val(info.event.title);
				$('#start').val(toDateString(info.event.start));
				$('#end').val(toDateString(info.event.end));
				$('#color').val(info.event.backgroundColor);
				
				$('#removeBtn').show();
				$("#exampleModal").modal('show');
		  	},
		  	dateClick: function (info) { // 빈 공간을 클릭 했을때
				$("#exampleModal").modal('show');
				$("#start").val(info.dateStr);
				$('#title').val('');
				$('#end').val('');
				$('#color').val('#000000');
				eventSeq = null;
				$('#removeBtn').hide();
			}
		});
		
		calendar.render();	
		gl_calendar = calendar;
		
		getlist();
	}
	
	function getlist() {
		$.ajax({
			url:"caleventlist.do",
			method: "get",
			type: "json",
			data: { myEvent: 'true' },
			success: function(data) {
				console.log('success', data);
				const eventList = [];
				for (let i = 0; i < data.length; i++) {
					const event = data[i];
					eventList.push({
						id: event.seq,
						title: event.title,
						start: event.start.substring(0, 10),
						end: event.end.substring(0, 10),
						color: event.color
					})
				}
				gl_calendar.removeAllEventSources();
				gl_calendar.addEventSource(eventList);
				$("#exampleModal").modal('hide');
			},
			error:function(e1, e2, e3){
				alert('오류!');
				console.log('error', e1, e2, e3);
				$("#exampleModal").modal('hide');
			}
		}); 
	}
	
	function toDateString(date) {
		date = new Date(date);
		date.setHours(date.getHours() + 9);
		return date.toISOString().split('T')[0];
	}

})


</script>

<div class="body">
	<div id="calendar"></div>
</div>



<!-- 모달 창 테두리 -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <!-- 모달 대화창 본체-->
  <div class="modal-dialog" role="document">
    <!-- 모달 콘텐츠 부분 -->
    <div class="modal-content">
      <!-- 모달 헤더ー -->
      <div class="modal-header">
        <!-- 모달 제목 -->
        <h5 class="modal-title" id="exampleModalLabel">모달 제목</h5>
        <!-- 닫기 아이콘 -->
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
         <span aria-hidden="true">&times;</span>
        </button>
      </div>
      
      <!-- 모달 본문 -->
      <div class="modal-body">
      	<div class="form-group row">
	       <label for="name" class="col-md-3 col-form-label">시작날짜</label>
	       <div class="col-md-9">
	         <input type="text" class="form-control" id="start" required>
	      </div>
	    </div>
	    <div class="form-group row">
	       <label for="name" class="col-md-3 col-form-label">종료날짜</label>
	       <div class="col-md-9">
	         <input type="text" class="form-control" id="end" required>
	      </div>
	    </div>
	    <div class="form-group row">
	       <label for="name" class="col-md-3 col-form-label">제목</label>
	       <div class="col-md-9">
	         <input type="text" class="form-control" id="title" required>
	      </div>
	    </div>
	    <div class="form-group row">
	       <label for="name" class="col-md-3 col-form-label">색</label>
	       <div class="col-md-9">
	         <input type="color" class="form-control" id="color" required>
	      </div>
	    </div>
      </div>
      
      
      <!-- 모달 꼬리말 -->
      <div class="modal-footer">
      	<button type="button" class="btn btn-secondary" id="saveBnt">일정저장</button>
        <!-- 닫기 버튼 -->
        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
        <!-- 삭제 버튼 -->
        <button type="button" class="btn btn-danger" id="removeBtn">삭제</button>
      </div>
    </div>
  </div>
</div>











    