// 思路要点：
// 瀑布流布局的特点是等宽不等高
// 1.先要把每个图片的box的位置排好;
//   1.1.获取第一行box(先要确定一行有几列)的top属性，然后查找第一行里面最小的top,把第二行的第一张图片放在他的位置正下方
//   1.2.依次类推把第二行的第二张图片放在第一行里面top次级小的；
// 2.然后给整体加滚动事件，使其能够加载出图片;
//   2.1 先要确定在什么情况下才能加载图片（最后一张图片top加它高度的一半小于滚动距离加可视区域这时候才要加载）
//   2.2 新加载出来的图片资源获取技术支持；
$(function  () {
    waterfall();   
    window.onscroll = scrollEvent;
    
})
function waterfall()
{
	var boxArr = $("#main>.box");
	var boxWidth = boxArr.eq(0).outerWidth();//每个box的宽度
	var rows = Math.floor($(window).width()/boxWidth);//确定每行有几列
	var boxHeight = [];
    boxArr.each(function(index,value)
    {	
    	var boxH = boxArr.eq(index).outerHeight();
    	if (index < rows) 
    	{
                boxHeight.push(boxH); 
    	}	
        else
        {
        	var minH = Math.min.apply(null,boxHeight);
    		var minHIndex = $.inArray(minH,boxHeight);
    		$(value).css({
    			'position':'absolute',
    			'top':minH +'px',
    			'left':boxWidth * minHIndex + 'px'});    		
    		boxHeight[minHIndex] = boxHeight[minHIndex] + boxArr.eq(index).outerHeight();
        }    
    });
}

function isLoad()
{
	var clientH = $(document).height();
	var scrollTop = $(window).scrollTop();	
	var clientHeight = clientH + scrollTop;
	var lastHeight = $("#main>.box").last().get(0).offsetTop + Math.floor($("#main>.box").last().height()/2);
    return (lastHeight < clientHeight ) ? true : false;	
}
function scrollEvent()
{
   var dataInt={'data':[{'src':'1.jpg'},{'src':'2.jpg'},{'src':'3.jpg'},{'src':'4.jpg'}]};
	if(isLoad())
	{
       $.each(dataInt.data, function(index,value){
       	var newBox = $('<div>').addClass('box').appendTo($("#main"));
       	var newPic = $('<div>').addClass('pic').appendTo(newBox);
       	var newImg = $('<img>').attr('src','./images/' +$(value).attr('src')).appendTo(newPic);
       });
       waterfall();
	}
}