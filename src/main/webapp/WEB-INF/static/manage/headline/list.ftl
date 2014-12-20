<#assign menu="headline">
<#assign submenu="headline_list">
<#include "/manage/head.ftl">
<style type="text/css">
</style>
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<!-- page start-->
		<div class="row">
				<div class="col-xs-12">
					<section class="panel">
	                  <header class="panel-heading">
	                      头图列表
	                  </header>
	                  <div class="panel-body" id="attachment">
	                  	<div>
	                  		<table class="table table-striped table-advance table-hover">
                            	<thead>
                                	<tr>
                                		<th>顺序</th>
                						<th>图片</th>
                						<th>名称</th>
                						<th>链接</th>
                						<th>时间</th>
                						<th>操作</th>
              						</tr>
                                </thead>
                            	<tbody role="alert" aria-live="polite" aria-relevant="all">
                            		<#list headlineList as headline>
                            		<tr class="gradeA odd">
                            			<td>
                                    		<input type="text" autocomplete="off" style="width: 40px;" class="js_headline_sort" name="sort" value="${headline.sort}" headlineId="${headline.headlineId}">
                                    	</td>
                                    	<td>
                                    		<a class="fancybox" rel="group" href="${BASE_PATH}/${headline.picture}">
                                    			<img src="${BASE_PATH}/${headline.picture}" alt="img04" style="height:50px;">
                                    		</a>
                                    	</td>
                                    	<td>
                                    		${headline.name}
                                    	</td>
                                    	<td>
                                    		${headline.url}
                                    	</td>
                                    	<td>${headline.createTime?string("MM-dd HH:mm")}</td>
                                    	<td>
                  							<!-- Icons -->
		                                      <a class="fancybox" rel="group" href="${BASE_PATH}/manage/headline/update.htm?headlineId=${headline.headlineId}">修改</a> | 
		                                      <a class="js_delete" href="javascript:void(0);" headlineId="${headline.headlineId}">删除</a>
                						</td>
                                	</tr>
                                	</#list>
                               	</tbody>
                              </table>
                      	</div>
						<div>
							<button type="button" class="btn btn-info js_update_sort">
								<i class="icon-refresh"></i> 更新排序
							</button>
							<a href="${BASE_PATH}/manage/headline/add.htm" type="button" class="btn btn-info">
								<i class="icon-refresh"></i> 增加头图
							</a>
						</div>                      	
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
		jQuery(".fancybox").fancybox();
	    $('.js_update_sort').click(function() {
	        var headlineSort = new Array();
	        $('.js_headline_sort').each(function(i, element) {
	            var headline = {};
	            headline.headlineId = $(element).attr('headlineId');
	            headline.sort = $(element).val();
	            headlineSort.push(headline);
	        });
	        $.post("${BASE_PATH}/manage/headline/sort.json", {
	            "sortJson": $.toJSON(headlineSort)
	        },
	        function(data) {
	            if (data.result) {
	                bootbox.alert("更新成功",
	                function() {
	                    window.location.reload();
	                });
	            } else {
	                bootbox.alert(data.msg,
	                function() {
	          		});
	            }
	        },
	        "json");
	    }); 
		$('#attachment .js_delete').click(function(){
			var file = $(this);
			bootbox.confirm("是否要删除文件？", function(result) {
				if (result) {
					$.post("${BASE_PATH}/manage/headline/delete.json",{'headlineId':file.attr("headlineId")},function(data){
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