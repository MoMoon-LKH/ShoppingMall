let imgArray = new Array(2);

function addStock() {

    let obj = {
        "itemId": $("#item_id").val(),
        "count": $("#stock_count").val()
    }


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
        "count": $("#stock_count").val(),
        "etrTxt": $("#update_etr").val()
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

    let itemId = $("#item_id").val();
    let imgForm = new FormData();
    let obj = {
        "id": itemId,
        "name": $("#item_name").val(),
        "count": 0,
        "cost": $("#item_cost").val(),
        "etrTxt": $("#update_etr").val()
    }

    imgForm.append("img", imgArray[0]);
    imgForm.append("description", imgArray[1]);

    $.ajax({
        url: "/api/item/update/img/" + itemId,
        type: "POST",
        enctype: "multipart/form-data",
        processData: false,
        contentType: false,
        data: imgForm,
    }).done(function (result) {
        document.getElementById("item_img").src = result["imgUrl"];
        document.getElementById('descriptionImg').src = result["description"];
    }).error(function (error) {
        alert("이미지 업로드에 실패")
    });

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

    window.location.href = "/sell/item/" + itemId;
}

function changeImgUrl(input, type) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {

            if (type === 0) {
                document.getElementById("item_img").src = e.target.result;
                imgArray[0] = input.files[0];
            } else{
                document.getElementById('descriptionImg').src = e.target.result;
                document.getElementById('descriptionImg').style.display = "block";
                    imgArray[1] = input.files[0];
            }
        };
        reader.readAsDataURL(input.files[0]);
    }
}