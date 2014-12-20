<#assign menu="default">
<#assign submenu="">
<#include "head.ftl">
<style>
.dd-handle {
    -moz-box-sizing: border-box;
    background: none repeat scroll 0 0 #F5F5F5;
    border: 1px solid #CCCCCC;
    color: #333333;
    cursor: move;
    display: block;
    font-weight: bold;
    height: 30px;
    margin: 5px 0;
    padding: 5px 10px;
    text-decoration: none;
}
.list-group-item {
    background-color: #FFFFFF;
    border: 1px solid #DDDDDD;
    display: block;
    margin-bottom: -1px;
    padding: 3px 15px;
    position: relative;
    font-size:18px;
}
</style>
		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">
				<!--state overview start-->
				<div class="row state-overview">
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                          <div class="symbol terques">
                              <a href="${BASE_PATH}/manage/user/page.htm"><i class="icon-user" title="前往用户列表"></i></a>
                          </div>
                          <div class="value">
                              <h1>${userCount}</h1>
                              <p>用户</p>
                          </div>
                      </section>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                          <div class="symbol red">
                              <a href="${BASE_PATH}/manage/article/page.htm" title="前往文章列表"><i class="icon-tags"></i></a>
                          </div>
                          <div class="value">
                              <h1>${articleCount}</h1>
                              <p>文章</p>
                          </div>
                      </section>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                          <div class="symbol yellow">
                              <a href="${BASE_PATH}/manage/folder/page.htm" title="前往目录列表"><i class="icon-folder-open"></i></a>
                          </div>
                          <div class="value">
                              <h1>0</h1>
                              <p>目录</p>
                          </div>
                      </section>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                      <section class="panel">
                          <div class="symbol blue">
                              <a href="${BASE_PATH}/manage/attachment/page.htm" title="上传附件"><i class="icon-download-alt"></i></a>
                          </div>
                          <div class="value">
                              <h1>0</h1>
                              <p>附件</p>
                          </div>
                      </section>
                  </div>
              </div>
              <div class="row">
                  <div class="col-lg-6">
                      <section class="panel">
                          <header class="panel-heading">
                              	目录列表
                          	<span class="tools pull-right">
                            </span>
                          </header>
                          <ul class="list-group">
                          <#list folderAll as firstFolder>
                          	<li class="list-group-item">
                          		<div style="width:50% float:left">
                          			${firstFolder.pathName}
                          			<a href="${BASE_PATH}/manage/folder/update.htm?folderId=${firstFolder.folderId}" title="修改" style="float:right;">
                						[修改]
                					</a>
									<a href="${BASE_PATH}/manage/article/list.htm?folderId=${firstFolder.folderId}"  folderId="${firstFolder.folderId}" style="float:right;">
													[文章列表]
									</a>
                  				</div>
                  			</li>
                          </#list>
                          </ul>
                      </section>
                  </div>
                  <div class="col-lg-6">
                      <section class="panel">
                          <header class="panel-heading">
                              	最新文章
                              <span class="tools pull-right">
                            </span>
                          </header>
                          <li class="list-group-item" style="background-color:#DFF0D8;"><a href="${BASE_PATH}/manage/article/add.htm">添加文章</a></li>
                          <ul class="list-group">
                          	<#list articleList as article>
                          		<li class="list-group-item" style="background-color:#FCF8E3;height:33px;">
                          			<a href="${BASE_PATH}/manage/article/update.htm?articleId=${article.articleId}">${article.title}</a>
                          			<a href="${BASE_PATH}/manage/article/update.htm?articleId=${article.articleId}" title="修改" style="float:right;">
                  						[修改]
                  					</a>
                          		</li>
                          	</#list>
                          </ul>
                      </section>
                  </div>
              </div>
			  <!--state overview end-->
			<!-- page end-->
			</section>
		</section>
		<!--main content end-->
<#include "foot.ftl">
