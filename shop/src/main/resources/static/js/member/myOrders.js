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

        console.log(obj);

        var html ="<tr><td><img src='/static/img/" + obj.imgUrl + "'></td>";

        if(obj.itemCount - 1 > 0)
            html += "<td><div className='order_name'>" + obj.itemName + " 외 " + (obj.itemCount - 1) + "</div>";
        else{
            html += "<td><div className='order_name'>" + obj.itemName + "</div>";
        }

        html += "<div className='order_infos'>";
        html += "<div><span className='order_cost'>" + obj.total + "</span><span>원</span></div>";
        html += "<div className='order_date'>" + obj.createDate + "</div></div>"
        html += "<div className='order_status'>" + obj.orderState + "</div></td>"
        html += "<td><div><button>주문 취소</button></div>";
        html += "<div><button > 교환 요청</button></div></td></tr>";
        table.append(html);
    }
}