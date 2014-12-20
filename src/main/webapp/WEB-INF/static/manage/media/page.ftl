<#assign menu="attachment">
<#assign submenu="attachment">
<#include "/manage/head.ftl">
<!--main content start-->
<section id="main-content">    
	<section class="wrapper">
		<div class="row">
			<div class="col-lg-9">            
              <!-- page start-->
              <section class="panel">
                  <header class="panel-heading">
                  	目录【${folder.name}】中的文件
                  </header>
                  <div class="panel-body">
                      <ul class="grid cs-style-3">
                      	<#list attachmentPage.list as attachment>
                          <li>
                          	  <#if attachment.type = "photo">
                              <figure>
                                  <img src="${BASE_PATH}/${attachment.path}" alt="${attachment.name}">
                                  <figcaption>
                                      <h3>名称：${attachment.name}</h3>
                                      <h5>大小：${attachment.size/1024}k</h5>
                                      <a class="fancybox" rel="group" href="${BASE_PATH}/${attachment.path}">查看大图</a>
                                  </figcaption>
                              </figure>
                              <#else>
                              <figure>
                                  <img src="${BASE_PATH}/system/images/folder-data.png" alt="${attachment.name}">
                                  <figcaption>
                                      <h3>名称：${attachment.name}</h3>
                                      <h5>大小：${attachment.size/1024}k</h5>
                                  </figcaption>
                              </figure>                              
                              </#if>
                          </li>
                        </#list>
                      </ul>
                  </div>
              </section>
            </div>
			<!-- 右侧开始 -->
			<div class="col-lg-3">	
				<section class="panel">
					<header class="panel-heading"> 上传 </header>
					<div class="panel-body">
						<div class="form-group">
							<label for="exampleInputEmail1">所属目录</label>
								<select class="form-control" id="folderId" autocomplete="off" >
									<option style="background-color:#DFF0D8;font-weight:bold;" value="1" <#if folder.folderId==1>selected</#if>>默认</option>
			                   		<#list folderAll as firstFolder>
			                        	<option style="background-color:#DFF0D8;font-weight:bold;" value="${firstFolder.folderId}"  <#if folder.folderId==firstFolder.folderId>selected</#if>>
			                        	├─┬─${firstFolder.name}
			                        	</option>
				                        	<#list firstFolder.folderList as secondFolder>
				                        	<option style="background-color:#5BC0DE;color:#FFFFFF;" value="${secondFolder.folderId}"  <#if folder.folderId==secondFolder.folderId>selected</#if>>
				                        	│&nbsp;&nbsp;&nbsp;└──${secondFolder.name}
				                        	</option>
												<#list secondFolder.folderList as thirdFolder>
					                        	<option style="background-color:#FCF8E3;" value="${thirdFolder.folderId}"  <#if folder.folderId==thirdFolder.folderId>selected</#if>>
					                        	│&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;└──${thirdFolder.name}
					                        	</option>
						                        	<#list thirdFolder.folderList as fourthFolder>
						                        	<option style="background-color:#D9EDF7;" value="${fourthFolder.folderId}"  <#if folder.folderId==fourthFolder.folderId>selected</#if>>
						                        	│&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;│&nbsp;&nbsp;└──${fourthFolder.name}
						                        	</option>
						                        	</#list>
					                        	</#list>			                        	
				                        	</#list>
			                        </#list>							
	                            </select>							
						</div>
						<div class="form-group">
							<button id="file_upload" class="btn btn-shadow btn-primary" type="submit">发布</button>
							<button id="btn_reflash" class="btn btn-shadow btn-primary" type="submit"><i class="icon-refresh"></i> 刷新</button>
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
$(function(){
	$('#file_upload').uploadify({
		'buttonText'  		: 	'请选择文件',
        'swf'         		: 	'${BASE_PATH}/system/assets/uploadify/uploadify.swf',
        'uploader'    		: 	'${BASE_PATH}/manage/attachment/upload.json;jsessionid=${JSESSIONID}',
        'formData'  		: 	{'articleId':0,folderId:${folder.folderId}},
        'fileObjName'		: 	'file',
        'fileTypeExts' 		: 	'*.gif;*.png;*.jpg;*.jpeg;*.bmp;*.rar;*.doc;*.docx;*.zip,*.pdf;*.txt;*.swf;*.wmv',
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
});
</script>
<#include "/manage/foot.ftl">
