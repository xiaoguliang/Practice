window.onload = function()
{
	waterfall("main","box");
	window.onscroll = scrollEvent;
}
// 思路要点：
// 瀑布流布局的特点是等宽不等高
// 1.先要把每个图片的box的位置排好;
//   1.1.获取第一行box(先要确定一行有几列)的top属性，然后查找第一行里面最小的top,把第二行的第一张图片放在他的位置正下方
//   1.2.依次类推把第二行的第二张图片放在第一行里面top次级小的；
// 2.然后给整体加滚动事件，使其能够加载出图片;
//   2.1 先要确定在什么情况下才能加载图片（最后一张图片top加它高度的一半小于滚动距离加可视区域这时候才要加载）
//   2.2 新加载出来的图片资源获取技术支持；
function waterfall(parent,className)
{
	var boxArr = getElementsClassName(parent,className);
    var boxWidth = boxArr[0].offsetWidth;//每个块的宽度
    var clientWith = document.documentElement.clientWidth || document.body.clientWidth;//可视区域的宽度
    var rows = Math.floor(clientWith/boxWidth);//确定一行有几列
    var boxHeight = [];//用来存放每列的高度
    for (var i = 0; i < boxArr.length; i++) //如何选前五个--------用一个数组来存放
    {
    	if (i < rows)
    	{
    		boxHeight.push(boxArr[i].offsetHeight);	
    	}
    	else
    	{
    		var minH = Math.min.apply(null,boxHeight);
            console.log(minH);
    		var index = minHIndex(boxHeight,minH);
            console.log(index);
            boxArr[i].style.cssText = "position:absolute";
            boxArr[i].style.left = boxArr[index].offsetLeft +"px";
            boxArr[i].style.top = minH +"px";
            boxHeight[index] = boxHeight[index] + boxArr[i].offsetHeight;
    	}
    }
}
function getElementsClassName(parent,className)
{
    var parent = parent ? document.getElementById(parent) : document;
    var boxs = new Array();
    var p = parent.getElementsByTagName("*");
    for (var i = 0; i < p.length; i++)
    {
        if(p[i].className == className)
        {
         	boxs.push(p[i]);
        }   
    }
  return boxs;
}
function minHIndex(arr,value)
{
	var index;
	for (var i = 0; i < arr.length; i++) {
		if (arr[i] == value) 
		{
			index = i;
		}
		
	}
	return index;
}
function isLoad()
{
	var boxArr = getElementsClassName("main","box");
	var scrollTop = document.body.scrollTop || document.documentElement.scrollTop;//滚动的高度
	//console.log(scrollTop);
	var clientHeight = document.documentElement.clientHeight || document.body.clientHeight;//可视区域的高度
	var clientH = scrollTop + clientHeight;
	//console.log(clientHeight);
	var lastBoxH = boxArr[boxArr.length-1].offsetTop + Math.floor(boxArr[boxArr.length-1].offsetHeight/2);
	console.log(lastBoxH);
	return lastBoxH < clientH ? true :false;
}
function scrollEvent()
{
	var dataInt={'data':[{'src':'1.jpg'},{'src':'2.jpg'},{'src':'3.jpg'},{'src':'4.jpg'}]};
	if (isLoad()) //加载
		{
           var parent = document.getElementById("main");
           for (var i = 0; i < dataInt.data.length; i++) 
           {
             var newBox = document.createElement("div");
             newBox.className = "box";
             parent.appendChild(newBox);
             var newPic = document.createElement("div");
             newPic.className = "pic";
             newBox.appendChild(newPic);
             var newImg = document.createElement("img");
             newImg.src='images/'+dataInt.data[i].src;
             newPic.appendChild(newImg);
           }
           waterfall("main","box");
		}
}