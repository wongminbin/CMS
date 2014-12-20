<#assign menu="system">
<#assign submenu="system_basic">
<#include "/manage/head.ftl">
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<!-- page start-->
		<section class="panel">
			<header class="panel-heading"> 基本设置</header>
			<div class="panel-body">
				<form id="basicForm" action="${BASE_PATH}/manage/config/basic.json" role="form" class="form-horizontal" autocomplete="off" method="post" >
					<div class="form-group">
						<label class="col-lg-2 col-sm-2 control-label" for="inputEmail1">网站名称</label>
						<div class="col-lg-10">
							<input type="text" placeholder="网站名称" id="inputEmail1" name="sitename"
								class="form-control" value="${SYS_SITENAME}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 col-sm-2 control-label" for="inputEmail1">网站描述</label>
						<div class="col-lg-10">
							<input type="text" placeholder="网站描述" id="inputEmail1" name="sitedesc"
								class="form-control" value="${SYS_SITEDESC}">
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-offset-2 col-lg-10">
							<button class="btn btn-danger" type="submit">保存</button>
						</div>
					</div>
				</form>
			</div>
		</section>
		<!-- page end-->
	</section>
</section>
<!--main content end-->
<script type="text/javascript">
	$(function() {
		$('#basicForm').ajaxForm({
			dataType : 'json',
			success : function(data) {
				if (data.result) {
					bootbox.alert("保存成功，将刷新页面", function() {
						window.location.reload();
					});					
				}else{
					showErrors($('#basicForm'),data.errors);
				}
			}
		});
	});	
</script>
<#include "/manage/foot.ftl">
