
//jQuery time
var current_fs, next_fs, previous_fs; //fieldsets
var left, opacity, scale; //fieldset properties which we will animate
var animating; //flag to prevent quick multi-click glitches

function PhoneValidation(phoneNumber) {
	var reg = /((09|03|07|08|05|01)+([0-9]{8})\b)/g; 
	if (!reg.test(phoneNumber)) return false;
    return true;
}  

function checkEmail(email) {
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    if (!reg.test(email)) return false;
    return true;
}

$(".next").click(function(){
	current_fs = $(this).parent().parent();
	next_fs = $(this).parent().parent().next();

	if (current_fs.attr('id') === 'section-info') {
		// Check validate.
		$('#error-name').text('');
		$('#error-birthDay').text('');
		$('#error-phone').text('');
		$('#error-email').text('');

		let name = $('#txt-name').val();
		let birthDay = $('#txt-birthDay').val();
		let address = $('#txt-address').val();
		let phone = $('#txt-phone').val();
		let email = $('#txt-email').val();

		let check = 0;

		if (!name) {
			check = 1;
			$('#error-name').text('không được để trống !');
		}

		if (!birthDay) {
			check = 1;
			$('#error-birthDay').text('không được để trống !');
		}

		if (!phone) {
			check = 2;
			$('#error-phone').text('Không được để trống !');
		} else if (!PhoneValidation(phone)) {
			check = 2;
			$('#error-phone').text('Không đúng định dạng !');
		}

		// // TODO : xem lai phan bị lệch email và phone.
		// let txt_email = '';

		// if (!email) {
		// 	txt_email = 'không được để trống !';
		// 	$('#error-email').html(txt_email);	
		// 	check = 1;
		// } else if (!checkEmail(email)) {
		// 	txt_email = 'Nhập sai định dạng !';
		// 	$('#error-email').html(txt_email);	
		// 	check = 1;
		// }

		if (check !== 0) return false;
		$('#name').text('Họ và tên : ' + name);
		$('#birthday').text('Ngày sinh : ' + birthDay);
		$('#address').text('Địa chỉ : ' + address);
		$('#phone').text('Số điện thoại : ' + phone);
		$('#email').text('Email : ' + email);
	}

	if(animating) return false;
	animating = true;

	// change image background.
	if (next_fs.attr('id') === 'section-form') {
		debugger;
		$('#background01').removeClass('show');
		$('#background02').addClass('show');
	}

	const typeRegister = $("#type").val();


	if (next_fs.attr('id') === 'section-detail' && typeRegister === '1' ) {
		$('#background02').removeClass('show');
		$('#background03').addClass('show');
	} else if (next_fs.attr('id') === 'section-detail' && typeRegister === '2') {
		$('#background02').removeClass('show');
		$('#background04').addClass('show');
	} else if (next_fs.attr('id') === 'section-detail') {
		$('#background02').removeClass('show');
		$('#background05').addClass('show');
	}
	
	//activate next step on progressbar using the index of next_fs
	$("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");
	
	//show the next fieldset
	next_fs.show(); 
	//hide the current fieldset with style
	current_fs.animate({opacity: 0}, {
		step: function(now, mx) {
			//as the opacity of current_fs reduces to 0 - stored in "now"
			//1. scale current_fs down to 80%
			scale = 1 - (1 - now) * 0.2;
			//2. bring next_fs from the right(50%)
			left = (now * 50)+"%";
			//3. increase opacity of next_fs to 1 as it moves in
			opacity = 1 - now;
			current_fs.css({
        'transform': 'scale('+scale+')',
        'position': 'absolute'
      });
			next_fs.css({'left': left, 'opacity': opacity});
		}, 
		duration: 800, 
		complete: function(){
			current_fs.hide();
			animating = false;
		}, 
		//this comes from the custom easing plugin
		easing: 'easeInOutBack'
	});
});

$(".previous").click(function(){
	if(animating) return false;
	animating = true;
	
	current_fs = $(this).parent().parent();
	previous_fs = $(this).parent().parent().prev();
	

	// change image.
	if (previous_fs.attr('id') === 'section-form') {
		$('#background03').removeClass('show');
		$('#background04').removeClass('show');
		$('#background05').removeClass('show');
		$('#background02').addClass('show');
	}

	if (previous_fs.attr('id') === 'section-info') {
		$('#background02').removeClass('show');
		$('#background01').addClass('show');
	}

	//de-activate current step on progressbar
	$("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");
		
	
	//show the previous fieldset
	previous_fs.show(); 
	//hide the current fieldset with style
	current_fs.animate({opacity: 0}, {
		step: function(now, mx) {
			//as the opacity of current_fs reduces to 0 - stored in "now"
			//1. scale previous_fs from 80% to 100%
			scale = 0.8 + (1 - now) * 0.2;
			//2. take current_fs to the right(50%) - from 0%
			left = ((1-now) * 50)+"%";
			//3. increase opacity of previous_fs to 1 as it moves in
			opacity = 1 - now;
			current_fs.css({'left': left});
			previous_fs.css({'transform': 'scale('+scale+')', 'opacity': opacity});
		}, 
		duration: 800, 
		complete: function(){
			current_fs.hide();
			animating = false;
		}, 
		//this comes from the custom easing plugin
		easing: 'easeInOutBack'
	});
});

$(".submit").click(function(e){	
	Swal.fire({
		type: 'success',
		title: 'Chúc mừng bạn đã hoàn thành Bản Đăng Ký !',
		showConfirmButton: false,
		html:
    'Chúng tôi sẽ liên lạc với bạn trong thời gian ngắn nhất' +
    '<p>liên hệ : <a href="http://vci.edu.vn">vci.edu.vn/</a></p>',
	})
	return true;
});

$(document).ready(function(){
   var $form = $('form');
   $form.submit(function(){
      $.post($(this).attr('action'), $(this).serialize(), function(response){
            // do something here on success
      },'json');
      return false;
   });
});


$("#btn-formal").click(function(){
	$(this).addClass("selected");
	// bỏ selected.
	$("#btn-studyAbroad").removeClass("selected");
	$("#btn-connect").removeClass("selected");

	$("#studyAbroad").css({'display': 'none'});
	$("#connect").css({'display': 'none'});
	$("#formal").css({'display': 'block'})

	$("#type").val("1");
});

$("#btn-connect").click(function(){
	$(this).addClass("selected");

	// Bỏ selected.
	$("#btn-studyAbroad").removeClass("selected");
	$("#btn-formal").removeClass("selected");

	// Chỉnh view
	$("#formal").css({'display': 'none'})
	$("#studyAbroad").css({'display': 'none'});
	$("#connect").css({'display': 'block'})

	$("#type").val("2");
});

$("#btn-studyAbroad").click(function(){
	$(this).addClass("selected");

	// bỏ selected.
	$("#btn-formal").removeClass("selected");
	$("#btn-connect").removeClass("selected");
	
	// Chỉnh view
	$("#formal").css({'display': 'none'})
	$("#connect").css({'display': 'none'});
	$("#studyAbroad").css({'display': 'block'})

	$("#type").val("3");
});

