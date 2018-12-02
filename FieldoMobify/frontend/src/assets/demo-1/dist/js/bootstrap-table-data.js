/*Bootstrap Table Init*/

var data = [{
    "name": "Ajsoft Solutions",
        "stargazers_count": "AC Repairing",
        "forks_count": "11/08/18 to 22/08/18",
        "person1": "Mr. Akasha Somani",
		"person2": "Mr. Akasha Somani",
		"status": "Completed"
}, {
    "name": "Fieldo Pvt Ltd.",
        "stargazers_count": "Maintinance",
        "forks_count": "01/11/18 to 01/11/18",
        "person1": "Mr. Joshep",
		"person2": "Mr. Joshi",
		"status": "Pending"
}, 
{
    "name": "Art Studio",
        "stargazers_count": "Computer installation",
        "forks_count": "31/03/18 to 18/04/18",
        "person1": "Mr. Amar Datar, Mr. Sagar Patil",
		"person2": "Mr. Shridhar Awate",
		"status": "Pending"
},
 
];

$(function () {
	"use strict";
	$('#table_js,#table_js_1,#table_js_2').bootstrapTable({
        data: data
    });
	
	/*Scroll to top*/
	$(document).on('click','#mybtn_top',function(e) {
        $('#table_js').bootstrapTable('scrollTo', 0);
		return false;
    });
    
	/*Scroll to index*/
	$(document).on('click','#mybtn_row',function(e) {
        var index = +$('#row_index').val(),
            top = 0;
        $('#table_js').find('tbody tr').each(function (i) {
        	if (i < index) {
            	top += $(this).height();
            }
        });
        $('#table_js').bootstrapTable('scrollTo', top);
		return false;
    });
    
	/*Scroll to bottom*/
	$(document).on('click','#mybtn_btm',function(e) {
        $('#table_js').bootstrapTable('scrollTo', 'bottom');
		return false;
    });

});