$(function() {
    setInterval(function() {
      var animationName = 'animated tada';
      var animationend = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
      $('#cd_registration').addClass(animationName).one(animationend, function() {
        $(this).removeClass(animationName);
      });
    }, 3000);
  });
