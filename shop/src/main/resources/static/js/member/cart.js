
var memberId = document.getElementById("memberid").value;

$(document).ready(function () {
    $.ajax({
        url: "/api/cart/" + memberId,
        type: "GET"
    }).done(function (result) {
        //성공 시 로직 작성

    }).error(function (error) {
        alert("불러오기에 실패했습니다.");
    });
});


function listRendering(list) {

}

function costUpdate() {
    const item_total = document.getElementById("item_costs");
    const delivery_cost = document.getElementById("delivery_cost");
    const total_span = document.getElementById("total");


}