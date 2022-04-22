

var members;

window.onload = function() {
    getSessionNickname();
};


function logout() {
    $.ajax({
        url: "/api/member/logout",
        type: "POST",
        contentType: "application/json",
        success: function (result) {
            if (result) {
                alert("로그아웃 되었습니다.");
                members = null;
                window.location.href = "/";
            }
        },
        error: function () {
            alert("로그아웃 이상");
            window.location.href = "/";

        }
    });

}

function getSessionNickname() {
    $.ajax({
        url: "/api/member/check",
        type: "GET",
        dataType:"text",
        success: function (result) {
            members = result;
            tag(members)
        },
        error: function (request,status,error) {
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}

function tag(member) {
    if (!member) {
        console.log(false);
        $(".not_login").show();
        $(".on_login").hide();
    } else{
        console.log(true)
        $(".on_login").show();
        $(".not_login").hide();

        document.getElementById('user_nickname').textContent = member;

    }
}