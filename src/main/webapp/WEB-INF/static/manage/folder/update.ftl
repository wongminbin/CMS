<#assign menu="folder"> <#assign submenu="update_folder"> <#include
"/manage/head.ftl">
<style type="text/css">
</style>
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<!-- page start-->
		<div class="row">
			<form id="update_folder_form" method="post"
				class="form-horizontal" autocomplete="off"
				action="${BASE_PATH}/manage/folder/update.json">
				<div class="col-lg-12">
					<input type="hidden" class="form-control" name="folderId"
						value="${folder.folderId}">						
					<section class="panel">
						<header class="panel-heading"> 
						修改目录
						</header>
						<div class="panel-body">
                        	<div class="form-group">
	                          <label class="col-sm-2 col-sm-2 control-label">目录中文名</label>
	                          <div class="col-sm-10">
	                              <input type="text" style="font-size:15px;width: 300px;" class="form-control" name="name"
	                              	placeholder="文章标题" id="name" value="${folder.name}">
	                              </input>
	                          </div>
	                        </div>
                        	<div class="form-group">
	                          <label class="col-sm-2 col-sm-2 control-label">目录英文名</label>
	                          <div class="col-sm-10">
	                              <input type="text" style="font-size:15px;width: 300px;" class="form-control" name="ename"
	                              	placeholder="文章标题" id="ename" value="${folder.ename}">
	                              </input>
	                          </div>
	                        </div>
	                        <div class="form-group">
	                          <label class="col-sm-2 col-sm-2 control-label">封面高</label>
	                          <div class="col-sm-10">
	                              <input type="text" style="font-size:15px;width: 300px;" class="form-control" name="height"
	                              	placeholder="封面高" id="height" value="${folder.height}">
	                              </input>
	                          </div>
	                        </div>
	                        <div class="form-group">
	                          <label class="col-sm-2 col-sm-2 control-label">封面宽</label>
	                          <div class="col-sm-10">
	                              <input type="text" style="font-size:15px;width: 300px;" class="form-control" name="width"
	                              	placeholder="封面宽" id="width" value="${folder.width}">
	                              </input>
	                          </div>
	                        </div>
	                          <div class="form-group">
	                              <label class="col-sm-2 col-sm-2 control-label">目录状态</label>
	                              <div class="col-sm-10">
	                              	<label class="radio-inline">
	                            		<input type="radio" name="status" value="display" <#if folder.status=="display">checked</#if>/> 显示
	                          		</label>
	                          		<label class="radio-inline">
	                            		<input type="radio" name="status" value="hidden" <#if folder.status=="hidden">checked</#if>/> 隐藏
	                          		</label>
	                              </div>
	                          </div>	                        
	                        <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">目录描述</label>
                              <div class="col-sm-10">
                                  <script id="content" name="content" type="text/plain"
										style="width: 100%; height: 260px;">${folder.content!}</script>
								  <script type="text/javascript">
									var contentEditor;
									$(function() {
										contentEditor = UE.getEditor('content');
									});
								  </script>
                              </div>
                        	</div>
	                        <div class="form-group">
	                      	  <div class="col-lg-offset-2 col-lg-10">
	                         	<button class="btn btn-shadow btn-primary" type="submit">更新目录</button>
	                          </div>
	                      </div>
						</div>
					</section>
				</div>
			</form>
		</div>

		<!-- page end-->
	</section>
</section>
<!--main content end-->
<script type="text/javascript">
 	var fatherId = ${folder.fatherId};
 	var kindId = ${folder.folderId};
	var kind = "folder";	
	$(function() {
		$('#update_folder_form').ajaxForm({
			dataType : 'json',
			beforeSerialize: function($form, options) {
				contentEditor.sync();
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
		$('#file_upload').uploadify({
			'buttonText'  		: 	'请选择文件',
	        'swf'         		: 	'${BASE_PATH}/system/assets/uploadify/uploadify.swf',
	        'uploader'    		: 	'${BASE_PATH}/manage/attachment/upload.json;jsessionid=${JSESSIONID}',
	        'formData'  		: 	{'kindId':kindId,'kind':kind},
	        'fileObjName'		: 	'file',
	        'fileTypeExts' 		: 	'*.jpg; *.png; *.gif',
	        'method'			:	'post',
	        'onUploadSuccess' 	: 	function(file, data, response) {
	        }
		});
		$('#folderId').change(function(){
			window.location.href = "${BASE_PATH}/manage/attachment/page.htm?folderId="+$('#folderId').val();
		});
		$('#btn_reflash').click(function(){
			window.location.reload();
		});
		jQuery(".fancybox").fancybox();
		$('#attachment .js_link').click(function(){
			var attachmentId = $(this).attr("attachmentId");
			var link = $(this).attr("link");
			bootbox.prompt("修改链接，当前链接为："+link, function(result) {
				if (result != null && result != "") {
					$.post("${BASE_PATH}/manage/attachment/update_link.json",{'attachmentId':attachmentId,'link':result},function(data){
						if(data.result){
							window.location.reload();
						}
					},"json");					
				} 
			});			
		});
		$('#attachment .js_delete').click(function(){
			var file = $(this);
			bootbox.confirm("是否要删除【"+$(this).attr("name")+"】文件？", function(result) {
				if (result) {
					$.post("${BASE_PATH}/manage/attachment/delete.json",{'attachmentId':file.attr("attachmentId")},function(data){
						if(data.result){
							window.location.reload();
						}
					},"json");
				}
			});		
		});				
	});	
</script>
<#include "/manage/foot.ftl">
