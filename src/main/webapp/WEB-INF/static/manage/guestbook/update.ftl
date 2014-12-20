<#assign menu="message">
<#assign submenu="update_message">
<#include "/manage/head.ftl">
<style type="text/css">
.guesbook_size{
    margin: 10px 0;
    font-size: 18px;
}
</style>
<!--main content start-->
	<section id="main-content">
		<section class="wrapper">
		<!-- page start-->
			<div class="row">
			<div class="col-lg-12">
			<section class="panel">
				<header class="panel-heading">
					${guestbook.title}
				</header>
				<div class="panel-body">
					<form id="reply_message_form" method="post"
								class="form-horizontal tasi-form" autocomplete="off"
								action="${BASE_PATH}/manage/guestbook/addReply.json">
						<div class="rows">
							<div class="col-xs-6">
								<p class="guesbook_size"><b>姓名&nbsp;:&nbsp;</b>${guestbook.name}
							</div>
							<div class="col-xs-6">
								<p class="guesbook_size"><b>邮箱&nbsp;:&nbsp;</b>${guestbook.email}</p>
							</div>	
						</div>
						<div class="rows">
							<div class="col-xs-12">
								<p class="guesbook_size"><b>标题&nbsp;:&nbsp;</b>${guestbook.title}</p>
							</div>	
						</div>
						<div class="rows">
							<div class="col-xs-12">
								<p class="guesbook_size" style="margin-top:25px;"><b>内容:</b></p>
								<p style="font-size;16px;">&nbsp;&nbsp;&nbsp;&nbsp;${guestbook.content}</p>
							</div>	
						</div>
						<input type="hidden" name="guestbookId" value="${guestbook.guestbookId}">
						<div class="rows">
							<div class="col-xs-12">
								<p class="guesbook_size" style="margin-top:25px;"><b>回复</b></p>
								<textarea style="width:930px;height:200px;" name="reply">${guestbook.reply}</textarea>								
							</div>							
						</div>
						<div class="rows">
							<div class="col-xs-12">
								<p class="guesbook_size"><b>状态：</b>
									<input type="radio"
												name="status" value="hidden" <#if guestbook.status =="hidden"> checked </#if>/> 隐藏	
									<input type="radio"
												name="status" value="display" <#if guestbook.status !="hidden"> checked </#if> /> 显示
								</p>						
							</div>							
						</div>
						<div class="rows">
							<div class="col-xs-12">
								<button type="submit" class="btn btn-shadow btn-primary">发布</button>						
							</div>							
						</div>
					</form>
				</div>
			</section>
		</div>
		</div>
<!-- page end-->
		</section>
	</section>
 <!--main content end-->
<script type="text/javascript">
var guestbookId=${guestbook.guestbookId};
	$(function(){
		$('#reply_message_form').ajaxForm(function(data){
			showErrors($('#add_message_form'),data.errors);
			if(data.result){
				bootbox.alert("保存成功",
			   		function() {
                    	window.location.href = "${BASE_PATH}/manage/guestbook/list.htm";
                    	$('#reply_message_form').clearForm();
                	});
			}else{
				bootbox.alert("保存失败！"+data.msg,
			   		function() {
                    	$('#reply_message_form').clearForm();
                    	window.location.href = "${BASE_PATH}/manage/guestbook/details.htm?guestbookId=" + guestbookId;
                	});
			}
		});	
		
		$(".js_message_status").change(function(){
		$.post("${BASE_PATH}/manage/guestbook/status.json", {"guestbookId": $(this).attr("guestbookId"),status:$(this).val()},function(){
			window.location.reload();
        },"json");  	
    });
	});
</script>
<#include "/manage/foot.ftl">