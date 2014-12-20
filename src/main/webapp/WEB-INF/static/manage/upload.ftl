<#assign menu="appendix">
<#assign submenu="add_appendix">
<#include "head.ftl">

	<section id="main-content">
          <section class="wrapper">
              <!-- page start-->
              <section class="panel">
                  <header class="panel-heading">
                                                                              上传文件
                  </header>
                  <div class="panel-body">
                  	<form id="my-awesome-dropzone" class="dropzone clickable" style="min-height:520px;" action="${BASE_PATH}/manage/photo">
                  		<div class="default message" >
                  			<span>Drop files here to upload</span>
                  			<span>从这里选择附件上传</span>
                  		</div>
                  	</form>
                     <button class="btn btn-primary upload" type="submit" style="margin-top:15px;">开始上传</button>
                  </div>
              </section>
              <!-- page end-->
          </section>
      </section>
    <script>
    	$(function(){
    		$(".upload").click(function(){
    		});
    	});
    </script>
	  
<#include "foot.ftl">
