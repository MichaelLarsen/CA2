$(document).ready(function () {
    alert("READY");
    console.log("Started!");
    getAllPersons();
    initPersons();
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
    });
}

function initPersons() {
    $("#persons").click(function (e) {
        var id = e.target.id;
        if (isNaN(id)) {
            return;
        }
        updateDetails(id);
    });
}

function updateDetails(id) {
    $.ajax({
        url: "../Person/" + id,
        type: "GET",
        dataType: 'json',
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.getResonseText + ": " + textStatus);
        }
    }).done(function (person) {
        $("#id").val(person.id);
        $("#firstname").val(person.firstName);
        $("#lastname").val(person.lastName);
        $("#phone").val(person.phone);
    });
}

function addNewPerson(){
    $.ajax({
        url: "../Person",
        type: "POST",
        dataType: 'json',
        error: function(jqXHR, textStatus, erroThrown){
            alert(textStatus);
        }
        
    });
}