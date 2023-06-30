const managerController = (t) => {
    document.querySelector('.admin-hospital-container').style.display='none';
    document.querySelector('.admin-user-container').style.display='none';
  $('.admin-notice-container').css({ 'display' : 'none' });
    
    switch (t) {
        case 'u': document.querySelector('.admin-user-container').style.display='block';
            break;
        case 'h': document.querySelector('.admin-hospital-container').style.display='block';
            break;
        case 'n': $('.admin-notice-container').css({ 'display' : 'block' });
            break;
        default:
            break;
    }
}

const getUserList = async () => {
    try {
        
        let f = await fetch('/api/userList');
        let j = await f.json();
    
        return j.data;
        //console.log(j);
        // let html = '';

        // j.data.map((item)=>{
        //     html += `
        //         <span class='hospital-title-wrap'>
        //             <div>사용자이름</div>
        //             <div>가입날짜</div>
        //             <div>소속</div>
        //             <div>부서</div>
        //             <div>유저권한</div>
        //             <div></div>
        //         </span>

        //         <span class='hospital-body-wrap'>
        //             <div>${item.NAME}</div>
        //             <div>${new Date(item.REG_DATE).toLocaleString()}</div>
        //             <div>${item.AGENCY}</div>
        //             <div>${item.DEPARTMENT}</div>
        //             <div>${roleChk(item.ROLE_CODE)}</div>

        //             <div>
        //                 <button onclick="setLocation(this);">승인</button>
                       
        //             </div>
        //         </span>
        //     `;
        // });

        // document.querySelector('.admin-user-list').innerHTML=html;
    } catch (error) {
        console.log(error);
    }
}
    
const roleChk = (e) => {

    let result;
    switch (e) {
        case 1: result = '일반 사용자'
            break;
        case 2: result = '진료과 의료진'
            break;
        case 3: result = '관리자(admin)'
            break;
        default:
            break;
    }

    return result;
}

const getNoticeArticleList = async () => {
    try {
        
        let f = await fetch('/api/getNoticeArticleList');
        let j = await f.json();
    
        return j.data;
      
    } catch (error) {
        console.log(error);
    }
}
    

const adminNoticeWrite = async () => {
    // console.log($('#noticeBody').val());

    const imageInput = $("#file")[0];

    let form = new FormData();

    form.append('TITLE', $('#noticeTitle').val());
    form.append('BODY', $('#noticeBody').val());
    form.append('LABEL', $('.emChk').prop("checked") == true ? '1' : '0');
    form.append('formFiles', imageInput.files[0]);

    try {
        let f = await fetch('/api/noticeArticle', {
            method:'post',
            body: form
        });
        let j = await f.json();

        console.log(j);

        if(j.code.includes('S-')) {
            alert(j.msg);
            location.reload();
        }
        
    } catch (error) {
        
    }
}

const adminNoticeModify = async () => {
    // console.log($('#noticeBody').val());

    const imageInput = $("#file")[0];

    let form = new FormData();

    form.append('TITLE', $('#noticeTitle').val());
    form.append('BODY', $('#noticeBody').val());
    form.append('ARTICLE_CODE', document.querySelector('#article_code').value);
    form.append('LABEL', $('.emChk').prop("checked") == true ? '1' : '0');
    form.append('formFiles', imageInput.files[0]);

    try {
        let f = await fetch('/api/noticeModify', {
            method:'post',
            body: form
        });
        let j = await f.json();

        console.log(j);
        
        if(j.code.includes('S-')) {
            alert(j.msg);
            location.reload();
        }

    } catch (error) {
        
    }
}


async function logout(){
	if(!confirm("로그아웃 하시겠습니까?"))return;

	await fetch('/comn/logout', {
        method:'post',
    })
	.then(()=>{location.replace('/login')})
	.catch(e=>console.log(e));
};