let orderTotal;
let currentPage = 0;
const dataPerPage = 10;

$(document).ready(function () {
    getOrderTotal();

});


function getOrderTotal() {
    $.ajax({
        url: "/api/order/total",
        type: "GET"
    }).done(function (result) {
        orderTotal = result;
        paging(result);

        if (result > 0) {
            getOrderList(currentPage, dataPerPage);

        }

    }).error(function (error) {
        alert("불러오기에 실패했습니다");
    });
}

function getOrderList(page, size) {
    $.ajax({
        url: "/api/order/list?page=" + page + "&size=" + size,
        type: "GET"
    }).success(function (result) {
        orderList_rendering(result);
    }).error(function (error) {
        alert("불러오기에 실패했습니다.");
    });
}

function paging(total) {

    const pageCount = 5;
    const now = currentPage + 1;
    const totalPage = Math.ceil(total / dataPerPage);
    const pageGroup = Math.ceil(now / pageCount);

    let last = pageGroup * pageCount;
    let first = last - (pageCount - 1) <= 0 ? 1 : last - (pageCount - 1);

    if (last > totalPage) {
        last = totalPage;
    }

    if (totalPage < 1) {
        first = last;
    }

    const pages = $("#order_pages");
    pages.empty();

    if (first > 5) {
        pages.append("<li class='page_num' onclick='order_prevBtn(this.value)' value='" + (first - 1) + "'>이전</li>");
    }
    if (last > 0) {
        for (var i = first; i <= last; i++) {
            pages.append("<li class='page_num' onclick='getOrderList(this.value, dataPerPage)' value='" + (i - 1) + "'>" + i + "</li>");
        }
    }

    if (last < totalPage) {
        pages.append("<li class='page_num' onclick='order_nextBtn(this.value)' value='" + (last) + "'>다음</li>");
    }



}

function order_prevBtn(num) {
    currentPage = num - 1;
    paging(orderTotal, 1);

}

function order_nextBtn(num) {
    currentPage = num;
    paging(orderTotal, 1);
}


function orderList_rendering(result) {
    const table = $("#order_list");

    table.empty();

    for (let i = 0; i < result.length; i++) {
        let obj = result[i];
        let html = "<tr>";

        html += "<td>" + obj.orderId + "</td>";

        if(obj.itemCount - 1 > 0)
            html += "<td>" + obj.itemName + " 외 " + (obj.itemCount - 1) + "</td>";
        else{
            html += "<td>" + obj.itemName + "</td>";
        }

        html += "<td>" + obj.total + "</td>";
        html += "<td>" + obj.createDate + "</td>";
        html += "<td>" + obj.orderState + "</td>";

        table.append(html);
    }

}