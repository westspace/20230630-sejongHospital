const infoMenu = (v, type) => {
    console.log(v.className);
    console.log(type);

    for (let i=0; i < document.getElementsByClassName('h-info-menu').length; i++) {
        document.getElementsByClassName('h-info-menu')[i].style.border='none';
    }

    v.style.borderBottom='2px solid black';

    let result;

    switch (type) {
        case '병원정보': result='.h-info';
            break;
        case '응급실정보': result='.e-info';
            break;
        case '메시지': result='.m-info';
            break;
        default:
            break;
    }

    $('html, body').animate({
        scrollTop: $(result).offset().top-100
    },
    'slow');

    //document.querySelector('.h-info-items').style.paddingTop='0px';
    //padding-top: 100px;
}

const areaChk = (area) => {
    let result;
    switch (area) {
        case '충청남도': result = '충남';	
            break;
    
        default: result = area;
            break;
    }
    return result;
}

const areaByStyle = (area) => {
    let result;
    switch (area) {
        case '충남': result='h-area-chungnam'
            break;
        case '세종특별자치시': result='h-area-sejong'
            break;
        default: result=''
            break;
    }
    return result;
}
