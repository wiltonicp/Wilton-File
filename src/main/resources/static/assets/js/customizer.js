(function (jQuery) {
    "use strict";
    // data-mode="click" for using event
    // data-dark="false" for property
    
    const urlParams = new URLSearchParams(window.location.search);
    let storageDark = sessionStorage.getItem('dark')
    const myParam = urlParams.get('dark');
    
    if (myParam !== null) {
      console.log(myParam)
      if (myParam === 'true') {
        changeMode('true');
      } else {
        changeMode('false');
      }
    } else {
      if (storageDark === 'null') {
        changeMode('false');
      }
    }
    storageDark = sessionStorage.getItem('dark')
    if (storageDark !== 'null') {
      if (storageDark !== 'null') {
        changeMode(storageDark)
      }
    } else {
      if($('body').hasClass('dark')){
          changeMode('true');
      } else {
          changeMode('true');
      }
    }
    
    jQuery(document).on("change", '.change-mode input[type="checkbox"]' ,function (e) {
        const dark = $(this).attr('data-active');
        if (dark === 'true') {
            $(this).attr('data-active','false')
        } else {
            $(this).attr('data-active','true')
        }
        changeMode(dark)
    })
    function changeMode (dark) {
        const body = jQuery('body')
        updateLocalStorage(dark)
      if (dark === 'true') {
        $('[data-mode="toggle"]').find('i.a-right').removeClass('ri-sun-line');
        $('[data-mode="toggle"]').find('i.a-left').addClass('ri-moon-clear-line');
        $('#dark-mode').prop('checked', true).attr('data-active', 'false')
        $('.darkmode-logo').removeClass('d-none')
        $('.light-logo').addClass('d-none')
        body.addClass('dark')
        dark = true
      } else {
        $('[data-mode="toggle"]').find('i.a-left').removeClass('ri-moon-clear-line');
        $('[data-mode="toggle"]').find('i.a-right').addClass('ri-sun-line');
        $('#dark-mode').prop('checked', false).attr('data-active', 'true');
        $('.light-logo').removeClass('d-none')
        $('.darkmode-logo').addClass('d-none')
        body.removeClass('dark')
        dark = false
      }
        const event = new CustomEvent("ChangeColorMode", {detail: {dark: dark} });
        document.dispatchEvent(event);
    }
    function updateLocalStorage(dark) {
      sessionStorage.setItem('dark', dark)
    }
})(jQuery)

/*------Select Dropdown--------*/
$(function() {
  
    $('.select-dropdown > .selet-caption').on('click', function() {
      $(this).parent().toggleClass('open');
    });
    
    $('.select-dropdown > .list-item > .item').on('click', function() {
      $('.select-dropdown > .list-item > .item').removeClass('selected');
      $(this).addClass('selected').parent().parent().removeClass('open').children('.selet-caption').text( $(this).text() );
    });
    
    $(document).on('keyup', function(evt) {
      if ( (evt.keyCode || evt.which) === 27 ) {
        $('.select-dropdown').removeClass('open');
      }
    });
    
    $(document).on('click', function(evt) {
      if ( $(evt.target).closest(".select-dropdown > .selet-caption").length === 0 ) {
        $('.select-dropdown').removeClass('open');
      }
    });
    
  });