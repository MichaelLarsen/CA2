$(document).ready(function () {
//    alert("READY");
//    console.log("Started!");


    getAllPersons();
    getTargetPersonId();
    addNewPerson();
    clearAddPersonDetails();
    deletePerson();
    initRoles();
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

//        roleStringArray.forEach(function (newString) {
//            console.log(newString);
//            if (newString === "Student" || newString === "Teacher" || newString === "AssistentTeacher") {
//                allRolesString += newString + " ";
//                roleDescriptionStr += roleStringArray[3] + " ";
//            }
//            for (var i = 0; i < roleStringArray.length; i++) {
//                
//            }
//            if (roleStringArray[i] ===  ) {
//                
//            }
//        });




//        console.log(JSON.stringify(person.roles));
//
//        var o = person.roles;
//        console.log("ArrayA: " + o);
//        var a = Array.prototype.slice.call(o);
//
//        console.log("ArrayB: " + a);
//
//        console.log("ArrayC: " + JSON.stringify(a));
//        console.log("Stringify: " + JSON.stringify(person.roles[0]));
//
//
//        var role = JSON.stringify(o[0]);;
//        role.roleName;

//        if (roleType === "Student") {
//            role = JSON.stringify(o[0]);
//        }
//        if (roleType === "Teacher") {
//            role = JSON.stringify(o[0])};
//        }
//        if (roleType === "AssistantTeacher") {
//            role = JSON.stringify(o[0]);
//        }









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


//    var roleStudent = {};
//    var roleTeacher = {};
//    var roleAssistantTeacher = {};

    var options = "<option id=Student>Student</option>"
            + "<option id=Teacher>Teacher</option>"
            + "<option id=AssistantTeacher>Assistant Teacher</option>";
    $("#roles").html(options);

//    $("#newFirstname").val("");
//    $("#newLastname").val("");
//    $("#newPhone").val("");
//    $("#newEmail").val("");
}

//document.getElementById()

function addRole() {
    $("#roles").click(function (e) {
        var roleType = e.target.id;
        if (isNaN(roleType)) {
            return;
        }
        $("#addRoleButton").click(function () {
            var role;
            if (roleType === "Student") {
                role = {"rolename": +roleType, "semester": $("#roleInput").val()};
            }
            if (roleType === "Teacher") {
                role = {"rolename": +roleType, "degree": $("#roleInput").val()};
            }
            if (roleType === "AssistantTeacher") {
                role = {"rolename": +roleType};
            }
            $.ajax({
                url: "../Role",
                data: JSON.stringify(role),
                type: "POST",
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