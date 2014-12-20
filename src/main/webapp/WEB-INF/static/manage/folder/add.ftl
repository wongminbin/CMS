<#assign menu="folder">
<#assign submenu="add_folder">
<#include "/manage/head.ftl">
<style type="text/css">
.m-bot15 {
    margin-bottom: 5px;
}
.form-control {
    border: 1px solid #E2E2E4;
    box-shadow: none;
    color: #C2C2C2;
}
.input-lg {
    border-radius: 6px;
    font-size: 15px;
    height: 40px;
    line-height: 1.33;
    padding: 9px 15px；
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
                            	 添加目录
                          </header>
                          <div class="panel-body">
                              <form id="addFolder_form" method="post" class="form-horizontal" autocomplete="off" action="${BASE_PATH}/manage/folder/add.json">
                              	<fieldset>
                                  <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">目录名称</label>
                                      <div class="col-sm-10">
                                          <input type="text" style="font-size:15px;width: 300px;" class="form-control" name="folderName"
                                          	placeholder="目录名称" id="folderName" >${folderName}
                                          </input>
                                      </div>
                                  </div>
                                  <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">英文名称</label>
                                      <div class="col-sm-10">
	                                      <input style="font-size:15px;width: 300px;" onkeyup="value=value.replace(/[\W]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" 
	                                       CLASS="form-control" NAME="folderEname" PLACEHOLDER="英文名称">
	                                       <p class="help-block">注意:英文名称只能是数字或者英文字母或者是下划线组成</p>
                                      </div>
                                  </div>
                                  <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">父级标签</label>
                                      <div class="col-sm-10">
										<select class="form-control input-lg m-bot15" style="font-size:15px;width: 300px;" name="fatherId">
					                   		<#list folderAll as firstFolder>
					                        	<option style="background-color:#DFF0D8;font-weight:bold;" value="${firstFolder.folderId}">
					                        	├─┬─${firstFolder.name}
					                        	</option>
						                        	<#list firstFolder.folderList as secondFolder>
						                        	<option style="background-color:#5BC0DE;color:#FFFFFF;" value="${secondFolder.folderId}">
						                        	│&nbsp;&nbsp;&nbsp;└──${secondFolder.name}
						                        	</option>
														<#list secondFolder.folderList as thirdFolder>
							                        	<option style="background-color:#FCF8E3;" value="${thirdFolder.folderId}">
							                        	│&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;└──${thirdFolder.name}
							                        	</option>
							                        	</#list>			                        	
						                        	</#list>
					                        </#list>							
			                            </select>                                        
                                      </div>
                                  </div>                                                                 
                                  <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">目录状态</label>
                                      <div class="col-sm-10">
                                      	<label class="radio-inline">
                                    		<input type="radio" name="status" value="display" checked/> 显示
                                  		</label>
                                  		<label class="radio-inline">
                                    		<input type="radio" name="status" value="hidden"/> 隐藏
                                  		</label>
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
		$('#addFolder_form').ajaxForm({
			dataType : 'json',
			success : function(data) {
				if (data.result) {
					bootbox.dialog({
						message : "保存成功",
						title : "提示",
						buttons : {
							add : {
								label : "继续添加",
								className : "btn-success",
								callback : function() {
									window.location.reload();
								}
							},
							list : {
								label : "查看文件夹列表",
								className : "btn-primary",
								callback : function() {
									window.location.href="${BASE_PATH}/manage/folder/list.htm";
								}
							},
						}
					});
				}else{
					showErrors($('#addFolder_form'),data.errors);
				}
			}
		});
	});	
</script>
<#include "/manage/foot.ftl">
