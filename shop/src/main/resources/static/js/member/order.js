

function checkOne(el) {
    const checkboxes = document.getElementsByName("payment");
    checkboxes.forEach(cb => cb.checked = false);
    el.checked = true;
}

function search_addr() {

    new daum.Postcode({
        oncomplete: function(data) {
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            document.getElementById("receive_zipcode").value = data.zonecode;
            document.getElementById("receive_addr").value = addr;

            document.getElementById("receive_addrDetail").value = '';
            document.getElementById("receive_addrDetail").focus();
        }
    }).open();

}

function cancelAction() {
    history.back();
}

