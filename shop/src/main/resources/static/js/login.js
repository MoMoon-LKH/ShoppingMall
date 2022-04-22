var id = document.getElementById("memberId");
var password = document.getElementById('password');


function login() {
    var obj = {
        "loginId": id.value,
        "password": password.value
    };

    $.ajax({
        url: "/api/member/login",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(obj),
        success: function (result) {
            if (result) {
                alert("로그인이 완료되었습니다.");
                window.location.href = "/";
            }
        },
        error: function () {
            alert("로그인에 실패하였습니다.");
        }
    });
}