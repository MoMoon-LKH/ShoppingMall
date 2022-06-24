let page = 0;
let pageCount = 5;

$(document).ready(function () {
    ajaxOrders();
});


function ajaxOrders() {
    $.ajax({
        url: "/order/my/list?page=" + page + "&size=" + pageCount,
        type: "GET"
    }).done(function (result) {
        list_rendering(result);
        page++;
    }).error(function () {
        alert("불러오기에 실패했습니다.");
    });

}


function list_rendering(list) {
    const table = $("#orders_tbody");

    for (var i = 0; i < list.length; i++) {


        let obj = list[i];

        var html ="<tr><td><a class='order_a' href='/order/info?orderId="+ obj.orderId +"'><img src='/image/" + obj.imgUrl + "'></a></td>";

        if(obj.itemCount - 1 > 0)
            html += "<td><a class='order_a' href='/order/info?orderId="+ obj.orderId +"'><div class='order_name'>" + obj.itemName + " 외 " + (obj.itemCount - 1) + "</div></a>";
        else{
            html += "<td><a class='order_a' href='/order/info?orderId="+ obj.orderId +"'><div class='order_name'>" + obj.itemName + "</div></a>";
        }

        html += "<div class='order_infos'>";
        html += "<div><span class='order_cost'>" + obj.total + "</span><span>원</span></div>";
        html += "<div class='order_date'>" + obj.createDate + "</div></div>"

        if(obj.orderState != "CANCEL") {
            html += "<div class='order_status'>" + obj.orderState + "</div></td>"
            html += "<td><div><button class='order_btns' onclick='order_cancel(this, " + obj.orderId + ")'>주문 취소</button></div>";
            html += "<div><button class='order_btns'> 교환 요청</button></div></td></tr>";
        } else{
            html += "</td>";
            html += "<td><div class='orderStatus_txt'>주문취소</div></td>";
        }

        table.append(html);
    }

}


function order_cancel(btn, orderId) {
    let order = $(btn).parentsUntil("tbody").children('td');
    let orderStatus = order[1].querySelector('.order_status');
    let order_tr = $(btn).parentsUntil("tbody");

    $.ajax({
        url: "/order/cancel?orderId=" + orderId,
        type: "POST",
    }).done(function (result) {
        if (result == "CANCEL") {

            orderStatus.remove();
            order[2].remove('div');

            let string = "<td><div class='orderStatus_txt'>주문취소</div></td>";

            order_tr.append(string);
        }
    }).error(function (error) {
        alert("주문 취소에 실패했습니다.")
    });

}