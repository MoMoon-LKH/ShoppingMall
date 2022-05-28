var memberId = document.getElementById("memberId");
var password = document.getElementById("password");
var nickname = document.getElementById("nickname");
var gender = document.getElementsByName("gender");
var phone = document.getElementById("phone");
var birth = document.getElementById("birthday");
var zipcode = document.getElementById("zip_code");
var address = document.getElementById("addr");
var detail = document.getElementById("addrDetail");
var member_name = document.getElementById("member_name");

document.getElementById("sendJoin").addEventListener('click', () =>{

    var obj = {
        "memberId": memberId.value,
        "pw": password.value,
        "nickname": nickname.value,
        "gender": gender.value,
        "phone": phone.value,
        "birthday": birth.value,
        "zipcode": zipcode.value,
        "address": address.value,
        "addrDetail": detail.value,
        "name": member_name.value
    };

    $.ajax({
        url: "/api/member/signup",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(obj),
        success: function (result) {
            if (result) {
                alert("가입이 완료되었습니다.");
                window.location.href = "/member/login";
            }
        },
        error: function () {
            alert("가입 중 오류가 발생하였습니다.")
        }
    });
});


function address_search() {
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

            document.getElementById("zip_code").value = data.zonecode;
            document.getElementById("addr").value = addr;

            document.getElementById("addrDetail").focus();
        }
    }).open();
}
