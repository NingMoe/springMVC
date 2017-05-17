<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 响应式布局 等比1:1缩放 -->
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<!-- 引入 Bootstrap -->
<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="/SpringMVCLearn/resources/js/alert.js"></script>
<script src="/SpringMVCLearn/resources/js/dist/dropload.js"></script>
<script src="/SpringMVCLearn/resources/js/zepto.min.js"></script>

<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link
	href="//cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet">



<!-- <script type="text/javascript" -->
<!--    th:src="${session.basePath}+'/resources/js/alert.js'" defer="true"></script> -->
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<style>
<!--
超小设备（手机，小于768px）Bootstrap中默认情况下没有媒体查询--
>
<!--
小型设备（平板电脑，768px起）--
>
@media ( min-width : @screen-sm-min) {
	...
}

<!--
中型设备（台式电脑，992px起）--
>
@media ( min-width : @screen-md-min) {
	...
}

<!--
大型设备（大台式电脑，1200px起）--
>
@media ( min-width : @screen-lg-min) {
	...
}

.top {
	margin-top: 20px;
}
</style>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$(".panel").click(function() {
							var id = $(this).attr("id");
							$.alert(id);
						});

						var dropload = $('.top')
								.dropload(
										{
											domUp : {
												domClass : 'dropload-up',
												domRefresh : '<div class="dropload-refresh">↓下拉刷新</div>',
												domUpdate : '<div class="dropload-update">↑释放更新</div>',
												domLoad : '<div class="dropload-load"><span class="loading"></span>加载中...</div>'
											},
											domDown : {
												domClass : 'dropload-down',
												domRefresh : '<div class="dropload-refresh">↑上拉加载更多</div>',
												domUpdate : '<div class="dropload-update">↓释放加载</div>',
												domLoad : '<div class="dropload-load"><span class="loading"></span>加载中...</div>'
											},
											loadUpFn : function(me) {
												$
														.ajax({
															type : 'POST',
															url : '/springboot/product/indexData',
															dataType : 'json',
															success : function(
																	data) {
																var result = '';
																for (var i = 0; i < 10; i++) {
																	result += '<a class="item opacity">'
																			+ '<img src="'+i+'" alt="">'
																			+ '<h3 href="'+i+'" >'
																			+ i
																			+ '</h3>'
																			+ '<span class="date">'
																			+ i
																			+ '</span>'
																			+ '</a>';
																}
																// 为了测试，延迟1秒加载
																setTimeout(
																		function() {
																			$(
																					'.lists')
																					.html(
																							'');
																			$(
																					'.lists')
																					.prepend(
																							result);
																			me
																					.resetload();
																		}, 1000);
															},
															error : function(
																	xhr, type) {
																alert('Ajax error!');
																me.resetload();
															}
														});
											},
											loadDownFn : function(me) {
												$
														.ajax({
															type : 'POST',
															url : '/springboot/product/indexData',
															dataType : 'json',
															success : function(
																	data) {
																var result = '';
																for (var i = 0; i < 10; i++) {
																	result += '<a class="item opacity">'
																			+ '<img src="'+1+'" alt="">'
																			+ '<h3 href="'+2+'" >'
																			+ 3
																			+ '</h3>'
																			+ '<span class="date">'
																			+ 4
																			+ '</span>'
																			+ '</a>';
																}
																// 为了测试，延迟1秒加载
																setTimeout(
																		function() {
																			$(
																					'.lists')
																					.append(
																							result);
																			me
																					.resetload();
																		}, 1000);
															},
															error : function(
																	xhr, type) {
																alert('Ajax error!');
																me.resetload();
															}
														});
											}
										});

					});
	// var dropload = $('.top').dropload({
	//             loadUpFn : function(me) {
	//                 $.ajax({
	//                             type : 'GET',
	//                             url : '/springboot/product/indexData',
	//                             dataType : 'json',
	//                             success : function(data) {
	//                                 if (data != null) {
	//                                     var htmls = "";
	//                                     for ( var i in data) {
	//                                         htmls += '<div class="panel panel-default" id="2">';
	//                                         htmls += '<div class="panel-heading">';
	//                                         htmls += '<h3 class="panel-title">smartwasp</h3>';
	//                                         htmls += '</div>';
	//                                         htmls += '<div class="center-block">';
	//                                         htmls += '<img';
	//                                         htmls += 'src="'
	//                                                 + data[i]
	//                                                 + '"';
	//                                         htmls += 'style="height: 20%; width: 100%;"';
	//                                         htmls += 'class="img-thumbnail img-responsive" />';
	//                                         htmls += '</div>';

	//                                         htmls += '<table class="table">';
	//                                         htmls += '<th>产品</th>';
	//                                         htmls += '<th>价格</th>';
	//                                         htmls += '<tr>';
	//                                         htmls += '<td>产品 A</td>';
	//                                         htmls += '<td>200</td>';
	//                                         htmls += '</tr>';
	//                                         htmls += '<tr>';
	//                                         htmls += '<td>产品 B</td>';
	//                                         htmls += '<td>400</td>';
	//                                         htmls += '</tr>';
	//                                         htmls += '</table>';
	//                                         htmls += '</div>';
	//                                     }
	//                                     $('.top').append(
	//                                                     htmls);
	//                                 }
	//                                 // 代码执行后必须重置  
	//                                 me.resetload();
	//                             },
	//                             error : function(xhr, type) {
	//                                 alert('Ajax error!');
	//                                 me.resetload();
	//                             }
	//                         });
	//             },
	//             loadDownFn : function(me) {
	//                 $.ajax({
	//                             type : 'GET',
	//                             url : '/springboot/product/indexData',
	//                             dataType : 'json',
	//                             success : function(data) {
	//                                 if (data != null) {
	//                                     var htmls = "";
	//                                     for ( var i in data) {
	//                                         htmls += '<div class="panel panel-default" id="2">';
	//                                         htmls += '<div class="panel-heading">';
	//                                         htmls += '<h3 class="panel-title">smartwasp</h3>';
	//                                         htmls += '</div>';
	//                                         htmls += '<div class="center-block">';
	//                                         htmls += '<img';
	//                                         htmls += 'src="'
	//                                                 + data[i]
	//                                                 + '"';
	//                                         htmls += 'style="height: 20%; width: 100%;"';
	//                                         htmls += 'class="img-thumbnail img-responsive" />';
	//                                         htmls += '</div>';

	//                                         htmls += '<table class="table">';
	//                                         htmls += '<th>产品</th>';
	//                                         htmls += '<th>价格</th>';
	//                                         htmls += '<tr>';
	//                                         htmls += '<td>产品 A</td>';
	//                                         htmls += '<td>200</td>';
	//                                         htmls += '</tr>';
	//                                         htmls += '<tr>';
	//                                         htmls += '<td>产品 B</td>';
	//                                         htmls += '<td>400</td>';
	//                                         htmls += '</tr>';
	//                                         htmls += '</table>';
	//                                         htmls += '</div>';
	//                                     }
	//                                     $('.top').append(htmls);
	//                                 }
	//                                 // 代码执行后必须重置  
	//                                 me.resetload();
	//                             },
	//                             error : function(xhr, type) {
	//                                 alert('Ajax error!');
	//                                 me.resetload();
	//                             }
	//                         });
	//             }
	//         });
</script>
</head>
<body>

	<div class="container">
		<div class="row top">
			<!--  <div class="col-md-8 col-sm-offset-2 text-center">
                <div class="panel panel-default" id="1">
                    <div class="panel-heading">
                        <h3 class="panel-title">smartwasp</h3>
                    </div>
                    <div class="center-block">
                        <img
                            src="http://www.smartwasp.com/upfile/201704/2017040857100441.jpg"
                            style="height: 20%; width: 100%;"
                            class="img-thumbnail img-responsive" />
                    </div>
                    <table class="table">
                        <th>产品</th>
                        <th>价格</th>
                        <tr>
                            <td>产品 A</td>
                            <td>200</td>
                        </tr>
                        <tr>
                            <td>产品 B</td>
                            <td>400</td>
                        </tr>
                    </table>
                </div>
                <div class="panel panel-default" id="2">
                    <div class="panel-heading">
                        <h3 class="panel-title">smartwasp</h3>
                    </div>
                    <div class="center-block">
                        <img
                            src="http://www.smartwasp.com/upfile/201704/2017040857099769.jpg"
                            style="height: 20%; width: 100%;"
                            class="img-thumbnail img-responsive" />
                    </div>

                    <table class="table">
                        <th>产品</th>
                        <th>价格</th>
                        <tr>
                            <td>产品 A</td>
                            <td>200</td>
                        </tr>
                        <tr>
                            <td>产品 B</td>
                            <td>400</td>
                        </tr>
                    </table>
                </div>
                <div class="panel panel-default" id="3">
                    <div class="panel-heading">
                        <h3 class="panel-title">smartwasp</h3>
                    </div>
                    <div class="center-block">
                        <img
                            src="http://www.smartwasp.com/upfile/201704/2017040857100145.jpg"
                            style="height: 20%; width: 100%;"
                            class="img-thumbnail img-responsive" />
                    </div>

                    <table class="table">
                        <th>产品</th>
                        <th>价格</th>
                        <tr>
                            <td>产品 A</td>
                            <td>200</td>
                        </tr>
                        <tr>
                            <td>产品 B</td>
                            <td>400</td>
                        </tr>
                    </table>
                </div>
            </div>-->
		</div>
	</div>


	<!-- 包括所有已编译的插件 -->
	<script src="../resources/js/bootstrap.min.js"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/less.js/1.7.3/less.min.js"></script>
	
</body>
</html>