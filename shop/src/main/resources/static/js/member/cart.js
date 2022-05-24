
$(document).ready(function () {
    getCart();
});

function getCart(){

    $.ajax({
        url: "/api/cart/list",
        type: "GET"
    }).done(function (result) {
        listRendering(result);
        costUpdate(result)

    }).error(function (error) {
        alert("불러오기에 실패했습니다.");
    });
}

function listRendering(list) {
    const cart_list = $("#cart_list");
    let costTotal = 0;
    cart_list.empty();



    for (let i = 0; i < list.length; i++) {
        let obj = list[i];

        var html = "<tr class='cart_item'>";
        html += "<td><input type='checkbox' value='" + obj.cartItemId + "' checked></td>";
        html += "<td id='cart_name'><img class='itemImg' src='/static/img/" + obj.itemImgUrl + "'><span id='item_name'>" + obj.itemName + "</span></td>";
        html += "<td>" + obj.count + "</td>";
        html += "<td>" + obj.itemCost + "</td>";
        html += "<td>" + (obj.count * obj.itemCost) + "</td>";
        html += "<td>선불</td>";

        costTotal += (obj.count * obj.itemCost);

        cart_list.append(html);
    }

    if (list.length > 0) {
        costUpdate(costTotal);

    } else{

        var html = "<tr>장바구니가 비어있습니다.</tr>"
        cart_list.append(html);
    }

}

function costUpdate(total) {
    const item_total = document.getElementById("item_costs");
    const delivery_cost = document.getElementById("delivery_cost");
    const total_span = document.getElementById("total");

    item_total.text = total;
    delivery_cost.text = 2500;
    total_span.text = (total + 2500);

}