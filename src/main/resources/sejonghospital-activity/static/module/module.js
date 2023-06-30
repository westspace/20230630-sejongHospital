/*
$('document').ready(function(){
    console.log("ready!!");

    $('header > .container .burger-icon').click(function(event){
        $('body > nav').css('visibility','visible');
        $('nav .drawer-wrap .container > .navigation').css('left','0');
    });

    $('.drawer-wrap .navigation .header .drawer-button .burger-icon').click(function(event){
        $('body > nav').css('visibility','hidden');
        
        if($(window).width() > 770){
            $('nav .drawer-wrap .container > .navigation').css('left','-71%');
        }else{
            $('nav .drawer-wrap .container > .navigation').css('left','-100%');
        }
    });

    $('header > .container .dot-icon').click(function(event){
        
    });

    var scroll = 0;
    $(window).scroll(function(){
        var scrollValue = $(document).scrollTop();
        
        var value = Math.floor(scrollValue/50);
        if(scroll != value) {
            if(value < 16){
                $('body > header').css('top',value*-1);
            }
            // $('.contents-wrap .container img').css('top',-1686+(value*10));
            scroll = value;
            
        }
        
    });
});
*/