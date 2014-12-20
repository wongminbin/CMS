<#assign menu="comment">
<#assign submenu="auditing_comment">
<#include "/manage/head.ftl">
<style type="text/css">

</style>
<!--main content start-->
	<section id="main-content">
		<section class="wrapper">
		<!-- page start-->
			<div class="row">
			<div class="col-lg-12">
			<section class="panel">
				<header class="panel-heading">
 					审核评论
				</header>
				<div class="panel-body">
					<form role="form" class="form-horizontal">
                         <div class="form-group">
                             <label class="col-lg-2 col-sm-2 control-label" for="inputEmail1">NAME</label>
                             <div class="col-lg-10">
                                   ${comment.name!}
                             </div>
                          </div>
                         <div class="form-group">
                             <label class="col-lg-2 col-sm-2 control-label" for="inputEmail1">EMAIL</label>
                             <div class="col-lg-10">
                                   ${comment.email!}
                             </div>
                          </div>
                         <div class="form-group">
                             <label class="col-lg-2 col-sm-2 control-label" for="inputEmail1">CONTENT</label>
                             <div class="col-lg-10">
                                   ${comment.content!}
                             </div>
                          </div>
                         <div class="form-group">
                             <label class="col-lg-2 col-sm-2 control-label" for="inputEmail1">URL</label>
                             <div class="col-lg-10">
                                   ${comment.url!}
                             </div>
                          </div>
                         <div class="form-group">
                             <label class="col-lg-2 col-sm-2 control-label" for="inputEmail1">PHONE</label>
                             <div class="col-lg-10">
                                   ${comment.phone!}
                             </div>
                          </div>
                         <div class="form-group">
                             <label class="col-lg-2 col-sm-2 control-label" for="inputEmail1">IP</label>
                             <div class="col-lg-10">
                                   ${comment.ip}
                             </div>
                          </div>
                         <div class="form-group">
                        	<label class="col-sm-2 col-sm-2 control-label"></label>
                        	<div class="col-lg-10">
	                        	<#if comment.status=="hidden">
	                        	<a class="btn btn-danger js_status" href="javascript:void(0);" status="display">审核通过</a>
	                        	<a class="btn btn-success js_status" href="javascript:void(0);" status="trash">垃圾评论</a>
	                        	<#elseif comment.status=="display">
	                        	<a class="btn btn-success js_status" href="javascript:void(0);" status="trash">垃圾评论</a>
	                        	<#elseif comment.status=="trash">
	                        	</#if>
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
	$(function() {
		var commentId = ${comment.commentId};
		$('.js_status').click(function(){
			var status = $(this).attr("status");
			$.post("${BASE_PATH}/manage/comment/update/status.json",{'commentId':commentId,'status':status},function(data){
				if(data.result){
					window.location.reload();
				}
			});
		});
	});
</script>
<#include "/manage/foot.ftl">
