function update_state() {
    let value = $("#state_select option:selected").val();
    let orderId = $("#order_id").text();
    const stateTxt = document.querySelector("#state_txt");


    $.ajax({
        url: "/api/order/state?state=" + value + "&orderId=" + orderId,
        type: "POST"
    }).done(function (result) {
        console.log(result);
        stateTxt.textContent = result;
    }).error(function () {
        alert("주문 상태 변경 실패")
    });
}


function update_delivery() {

    let name = $("#update_name");
    let zipcode = $("#update_zipcode");
    let address = $("#update_address");
    let detail = $("#update_detailAddr");

    let obj = {
        "orderId": $("#order_id").text(),
        "name": name.val(),
        "zipcode": zipcode.val(),
        "address": address.val(),
        "detail_addr": detail.val() ,
    }

    $.ajax({
        url: "/api/order/delivery/update",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(obj)
    }).done(function (result) {
        document.getElementById("name").textContent = result.name;
        document.getElementById("zipcode").textContent = result.zipcode;
        document.getElementById("address").textContent = result.address;
        document.getElementById("details").textContent = result.detail_addr;

        name.val("");
        zipcode.val("");
        address.val("");
        detail.val("");

    }).error(function (error) {
        alert("배송지 수정에 실패");
    });

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

            document.getElementById("update_zipcode").value = data.zonecode;
            document.getElementById("update_address").value = addr;

            document.getElementById("update_detailAddr").value = '';
            document.getElementById("update_detailAddr").focus();
        }
    }).open();

}