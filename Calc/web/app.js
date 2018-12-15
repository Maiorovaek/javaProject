function sendRequest(formParam) {
    var a = formParam.a.value;
    var b = formParam.b.value;
    var c = formParam.c.value;
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var xmlDoc = this.responseText;
            addRow(JSON.parse(xmlDoc));

        }

    }

    xhttp.open("GET", "calculateEq?a=" + a + "&b=" + b + "&c=" + c, true);
    xhttp.send();

}

function addRow(param) {
    var row = document.createElement('tr');
    var paramArray = [param.a, param.b, param.c, param.res];
    for (var i = 0; i < 4; i++) {
        var col = document.createElement('td');
        row.appendChild(col);
        col.innerHTML = paramArray[i];
    }

    row.onclick = function SomeDeleteRowFunction() {
        var td = event.target;
        var tr = td.parentNode;
        tr.parentNode.removeChild(tr);
    };

    var table = document.getElementById("valuesResult");
    table.appendChild(row);
    document.getElementById("table");
}


