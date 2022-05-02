
function logout() {
    $.ajax({
        url: "/api/member/logout",
        type: "POST",
        success: function (result) {
            if (result) {
                alert("로그아웃 되었습니다.");
                document.location.reload();
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
