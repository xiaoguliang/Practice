// 思路要点：(动态提示)
// 1.首先要一步步获取文本框里的内容
// 2.然后要进行匹配，匹配成功的存放到数组里
// 3.显示到界面上
$(function()
{
   $(".input").bind("keyup click",function()
   {
   	
    var t = $(this);
   	suggest_so = function(data)
   	{
      // stringify()用于从一个对象解析出字符串JSON.stringify
      // parse用于从一个字符串中解析出json对象JSON.parse
       var x = JSON.stringify(data); var x=eval ("(" + x + ")");
       var abc = x.result;
       var html = "";
       for (var i = 0; i <abc.length; i++) 
       {
          html+="<li>"+abc[i].word+"</li>";
       }
       $("#list").html(html);
       if(t.val() == '')
       {
       	$('#list').hide();
       }
       else
       {
       	$("#list").show();
       }
       if($('#list').html() == '')
       {
        $('#list').hide();
       }
   	};
   $.ajax({
        async:false,
        url:'https://sug.so.360.cn/suggest?callback=suggest_so&encodein=utf-8&encodeout=utf-8&format=json&fields=word,obdata&word='+t.val(),
        dataType:'jsonp',
        // jsonp:"mycallback",
        jsonpCallback:"suggest_so"
      });
   })
   $(document).delegate("#list li","click",function()
   {
    var key = $(this).text();
    location.href = "https://www.baidu.com/s?wd=" + key;
   })
   $(document).delegate("#su","click",function()
   {
    var key = $(".input").val();
    location.href = "https://www.baidu.com/s?wd="+key;
   })
   $(document).click(function()
   {
    $('#list').hide();
   })
});