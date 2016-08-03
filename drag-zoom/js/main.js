// 思路要点：
// 1.当鼠标按下时，box能随鼠标移动;
//   1.1先要获取鼠标点击的坐标，把鼠标位置变化的距离给box;
// 2.当鼠标松开时，鼠标所在的位置就是box所在的位置（这里涉及鼠标位置的偏移）;
	window.onload = function()
	{
	  DISH = 148;
	  DISW = 118;
	  var sBox = document.getElementById('smallBox');
	  var bBox = document.getElementById('bigBox');
      sBox.onmousedown = function()
      {
      	drag(event);
      }
      bBox.onmousedown = function(event)
      {
      	zoom(event);
      }
	}
	// 拖曳
	function drag(event)
	{
	    var ev = event || window.event;
	    var sBox = document.getElementById('smallBox');
	    var bBox = document.getElementById('bigBox');
	    var disX = ev.clientX - sBox.offsetLeft;
	    var disY = ev.clientY - sBox.offsetTop;
	    document.onmousemove = function(event)
	    {
		    var ev = event || window.event;	    
		    var left = ev.clientX - disX;
		    var top = ev.clientY - disY;
		    sBox.style.left = left + "px";
		    sBox.style.top = top + "px";
		    bBox.style.left = left - DISW + "px";
		    bBox.style.top = top - DISH +"px";
	    }
	    document.onmouseup = function()
	    {
	     	document.onmousemove = null;
	     	document.onmouseup = null;
	    }
	}
//缩放
	function zoom(event)
    {
		var event = event || window.event;
		var bBox = document.getElementById('bigBox');
		var width = bBox.offsetWidth;
		var height = bBox.offsetHeight;
		var posX = event.clientX;
		var posY= event.clientY;
	document.onmousemove = function(event)
	{
	    event = event || window.event;
	    var disX = event.clientX - posX;
        var disY = event.clientY - posY;
        bBox.style.width = width + disX + 'px';
        bBox.style.height = height + disY + 'px';
	}
    document.onmouseup = function()
    {
     document.onmousemove = null;
     document.onmouseup = null;
    }
}