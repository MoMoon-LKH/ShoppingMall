let currentPage = 0;
const dataPerPage = 5;
let id = document.getElementById("get_memberId").value;
let total;

$(document).ready(function () {
    getTotal(id);

    if (total !== 0) {
        getList(currentPage, dataPerPage);
    }


});

function getTotal(memberId) {
    $.ajax({
        url: "/api/item/total/" + memberId,
        type: "GET",
        success: function (result) {
            total = result;
            paging(result);
        },
    })
}


function getList(page, size) {

    $.ajax({
        url: "/api/item/" + id +"?page="+ page + "&size=" + size,
        type: "GET",
        success: function (result) {
            list_rendering(result);
        },
        error: function () {
            alert("불러오기에 실패했습니다.")
        },

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

    const nextNum = last + 1;
    const prevNum = first - 1;

    if (totalPage < 1) {
        first = last;
    }


    const pages = $("#pages");
    pages.empty();


    if (first > 5) {
        pages.append("<li class='page_num' onclick='prev_btn(this.value)' value='" + (first - 1) + "'>이전</li>");
    }
    if(last > 0) {
        for (var i = first; i <= last; i++) {
            pages.append("<li class='page_num' onclick='getList(this.value, dataPerPage)' value='" + (i - 1) + "'>" + i + "</li>");
        }
    }

    if (last < totalPage) {
        pages.append("<li class='page_num' onclick='next_btn(this.value)' value='" + (last) + "'>다음</li>");
    }

}

function prev_btn(num) {
    currentPage = num - 1;
    paging(total);
    getList(num, dataPerPage);
}

function next_btn(num) {
    currentPage = num;
    paging(total);
    getList(num, dataPerPage);
}


function list_rendering(result) {
    const table = $("#item_list");
    table.empty();

    if(result.length > 0) {
        table.append("<tr><td>상품 번호</td><td>상품명</td><td>가격</td><td>재고량</td><td>생성 일자</td></tr>");

        for (let i = 0; i < result.length; i++) {
            let object = result[i]

            table.append(
                "<tr onclick='getItemPage(" + object.id +")'>" +
                    "<td>" + object.id + "</td>" +
                    "<td>" + object.name + "</td>" +
                    "<td>" + object.cost + "</td>" +
                    "<td>" + object.count + "</td>" +
                    "<td>" + object.createDate + "</td>" +
                "</tr>"
            );

        }
    } else{
        table.append("<tr><td>데이터가 없습니다.</td></tr>")
    }

}

function getItemPage(id) {
    location.href = "/sell/item/" + id;
}