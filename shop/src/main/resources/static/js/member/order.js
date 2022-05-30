

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


function orderAjax() {
    let obj = createObj();

    $.ajax({
        url: "/order/cart/addOrder",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(obj)

    }).done(function (result) {
        console.log(result);
        location.href = "/order/successPage";
    }).error(function (error) {
        alert("실패했습니다.");
    });
}


function createObj() {

    let cartDtos = new Array();

    let rows = document.getElementById('item_tbody').getElementsByTagName('tr');

    for (var i = 0; i < rows.length; i++) {
        let cells = rows[i].getElementsByTagName("td");
        let count = cells[2].getElementsByTagName("div")[0].textContent;
        let itemId = cells[3].getElementsByTagName("input")[0].value;

        let item = {
            "itemId": Number(itemId),
            "count": Number(count)
        };

        cartDtos.push(item);
    }



    let object = {
        "total": $("#total_cost").text(),
        "receiveName": document.getElementById("receive_name").value,
        "zipcode": document.getElementById("receive_zipcode").value,
        "address": document.getElementById("receive_addr").value,
        "extraAddr": document.getElementById("receive_addrDetail").value,
        "cartDtos": cartDtos
    }

    return object;
}

