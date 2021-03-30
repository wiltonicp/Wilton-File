$(function() {
    const $list = $("#fileList");
    $('#upload-file-container').click(function(event) {
        $("#pickerKey").find('input').click();
        let elementById = document.getElementById('pickerKey');
        elementById.getElementsByTagName("input").click();
    });
    var uploader = WebUploader.create({
        auto: true,// 选完文件后，是否自动上传。
        swf: 'https://cdn.bootcss.com/webuploader/0.1.1/Uploader.swf',// swf文件路径
        server: '/upload/batch/file',// 文件接收服务端。
        dnd: '#upload-file-container',
        pick: '#pickerKey',// 内部根据当前运行是创建，可能是input元素，也可能是flash. 这里是div的id
        formData: {
            folderId: getFolderId()
        },
        multiple: true, // 选择多个
        chunked: true,// 开起分片上传。
        threads: 5, // 上传并发数。允许同时最大上传进程数。
        method: 'POST', // 文件上传方式，POST或者GET。
        fileSizeLimit: 1024*1024*100*100, //验证文件总大小是否超出限制, 超出则不允许加入队列。
        fileSingleSizeLimit: 1024*1024*100, //验证单个文件大小是否超出限制, 超出则不允许加入队列。
        fileVal:'files', // [默认值：'file'] 设置文件上传域的name。
    });

    uploader.on('fileQueued', function(file) {
        $('#upload-file-container').css("display","none");
        $('#hasFile').css("display","");
        $('#hasFileBtn').css("display","")
        const html = '<li id="' + file.id + '" aid="1" cid="0" complete="1" success="1"><i class="hint-icon hint-suc-s"\n' +
            '                                                                                                                     rel="ico"></i>\n' +
            '                                <span class="file-name" title="' + file.name + '" style="width: 315px;">' + file.name + ' <i\n' +
            '                                        rel="size_str">- ' + getfilesize(file.size) + '</i></span>\n' +
            '                                <span class="file-status" rel="status"\n' +
            '                                      style="width: 265px;font-size: 14px;float: none;">\n' +
            '                                <div class="progress" style="width: 220px;">\n' +
            '                                    <div class="progress-bar" role="progressbar" style="width: 0%;" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">0%</div>\n' +
            '                                 </div>\n' +
            '                            </span>\n' +
            '                            </li>';
        $list.append(html);
    });

    uploader.on('uploadProgress', function(file, percentage) {
        console.log(percentage * 100 + '%');
        let $li = $('#' + file.id), $percent = $li.find('.progress .progress-bar');

        // 避免重复创建
        if (!$percent.length) {
            $percent = $(
                ' <div class="progress" style="width: 220px;">' +
                '    <div class="progress-bar" role="progressbar" style="width: 0%;" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">0%</div>' +
                ' </div>')
                .appendTo($li).find('.progress-bar');
        }
        //$li.find('p.state').text('上传中');
        $percent.css('width', percentage * 100 + '%');
        $percent.html(percentage * 100 + '%');
    });

    uploader.on('uploadSuccess', function(file, response) {
        console.log(file.id+"传输成功");
    });

    uploader.on('uploadError', function(file) {
        console.log(file);
        console.log(file.id+'upload error')
    });

    $('#upload-list').on('click', '.upload-item .btn-delete', function() {
        // 从文件队列中删除某个文件id
        file_id = $(this).data('file_id');
        // uploader.removeFile(file_id); // 标记文件状态为已取消
        uploader.removeFile(file_id, true); // 从queue中删除
        console.log(uploader.getFiles());
    });

    $('#upload-list').on('click', '.btn-retry', function() {
        uploader.retry($(this).data('file_id'));
    });

    uploader.on('uploadComplete', function(file) {
        console.log(uploader.getFiles());
    });

});

/**
 * 计算文件大小
 * @param size
 * @returns {string}
 */
function getfilesize(size) {
    if (!size)
        return "";
    var num = 1024.00; //byte
    if (size < num)
        return size + "B";
    if (size < Math.pow(num, 2))
        return (size / num).toFixed(2) + "K"; //kb
    if (size < Math.pow(num, 3))
        return (size / Math.pow(num, 2)).toFixed(2) + "M"; //M
    if (size < Math.pow(num, 4))
        return (size / Math.pow(num, 3)).toFixed(2) + "G"; //G
    return (size / Math.pow(num, 4)).toFixed(2) + "T"; //T
}

function getFolderId(){
    let jQuery = $(".breadcrumb").children().last().prev();
    let folderId = $(jQuery).data("id");
    return folderId;
}