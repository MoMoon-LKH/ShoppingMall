let currentPage = 0;
const dataPerPage = 10;
let obj = {};

$(document).ready(function () {
    getOrderList(currentPage, dataPerPage);

});


function getOrderList(page, size) {

    $.ajax({
        url: "/api/order/list/search/?page="+ page + "&size=" + size,
        type: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(obj)
    }).success(function (result) {
        paging(result.total);
        orderList_rendering(result.list);

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
        let create_date = new Date(obj.createDate);
        let html = "<tr class='list_tr' onclick='managePage(" + obj.orderId + ")'>";

        html += "<td>" + obj.orderId + "</td>";

        if(obj.itemCount - 1 > 0)
            html += "<td class='item_name'>" + obj.itemName + " 외 " + (obj.itemCount - 1) + "</td>";
        else{
            html += "<td class='item_name'>" + obj.itemName + "</td>";
        }

        html += "<td>" + obj.total + "</td>";
        html += "<td>" + create_date.getFullYear() + "-" + (create_date.getMonth() + 1) + "-" + (create_date.getDate() < 10 ? "0" + create_date.getDate() : create_date.getDate()) + "</td>";
        html += "<td>" + obj.orderState + "</td></tr>";

        table.append(html);
    }

}

function search() {
    currentPage = 0;

    obj = {
        "search_word": document.getElementById("search_word").value,
        "start_day": document.getElementById("start_date").value,
        "end_day": document.getElementById("end_date").value
    };

    getOrderList(currentPage, dataPerPage);


}

function searchReset() {
    currentPage = 0;
    document.getElementById("search_word").value = null
    document.getElementById("start_date").value = null
    document.getElementById("end_date").value = null

    obj = {};

    getOrderList(currentPage, dataPerPage);
}

function managePage(orderId) {
    location.href = "/sell/order/manage/" + orderId;
}