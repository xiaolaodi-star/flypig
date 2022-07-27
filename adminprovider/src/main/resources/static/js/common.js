function localhttp(uri){
    var localhttp="";
    localhttp=window.location.host;
    var support=window.location.protocol;
    localhttp=support+"//"+localhttp+uri;
    return localhttp;
}
function postmenu(uri){
    var localhttp="";
    localhttp=window.location.host;
    var support=window.location.protocol;
    localhttp=support+"//"+localhttp+uri;
    let result;
    $.ajax({
        type:"POST",
        dataType:"json",
        url:localhttp,
        async:false,
        success:function (data){
            if (Object.keys(data).length>0){
                result=data.data;
            }else {
                console.log("未接收到数据");
            }
        },
        error:function (result){
            console.log("出现错误");
        }
    })
    return result;
}
function menuinput(data){
    var menuoneicon=data.menuTableDTOMap;
    var menuone=Object.keys(data.menuTableDTOMap);
    var outstring="";
    for (var i=0;i<menuone.length;i++){
        var keymenu=menuone[i];
        var icon;
        icon=menuoneicon[keymenu];
        outstring=outstring+
            '<button type="button" class="btn dropdown-toggle" id="dropdownMenu'
            +(i+1)+'" data-toggle="dropdown">'+keymenu+'<span class="'+icon
            +'"></span>\n' +
            '        </button>';
        var second=data.secondMenu[keymenu];
        var secondlist=second.split("|");
        outstring=outstring+'<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu' +(i+1)+'">';
        for (var j=0;j<secondlist.length;j++){
            var key2=secondlist[j];
            var jumplink=data.secondlink[key2];
            console.dir(jumplink);
            outstring=outstring
                + '<li role="presentation"><a role="menuitem" tabindex="-1" href="javascript:alinkiframe(\'' +
                localhttp(jumplink)+'\''+',\''+key2+'\');">'+key2+'</a></li>';
        }
        outstring=outstring+'</ul>';
    }
    return outstring;
}
function alinkiframe(obj,key){
    var addlist="";
    var width;
    var height;
    var div=document.getElementById("content");
    height=div.offsetHeight;
    width=div.offsetWidth;
    addlist='<div style="overflow:hidden;"><iFrame scrolling="no" src="'+obj+'" width="'+width+'" height="'+height+'"></iFrame></div>'
    document.getElementById("contentlist").innerHTML=addlist;
}
