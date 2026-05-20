jQuery(document).ready(function($) {
	$('.table tbody').paginathing({
		perPage : 100,
		insertAfter : '.table',
		pageNumbers : true
	});
});

jQuery(document).ready(function($) {
var options = {
	valueNames : [ 'search', {} ],
};
var userList = new List('users', options);
$('#checkAll').click(function() {
	$('input:checkbox').prop('checked', this.checked);
});

});
jQuery(document).ready(function($) {
var count = 0;
var favorite = new Array();
$.each($("input[name='CheckedIDList']:checked"), function() {
	favorite.push($(this).val());
});
document.getElementById("hiddenField").value = favorite;
count = favorite.length;
$("b").html(count);
favorite = null;
$('input[type="checkbox"]').change(function(event) {
	var favorite = new Array();
	var count;
	$.each($("input[name='CheckedIDList']:checked"), function() {
		favorite.push($(this).val());
	});
	document.getElementById("hiddenField").value = favorite;
	count = favorite.length;
	$("b").html(count);
});
if (!favorite.length == 0) {
	count = count + favorite.length;
}
});
$('#checkAll').click(function() {
$('input:checkbox').prop('checked', this.checked);
});

