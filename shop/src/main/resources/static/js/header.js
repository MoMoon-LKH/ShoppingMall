var not_login_header = document.getElementsByClassName("not_login")[0];
var login_header = document.getElementsByClassName("on_login")[0];

$(document).ready(function () {
    $.ajax({
        url: "/api/member/usernickname",
        type: "GET",
        contentType: "application/json"
    }).done(function (result) {
        if(!result) {

        }else{
            document.getElementById("memberId").value = result["id"];
            document.getElementById("user_nickname").textContent = result["nickname"];
            id = result["id"];
        }
    }).error(function (error) {

    });
});


function logout() {
    $.ajax({
        url: "/api/member/logout",
        type: "POST",
        success: function (result) {
            if (result) {
                alert("로그아웃 되었습니다.");
                location.href = "/";
            } else {
                alert("로그아웃 실패");
            }
        },
        error: function () {
            alert("로그아웃 이상");
            document.location.reload();

        }
    });

}
