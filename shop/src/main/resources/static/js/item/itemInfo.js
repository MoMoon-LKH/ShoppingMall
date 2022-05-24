

function cartBtn() {
    let itemId = document.getElementById("itemId").value;
    let itemCount = document.getElementById("purchase_count").value;

    let obj = {
        "itemId": itemId,
        "count": itemCount
    }


    $.ajax({
        url: "/api/cart/item/new",
        type: "POST",
        data: JSON.stringify(obj),
        contentType: "application/json"
    }).done(function (result) {

        location.href = "/member/cart";
    }).error(function (error) {
        alert("장바구니 추가 실패");
    });


}