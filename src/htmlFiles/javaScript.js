$(document).ready(function () {
    alert("READY");
    console.log("Started!");
    getAllPersons();
});

function getAllPersons() {
    console.log("Inde i getAllPersons!");
    $.ajax({
        url: "../Person",
        type: "GET",
        dataType: 'json',
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus);
        }
    }).done(function (persons) {
        var options = "";
        persons.forEach(function (person) {
            options += "<option id=" + person.id + ">" + person.firstName + ", " + person.lastName + "</option>";
        });
        $("#persons").html(options);
        clearDetails();
    });
}

function clearDetails() {
    $("#id").val("");
    $("#fname").val("");
    $("#lname").val("");
    $("#phone").val("");
}