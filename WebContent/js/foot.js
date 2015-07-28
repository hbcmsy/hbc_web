/*
 *设置body和root的最小高度
 *使foot总是在最下面 
 */
$(document).ready(function(){
	$("body").css("min-height",document.body.scrollHeight)
	$("#root").css("min-height",document.body.scrollHeight)
	/*保持body的最小值是浏览器的大小,使foot在最下面*/
});
   