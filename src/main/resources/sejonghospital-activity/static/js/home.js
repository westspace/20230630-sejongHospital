const getNoticeBoard = async () => {
    try {
        let f = await fetch('/api/getNoticeBoard');
        let j = await f.json();
        
        let html = '';

        j.data.map((item)=>{
            console.log(item);
            let body = item.BODY.replace(/(?:\r\n|\r|\n)/g, '<br />');
            html += `
            <div class="que">
                <span class="home-notice-item">
                    <span>
                        <span style="display: flex; align-items: center;">
                            ${item.LABEL == 1 ? "<div style='background-color:red; padding:3px; color:#fff; border-radius:5px'>공지</div>" : ''}
                            <div style="${item.LABEL == 1 && 'margin: 0px 5px;'}">${item.TITLE}</div>
                        </span>

                        <div style="color: #9D9D9D;">${new Date(item.REG_DATE).toLocaleString()}</div>
                    </span>

                    <div class="arrow-wrap">
                        <span class="arrow-top"><img src="/images/expand_less.png" class="expand-size"></span>
                        <span class="arrow-bottom"><img src="/images/expand_more.png" class="expand-size"></span>
                    </div>
                </span>

            </div>
            <div class="anw">
                <span>
                    ${item.IMAGE_PATH ? ` <img src="/articleImage/${item.IMAGE_PATH.split('articleImage/')[1]}" alt="">` : ''}
                    <div>${body}</div>
                </span>
            </div>
            `;
        });

        $('#Accordion_wrap').append(html);

        $(".que").click(function() {
            $(this).next(".anw").stop().slideToggle(300);
            $(this).toggleClass('on').siblings().removeClass('on');
            $(this).next(".anw").siblings(".anw").slideUp(300); // 1개씩 펼치기
        });
        
    } catch (error) {
        console.log(error);
    }   
}