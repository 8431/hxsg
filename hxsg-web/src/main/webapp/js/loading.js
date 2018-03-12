// 导入患者信息
function loadingShow(loadingStyle,loadingContent){
	console.log("OKOKO")
	var is_loadingStyle = loadingStyle;
	var is_loadingContent = loadingContent;
	
    var mask_bg_div = '<div class="mask_bg"></div>';
	
	var mask_bg = "";
	
	if($("div").hasClass("mask_bg")){
		mask_bg = $(".mask_bg");
	}else{
		$("body").append(mask_bg_div);
		mask_bg = $(".mask_bg");
	}
	
	var loadingImg = "loading_save";
	
	if(is_loadingStyle == "save"){
		loadingImg = "loading_save";
	}else if(is_loadingStyle == "load"){
		loadingImg = "loading_load";
	}
	console.log(is_loadingStyle);
	mask_bg.show();
	
	var addHtml_loading = '';
	
	addHtml_loading += '<div class="loading_box">';
								
	addHtml_loading += '<div class="loading_content">' +
						 '<div class="' + loadingImg + '"></div>' +
						 '<p class="loading_p">' + is_loadingContent + '</p>' +
				       '</div>';
							
	addHtml_loading += '</div>';
		
		
	$("body").append(addHtml_loading);
	
	$(".loading_box").show();
	
};

function loadingClose(){
	mask_bg = $(".mask_bg");
	mask_bg.hide();
	$(".loading_box").remove();
}