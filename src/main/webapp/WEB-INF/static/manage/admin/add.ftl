<#assign menu="admin_list">
<#assign submenu="add_admin">
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
                            	 添加管理员
                          </header>
                          <div class="panel-body">
                              <form id="add_admin_form" method="post" class="form-horizontal" autocomplete="off" action="${BASE_PATH}/manage/admin/addNew.json">
                              	<fieldset>
                                  <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">管理员名称</label>
                                      <div class="col-sm-10">
                                          <input type="text" class="form-control" name="adminName"
                                          	placeholder="管理员名称" id="adminName" vaule="${adminName}">
                                      </div>
                                  </div>
                                  <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">管理员邮箱</label>
                                      <div class="col-sm-10">
                                          <input type="text" class="form-control" name="email"
                                          	placeholder="管理员邮箱" id="email" vaule="${email}">
                                      </div>
                                  </div>
                                  <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">密码</label>
                                      <div class="col-sm-10">
                                          <input type="password" class="form-control" name="password"
                                          	placeholder="管理员密码">
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
              </div>
              <!-- page end-->
          </section>
		</section>
		<!--main content end-->
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
	});	
</script>
<#include "/manage/foot.ftl">
