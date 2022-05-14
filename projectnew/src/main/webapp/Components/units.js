$(document).ready(function() 
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateItemForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidRecIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "UnitsAPI", 
 type : type, 
 data : $("#formItem").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemSaveComplete(response.responseText, status); 
 } 
 }); 
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
$("#hidRecIDSave").val($(this).data("recordID")); 
 $("#accountNo").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#consumerName").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#address").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#district").val($(this).closest("tr").find('td:eq(3)').text()); 
 $("#consumedUnits").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#year").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#month").val($(this).closest("tr").find('td:eq(2)').text()); 
});

$(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "UnitsAPI", 
 type : "DELETE", 
 data : "recordID=" + $(this).data("recordID"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemDeleteComplete(response.responseText, status); 
 } 
 }); 
});
// CLIENT-MODEL================================================================
function validateItemForm() 
{ 
// CODE
if ($("#accountNo").val().trim() == "") 
 { 
 return "Insert accountNo."; 
 } 
// NAME
if ($("#consumerName").val().trim() == "") 
 { 
 return "Insert Name."; 
 } 
// NAME
if ($("#address").val().trim() == "") 
 { 
 return "Insert address."; 
 } 
// NAME
if ($("#district").val().trim() == "") 
 { 
 return "Insert district."; 
 } 
// NAME
if ($("#consumedUnits").val().trim() == "") 
 { 
 return "Insert consumedUnits."; 
 } 
// NAME
if ($("#year").val().trim() == "") 
 { 
 return "Insert year."; 
 } 
// NAME
if ($("#month").val().trim() == "") 
 { 
 return "Insert month."; 
 } 

return true; 
}

function onItemSaveComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
 $("#hidItemIDSave").val(""); 
 $("#formItem")[0].reset(); 
}


function onItemDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}




