<#include "header.ftl">
		<div class="row">
			<div class="col-sm-8 blog-main">
				<@shishuo_article_page_tag folderId=1 p=1 rows="8">
					<div class="sidebar-box category-box white" style="margin-bottom: 20px;">
						<ul style="padding-left:10px;">					
						<#list tag_article_page.list as tag_article>
							<div class="blog-post">
								<h2 class="blog-post-title">${tag_article.title}</h2>
								<p class="blog-post-meta">
									${tag_article.createTime?string("yyyy-MM-dd")}
								</p>
								<p>
									${tag_article.content}
								</p>
							</div>
						</#list>
						<div class="text-center">${tag_article_page.pageNumHtml}</div>
						</ul>
					</div>
				</@shishuo_article_page_tag>
			</div>
			<!-- /.blog-sidebar -->
			<#include "sidebar.ftl">
		</div>
		<!-- /.row -->
<#include "footer.ftl">