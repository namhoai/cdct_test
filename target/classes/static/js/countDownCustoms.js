function countDownTimer(date) {
    var elem = $('#timer');
   
   //$( "p" ).add( "div" ).addClass( "widget" );
    var futureTime = new Date(date).getTime();
  
    setInterval(function() { 
      // Time left between future and current time in Seconds
      var timeLeft = Math.floor( (futureTime - new Date().getTime()) / 1000 );
      // console.log(timeLeft);
      
      // Days left = time left / Seconds per Day 
      var days =  Math.floor(timeLeft / 86400);
      // console.log(days);
      
      // 86400 seconds per Day
      timeLeft -= days * 86400;
      // console.log(timeLeft);
      
      // Hours left = time left / Seconds per Hour
      var hours = Math.floor(timeLeft / 3600) % 24;
      // console.log(hours);
  
      // 3600 seconds per Hour
      timeLeft -= hours * 3600;
      // console.log(timeLeft);
      
      // Minutes left = time left / Minutes per Hour
      var min = Math.floor(timeLeft / 60) % 60;
      // console.log(min);
      
      // 60 seconds per minute
      timeLeft -= min * 60;
      // console.log(timeLeft);
      
      // Seconds Left
      var sec = timeLeft % 60;
      
      // Combined DAYS+HOURS+MIN+SEC
      var timeString = "<div class='counter-item'><h4>"+days+" </h4>Ngày</div>"+
                       "<div class='counter-item'><h4>"+hours+"</h4>Giờ</div>"+
                       "<div class='counter-item'><h4>"+min+"</h4>Phút</div>"+
                       "<div class='counter-item'><h4>"+sec+" </h4>Giây</div>";
                       
      elem.html(timeString);
      
    }, 1000);
  }
  
  // Enter date in this format.
  var timer = 'June 22, 2019 09:00:00';
  countDownTimer(timer);
  