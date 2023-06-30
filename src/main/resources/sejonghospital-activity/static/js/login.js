function validChk(e) {
    if(!e.username.value || !e.password.value) {
        alert('로그인 정보를 입력해주세요.');
        return false;
    }

    
    console.log(e.username.value);
    console.log(e.password.value);


    return true;
}