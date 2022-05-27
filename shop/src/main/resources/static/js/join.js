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
    console.log(member_name.value);
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
