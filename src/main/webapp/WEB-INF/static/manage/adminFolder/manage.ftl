<#assign menu="admin">
<#assign submenu="admin_folder">
<#include "/manage/head.ftl">
<style type="text/css">
.pagination {
	border-radius: 4px;
	display: inline-block;
	margin: 0;
	padding-left: 0;
}
</style>
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<!-- page start-->
		<div class="row">
			<div class="col-lg-12">
				<!--breadcrumbs start -->
				<ul class="breadcrumb">
					<li><a href="http://localhost:8080/byvision/manage/folder/page.htm?folderId=0"><i
							class="icon-home"></i>管理员权限</a></li> 				</ul>
				<!--breadcrumbs end -->
			</div>
		</div>
		
		<div class="row">
			<div class="col-lg-4">
				<section class="panel">
					<header class="panel-heading"> 添加权限 </header>
					<div class="panel-body">
						 <form id="add_adminFolder_form" method="post" class="form-horizontal" autocomplete="off" action="${BASE_PATH}/manage/adminFolder/addFolder.json">
							<fieldset>
								<input type="hidden" name="adminId" value="${admin.adminId}">
								<div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">名称</label>
                                      <div class="col-sm-10">
                                        <p style="margin-top:15px;"> ${admin.name}</p>
                                      </div>
                                  </div>
                                  <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">目录</label>
                                      <div class="col-sm-10">
                                          <select class="form-control" name="folderId" style="font-size:15px;width: 200px;">
					                   		<#list folderAll as folder>
					                   			<#if folder.owner == "no">
					                   			<option value="${folder.folderId}"> ${folder.pathName} </option>
					                   			</#if>
					                        </#list>							
			                            </select>
                                      </div>
                                  </div>
                                  <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label"></label>
                                      <button class="btn btn-danger" type="submit">增加</button>
                                  </div>		
							</fieldset>
						</form>
					</div>
				</section>
			</div>
			<div class="col-lg-8">
				<section class="panel">
					<header class="panel-heading"> 管理员权限列表 </header>
					<div class="panel-body">
						<div class="adv-table">
							<div role="grid" class="dataTables_wrapper"
								id="hidden-table-info_wrapper">
								<table class="table table-striped table-advance table-hover">
                              <thead>
                                  <tr>
                            <th>目录</th>
                          </tr>
                                </thead>
                              <tbody role="alert" aria-live="polite" aria-relevant="all">
                                  <#list folderAll as folder>
					                 <tr class="gradeA odd" <#if folder.owner == "no">style="display:none;"</#if>>
                                      <td>${folder.pathName}</td>
                                      <td>
                                		<!-- Icons -->
                              			 <a href="javascript:void(0);" title="删除${folder.name}" class="js_folder_delete" folderId="${folder.folderId}">
                                                                                                   		   删除
                                		</a>
                            			</td>
                                  	</tr>
                                  </#list>
                                </tbody>
                              </table>
                              <div style="height: 30px;">
                              </div>
                           </div>
						</div>
					</div>
				</section>
			</div>
			<!-- page end-->
	</section>
</section>
<!--main content end-->
<script type="text/javascript">
var adminId = "${admin.adminId}";
$(function() {
	$('#add_adminFolder_form').ajaxForm({
			dataType : 'json',
			success : function(data) {
				if (data.result) {
					window.location.reload();
				}else{
					bootbox.alert(data.msg, function() {
						window.location.reload();
					});
				}
			}
		});
	$('.js_folder_delete').click(function() {
        var folderId = $(this).attr('folderId');
        bootbox.dialog({
            message: "是否" + $(this).attr('title') + "文件夹",
            title: "提示",
            buttons: {
                "delete": {
                    label: "删除",
                    className: "btn-success",
                    callback: function() {
                        $.post("${BASE_PATH}/manage/adminFolder/delete.json", {
                            "folderId": folderId,
                            "adminId": adminId
                        },
                        function(data) {
                            if (data.result) {
                                window.location.reload();
                            } else {
                                bootbox.alert(data.msg,
                                function() {});
                            }
                        },
                        "json");
                    }
                },
                "cancel": {
                    label: "取消",
                    className: "btn-primary",
                    callback: function() {}
                }
            }
        });
    });
});
</script>
<#include "/manage/foot.ftl">