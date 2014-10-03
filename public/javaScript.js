$(document).ready(function () {
    getAllPersons();
    getTargetPersonId();
    addNewPerson();
    clearAddPersonDetails();
    deletePerson();
    initRoles();
    addRole();
});

function getAllPersons() {
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

function getTargetPersonId() {
    $("#persons").click(function (e) {
        var id = e.target.id;
        if (isNaN(id)) {
            return;
        }
        getPerson(id);
    });
}

function getPerson(id) {
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
        $("#email").val(person.email);

        var roleString = JSON.stringify(person.roles);
        var roleStringArray = roleString.split("\"", 9999);

        var allRolesString = "";
        var roleDescriptionStr = "";

        for (var i = 0; i < roleStringArray.length; i++) {
            if (roleStringArray[i] === "AssistantTeacher") {
                allRolesString += roleStringArray[i] + ", ";
            }
            if (roleStringArray[i] === "Student" || roleStringArray[i] === "Teacher") {
                allRolesString += roleStringArray[i] + ", ";
                roleDescriptionStr += roleStringArray[i - 4] + ", ";
            }
        }
        $("#role").val(allRolesString);
        $("#roleDescription").val(roleDescriptionStr);
    });
}

function addNewPerson() {
    $("#addNewPersonButton").click(function () {
        var newPerson = {"firstName": $("#newFirstname").val(), "lastName": $("#newLastname").val(), "phone": $("#newPhone").val(), "email": $("#newEmail").val()};
        $.ajax({
            url: "../Person",
            data: JSON.stringify(newPerson),
            type: "POST",
            dataType: 'json',
            error: function (jqXHR, textStatus, erroThrown) {
                alert(textStatus);
            }
        }).done(function () {
            getAllPersons();
            clearAddPersonDetails();
        });
    });
}

function clearAddPersonDetails() {
    $("#newFirstname").val("");
    $("#newLastname").val("");
    $("#newPhone").val("");
    $("#newEmail").val("");
}

function deletePerson() {
    $("#persons").click(function (e) {
        var id = e.target.id;
        if (isNaN(id)) {
            return;
        }
        $("#deletePersonButton").click(function () {
            $.ajax({
                url: "../Person/" + id,
                type: "DELETE",
                dataType: 'json',
                error: function (jqXHR, textStatus, erroThrown) {
                    alert(textStatus);
                }
            }).done(function () {
                getAllPersons();
            });
        });
    });
}

function initRoles() {
    var options = "<option id=Student>Student</option>"
            + "<option id=Teacher>Teacher</option>"
            + "<option id=AssistantTeacher>Assistant Teacher</option>";
    $("#roles").html(options);
}

function addRole() {
    var roleType;
    var pid;
    
    $("#persons").click(function (e) {
        pid = e.target.id;
        if (isNaN(pid)) {
            return;
        }
    });
    
    $("#roles").click(function (e) {
        roleType = e.target.id;
        if (isNaN(roleType)) {
            return;
        }
    });

    $("#addRoleButton").click(function () {
        var role;

        if (roleType === "Student") {
            role = {"semester": $("#roleInput").val(), "roleName": roleType};
        }
        if (roleType === "Teacher") {
            role = {"degree": $("#roleInput").val(), "roleName": roleType};
        }
        if (roleType === "AssistantTeacher") {
            role = {"roleName": roleType};
        }
        $.ajax({
            url: "../Role",
            data: JSON.stringify(role) + pid,
            type: "POST",
            dataType: 'json',
            error: function (jqXHR, textStatus, erroThrown) {
                alert(textStatus);
            }
        }).done(function (roleSchool) {
            getPerson(pid);
            $("#rolenInput").val("");
            getAllPersons();
        });
    });

}