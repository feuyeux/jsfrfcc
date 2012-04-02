(function($) {
	$.fn.handlePic = function(options) {
		var width = options.width;
		var height = options.height;
		var image = options.image;
		this.fadeOut();
		alert(width + " " + height + " " + image);
	};
})(jQuery);
