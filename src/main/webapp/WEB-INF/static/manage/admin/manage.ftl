<#assign menu="admin_list">
<#assign submenu="admin_list">
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
					<li>
						<a href="http://localhost:8080/byvision/manage/folder/page.htm?folderId=0"><i class="icon-home"></i>管理员管理</a>
					</li>
				</ul>
				<!--breadcrumbs end -->
			</div>
		</div>
		<div class="row">
			<div class="col-lg-4">
				<section class="panel">
					<header class="panel-heading"> 添加管理员 </header>
					<div class="panel-body">
						 <form id="add_admin_form" method="post" class="form-horizontal" autocomplete="off" action="${BASE_PATH}/manage/admin/addNew.json">
							<fieldset>
								<div class="form-group">
                                      <label class="col-sm-3 col-sm-3 control-label">名称</label>
                                      <div class="col-sm-9">
                                          <input type="text" class="form-control" name="adminName"
                                            placeholder="管理员名称" id="adminName" >
                                      </div>
                                  </div>
                                  <div class="form-group">
                                      <label class="col-sm-3 col-sm-3 control-label">密码</label>
                                      <div class="col-sm-9">
                                          <input type="password" class="form-control" name="password"
                                            placeholder="管理员密码">
                                      </div>
                                  </div>
                                  <div class="form-group">
                                    <label class="col-sm-3 col-sm-3 control-label"></label>
                                    <div class="col-sm-9">
                                      <button class="btn btn-danger" type="submit">增加</button>
                                    </div>
                                  </div>		
							</fieldset>
						</form>
					</div>
				</section>
			</div>
			<div class="col-lg-8">
				<section class="panel">
					<header class="panel-heading"> 所有管理员列表 </header>
					<div class="panel-body">
						<div class="adv-table">
							<div role="grid" class="dataTables_wrapper"
								id="hidden-table-info_wrapper">
								<table class="table table-striped table-advance table-hover">
                              <thead>
                                  <tr>
                            <th>管理员</th>
                            <th>操作</th>
                          </tr>
                                </thead>
                              <tbody role="alert" aria-live="polite" aria-relevant="all">
                                <#list pageVo.list as e>
                                <tr class="gradeA odd">
                                      <td>${e.name}</td>
                                      <td>
                                <!-- Icons -->
                               <a href="javascript:void(0);" adminId="${e.adminId}" title="删除" class="js_delete_admin">
                                                                                                      删除
                                </a>|
                                <a href="${BASE_PATH}/manage/adminFolder/manage.htm?adminId=${e.adminId}" title="权限">
                                	权限
                                </a>
                            </td>
                                  </tr>
                                  </#list>
                                </tbody>
                              </table>
                              <div style="height: 30px;">
                              <div class="pagination">${pageVo.pageNumHtml}</div>
                              </div>
                           </div>
						</div>
					</div>
				</section>
			</div>
			<!-- page end-->
	</section>
</section>
<script type="text/javascript">
	$(function() {
		$('#add_admin_form').ajaxForm({
			dataType : 'json',
			success : function(data) {
				if (data.result) {
					bootbox.alert("保存成功，将刷新页面", function() {
						window.location.reload();
					});
				}else{
					showErrors($('#add_admin_form'),data.errors);
				}
			}
		});
		
		$('.js_delete_admin').click(function() {
		var adminId= $(this).attr('adminId');
        bootbox.dialog({
            message: "是否" + $(this).attr('title') + "管理员",
            title: "提示",
            buttons: {
                "delete": {
                    label: "删除",
                    className: "btn-success",
                    callback: function() {
                        $.post("${BASE_PATH}/manage/admin/delete.json", {
                            "adminId": adminId
                        },
                        function(data) {
                            if (data.result) {
                                bootbox.alert("删除成功",
                                function() {
                                   window.location.reload();
                                });
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