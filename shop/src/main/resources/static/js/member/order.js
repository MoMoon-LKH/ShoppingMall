

function checkOne(el) {
    const checkboxes = document.getElementsByName("payment");
    checkboxes.forEach(cb => cb.checked = false);
    el.checked = true;
}

function search_addr() {

}

function cancelAction() {
    history.back();
}