(function (jQuery) {
    "use strict";
    // const urlParams = new URLSearchParams(window.location.search);
    // const myParam = urlParams.get('file');
    // if (myParam !== null) {
    //     let target = $('[base-url]').attr('data-target')
    //     let url = $('[base-url]').attr('base-url')+myParam
    //     console.log(url)
    //     loadDocToHtml(target, url)
    // }
    $(document).on('click', '[data-load-file="file"]' ,function(){
        let target = $(this).attr('data-load-target')
        let url = $(this).attr('data-url')
        let title = $(this).attr('data-title')
        let previewUrl = $(this).attr('data-preview-url')
        $('.modal-title').html(title)
        $('.modal-preview-link').attr('href', previewUrl)
        loadDocToHtml(target, url)
    })

    function loadDocToHtml(target, url){
        $(target).html('')
        $(target).officeToHtml({
            url: url,
            pdfSetting: {
                setLang: "",
                setLangFilesPath: "" /*"include/pdf/lang/locale" - relative to app path*/
            }
        });
    }

})(jQuery)