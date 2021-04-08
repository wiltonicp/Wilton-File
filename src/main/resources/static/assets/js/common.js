$(function(){

});

function getFreeStorage(){
    $.ajax({
        url:"/user/freeStorage",
        type:"GET",
        success:function (data) {
            $("#freeStorage").html(data);
        }
    });
}