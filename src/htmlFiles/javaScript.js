$(document).ready(function() {
    $("#getAll").click(getAllPersons());
      });
      
      function getAllPersons(){
          $.ajax({
          url: "../person",
          type: "GET",
          dataType: 'json'
//          error: function(jqXHR, textStatus, errorThrown) {
//            alert(textStatus);
//          }
        }).done(function(persons) {
          var options = "";
          persons.forEach(function(person) {
            options += "<option id=" + person.id + ">" + person.fName[0] + ", " + person.lName + "</option>";
          });
          $("#all_persons").html(options);
        });
      }

