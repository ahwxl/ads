$(function(){
		$("#menu ul li").hover(
			   function(){
				   $(this).find("ul").stop().slideDown(100);},
			   function(){
				   $(this).find("ul").stop().slideUp(100);}
		);  
		$("#menu ul li ul").hover(
			   function(){
				  $(this).parent().addClass("HoverClass");},
			   function(){
				  $(this).parent().removeClass("HoverClass");}
		); 
		$('a').focus(function(){
			this.blur();
		});
});

$(function(){
		$("#leftMenu ul li").click(function(){
				   $(this).find("ul").stop().slideDown(300);
				   $(this).addClass("select");
				   $(this).siblings().find("ul").stop().slideUp(300);
				   $(this).siblings().removeClass("select");
		});  
});
//���~����
$(function(){
		$("#productMenu li").hover(
			   function(){
				   $(this).find("ul").stop().slideDown(300);},
			   function(){
				   $(this).find("ul").stop().hide(0);}
		);  
		$("#productMenu li ul").hover(
			   function(){
				  $(this).parents().addClass("HoverClass");},
			   function(){
				  $(this).parents().removeClass("HoverClass");}
		); 
});


$(function(){
		$("#FAQ li").click(function(){
				   $(this).find("div").stop().slideDown(200);
				   $(this).addClass("select");
				   $(this).siblings().find("div").stop().slideUp(200);
				   $(this).siblings().removeClass("select");
		});  
});



$(function(){
		$(".top").click(function(){
			$('html, body').animate({
			scrollTop: $("#header").offset().top
			}, 1000);
		});
	});

$(function(){
		   $(".tableDisplay tr:odd").addClass("");
		   $(".tableDisplay tr:even").addClass("light");
});
$(document).ready(function() {  
      /*$(document).get(0).oncontextmenu = function() {
          return false;
      };*/
  });