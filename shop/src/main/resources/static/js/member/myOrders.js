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

        var html ="<tr><td><a class='order_a' href='/order/info?orderId="+ obj.orderId +"'><img src='/static/img/" + obj.imgUrl + "'></a></td>";

        if(obj.itemCount - 1 > 0)
            html += "<td><a class='order_a' href='/order/info?orderId="+ obj.orderId +"'><div class='order_name'>" + obj.itemName + " 외 " + (obj.itemCount - 1) + "</div></a>";
        else{
            html += "<td><a class='order_a' href='/order/info?orderId="+ obj.orderId +"'><div class='order_name'>" + obj.itemName + "</div></a>";
        }

        html += "<div class='order_infos'>";
        html += "<div><span class='order_cost'>" + obj.total + "</span><span>원</span></div>";
        html += "<div class='order_date'>" + obj.createDate + "</div></div>"
        html += "<div class='order_status'>" + obj.orderState + "</div></td>"
        html += "<td><div><button class='order_btns'>주문 취소</button></div>";
        html += "<div><button class='order_btns'> 교환 요청</button></div></td></tr>";
        table.append(html);
    }
}
