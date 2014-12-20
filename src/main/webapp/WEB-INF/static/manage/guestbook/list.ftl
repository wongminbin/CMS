<#assign menu="message">
<#assign submenu="message_list">
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
            <section class="panel">
            	<header class="panel-heading" style="font-size: 18px;">
               		 留言列表	 
                </header>
                <div class="panel-body">
                	<div class="adv-table">
                    	<div role="grid" class="dataTables_wrapper" id="hidden-table-info_wrapper">
                            <table class="table table-striped table-advance table-hover">
                            	<thead>
                                	<tr>
                						<th>标题</th>
                						<th>名称</th>
                						<th>状态</th>
                						<th>时间</th>
                						<th>操作</th>
              						</tr>
                                </thead>
                            	<tbody role="alert" aria-live="polite" aria-relevant="all">
                            		<#list pageVo.list as guestbook>
                            		<tr class="gradeA odd">
                                    	<td>${guestbook.title}</td>
                                    	<td>${guestbook.name}</td>
                                    	<td>
                                    		<#if guestbook.status=="init">未回复
                                    		<#elseif guestbook.status == "display">
                                    			显示
                                    		<#else>隐藏
                                    		</#if>
                                    	</td>
                                    	<td>${guestbook.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                                    	<td>
                  							<!-- Icons -->
                  								<a href="${BASE_PATH}/manage/guestbook/details.htm?guestbookId=${guestbook.guestbookId}" title="回复">
	                								回复
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
              <!-- page end-->
          </section>
		</section>
		<!--main content end-->
<script type="text/javascript">
$(function(){
});
</script>
<#include "/manage/foot.ftl">