let currentPage = 0;
const dataPerPage = 5;
let memberId;
let total;

$(document).ready(function () {
    memberId = document.getElementById('memberId').value;
    getTotal(memberId);

    if (total !== 0) {
        getList(currentPage, dataPerPage);
    }


});

function getTotal(memberId) {
    $.ajax({
        url: "/api/item/total/" + memberId,
        type: "GET",
        success: function (result) {
            paging(result);
        },
    })
}

function getList(page, size) {

    $.ajax({
        url: "/api/item/" + memberId +"?page="+ page + "&size=" + size,
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
    if (last > totalPage) {
        last = totalPage;
    }

    let first = last - (pageCount - 1) <= 0 ? 1 : last - (pageCount - 1)

    const nextNum = last + 1;
    const prevNum = first - 1;

    if (totalPage < 1) {
        first = last;
    }

    const pages = $("#pages");
    pages.empty();


    if (first > 5) {
        pages.append("<li class='page_prev_btn' onclick='prev_btn(this.value)' value='"+ (first - 1) + "'>이전</li>")
    }
    if(last > 0) {
        for (var i = first; i <= last; i++) {
            pages.append("<li class='page_num' onclick='getList(this.value, dataPerPage)' value='"+ (i - 1) + "'>" + i + "</li>")
        }
    }

    if (last < totalPage) {
        pages.append("<li class='page_next_btn' onclick='next_btn(this.value)' value='"+ last + 1 +"'>다음</li>")
    }

}

function prev_btn(first) {
    currentPage = first;
    paging(total);
    getList(first, dataPerPage);
}

function next_btn(last) {
    currentPage = last;
    paging(total);
    getList(last, dataPerPage);
}


function list_rendering(result) {
    const table = $("#item_list");
    table.empty();

    if(result.length > 0) {
        table.append("<tr><td>상품 번호</td><td>상품명</td><td>가격</td><td>재고량</td><td>생성 일자</td></tr>");

        for (let i = 0; i < result.length; i++) {
            let object = result[i]

            table.append(
                "<tr>" +
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