let currentPage = 0;
const dataPerPage = 20;
let total;

$(document).ready(function () {
    getTotal();
    getList(currentPage);

});


function getTotal(){
    $.ajax({
        url: "/api/item/total",
        type: "GET",
        success: function (result) {
            total = result;
            paging(result);
        },
    })
}


function getList(page) {
    $.ajax({
        url: "/api/item/list"+"?page=" + page +"&size=" + dataPerPage,
        type: "GET"
    }).done(function (result) {
        itemList_rendering(result);
    }).error(function (error) {
        alert("불러오기에 실패했습니다.");
    });
}


function itemList_rendering(result) {

    const item_list = $("#item_list");
    let row = result.length / 5;
    let count = 0;

    item_list.empty();

    for (let i = 0; i < row; i++) {
        var html = "<tr class='item_row'>";
        let column = result.length - (i * 5) >= 5 ? 5 : result.length % 5;
        for (let j = 0; j < column; j++) {
            let object = result[count];
            html += "<td class='items' >";
            html += "<a href='/item/"+ object.id + "'>";
            html += "<img id='itemImg' style='width: 200px; height: 225px;' src='/image/" + object.imgUrl + "' onerror='getErrorImg(this);'/>";
            html += "<div class='item_el' id='item_name'>" + object.name + "</div>";
            html += "</a>";
            html += "<div class='item_el' id='item_cost'>" + object.cost+ "원" + "</div>";
            html += "</td>";
            count++;
        }

        html += "</tr>";
        item_list.append(html);
    }


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
            pages.append("<li class='page_num' onclick='getList(this.value)' value='" + (i - 1) + "'>" + i + "</li>");
        }
    }

    if (last < totalPage) {
        pages.append("<li class='page_num' onclick='next_btn(this.value)' value='" + (last) + "'>다음</li>");
    }

}

function prev_btn(num) {
    currentPage = num - 1;
    paging(total);
    getList(num);
}

function next_btn(num) {
    currentPage = num;
    paging(total);
    getList(num);
}

function getErrorImg(image) {
    image.onerror = "";
    image.src = "/image/error.png";
    return true;
}