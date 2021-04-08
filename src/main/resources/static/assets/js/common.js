/**
 * 空间使用量获取
 */
function getFreeStorage(){
    $.ajax({
        url:"/user/freeStorage",
        type:"GET",
        success:function (result) {
            if(result.code == 200){
                let total = result.data.total;
                let used = result.data.used;
                let usedBl = result.data.usedBl;
                let free = result.data.free;
                $('#freeStorage > p:nth-child(2)').html(used + ' / '+total+' 已使用');
                $('#freeStorage > p:nth-child(4)').html(usedBl + ' 已满 - '+ free +' 可用');
                usedBl = usedBl.replace('%','')
                $('#freeStorage > div').html('<span class="bg-primary iq-progress progress-1" data-percent="'+usedBl+'">\n' +
                    '        </span>');
            }else {
                $.alert({
                    content: result.message
                });
            }
        }
    });
}