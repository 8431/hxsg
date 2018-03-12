var thisURL = document.URL; 
var getval = thisURL.split('?')[1];
var judgeval;
if(getval == undefined){
    judgeval = 0
}else{
    var optionVal = getval.split("&")[0];
    var judgeVal = getval.split("&")[1];
    var showVal = getval.split("&")[2];
    var optionval = optionVal.split("=")[1];
    judgeval = judgeVal.split("=")[1];
    var showval = showVal.split("=")[1];
}