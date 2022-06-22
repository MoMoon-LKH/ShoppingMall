function addStock() {

    let obj = {
        "itemId": $("#item_id").val(),
        "count": $("#stock_count").val()
    }

    console.log(obj);

    $.ajax({
        url: "/api/item/stock/add",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(obj)
    }).done(function (result) {
        document.getElementById("stock_count_div").textContent = result;
        $("#stock_count").val("");

    });
}

function removeStock() {

    let obj = {
        "itemId": $("#item_id").val(),
        "count": $("#stock_count").val()
    }

    $.ajax({
        url: "/api/item/stock/remove",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(obj)
    }).done(function (result) {
        document.getElementById("stock_count_div").textContent = result;
        $("#stock_count").val("");
    });
}

function updateStock() {

    let obj = {
        "itemId": $("#item_id").val(),
        "count": $("#stock_count").val()
    }

    $.ajax({
        url: "/api/item/stock/update",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(obj)
    }).done(function (result) {
        document.getElementById("stock_count_div").textContent = result;
        $("#stock_count").val("");
    });
}

function updateItem() {

    let obj = {
        id: $("#item_id").val(),
        name: $("#item_name").val(),
        count: 0,
        cost: $("#item_cost").val()
    }

    $.ajax({
        url: "/api/item/update",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(obj)
    }).done(function (result) {
        document.getElementById("item_name_div").textContent = result.name;
        document.getElementById("item_cost_div").textContent = result.cost;
        $("#item_name").val("");
        $("#item_cost").val("");
    }).error(function (error) {
        alert("상품 수정에 실패했습니다.");
    });

}

function changeImgUrl() {

}