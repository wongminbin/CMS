<#assign menu="folder"> <#assign submenu="update_folder"> <#include
"/manage/head.ftl">
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
	                	 修改头图
	              </header>
	              <div class="panel-body">
	                  <form id="update_headline_form" method="post" class="form-horizontal" autocomplete="off" action="${BASE_PATH}/manage/headline/update.json"
	                  	enctype="multipart/form-data">
	                  	<fieldset>
	                      <div class="form-group">
	                          <label class="col-sm-2 col-sm-2 control-label">选择图片</label>
	                          <div class="col-sm-10">
	                            <img src="${BASE_PATH}/${headline.picture}" alt="img04" style="height:120px;">
	                          	<input type="file" name="file"
	                              	id="file" >
	                          </div>
	                      </div>	                  	
	                  		<input type="hidden" name="headlineId" value="${headline.headlineId}">
	                      <div class="form-group">
	                          <label class="col-sm-2 col-sm-2 control-label">头图名称</label>
	                          <div class="col-sm-10">
	                              <input type="text" style="font-size:15px;width: 600px;" class="form-control" name="name" value="${headline.name}" placeholder="头图名称">
	                          </div>
	                      </div>
	                      <div class="form-group">
	                          <label class="col-sm-2 col-sm-2 control-label">头图链接</label>
	                          <div class="col-sm-10">
	                              <input style="font-size:15px;width: 600px;"
	                               class="form-control" name="url" value="${headline.url}" placeholder="头图链接">
	                          </div>
	                      </div>
	                      <div class="form-group">
	                      	  <div class="col-lg-offset-2 col-lg-10">
	                          <button class="btn btn-danger" type="submit">保存</button>
	                          </div>
	                      </div>
	                     </fieldset>
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
		$('#update_headline_form').ajaxForm({
			dataType : 'json',
			beforeSerialize: function($form, options) {
			},			
			success : function(data) {
				if (data.result) {
					bootbox.alert("保存成功，将刷新页面", function() {
						window.location.reload();
					});
				}else{
					showErrors($('#update_folder_form'),data.errors);
				}
			}
		});
	});	
</script>
<#include "/manage/foot.ftl">
