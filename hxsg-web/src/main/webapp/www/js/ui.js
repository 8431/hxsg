var uib=1;
function mg(num,url,divid,imgcss){
if(uib==num){uib=1;}

$("#"+divid+"").html("");
$("#"+divid+"").html("<div  class='"+imgcss+"' style='background:url("+url+"/"+uib+".png) no-repeat;background-size:100% 100%;'/>");

uib++;

}
