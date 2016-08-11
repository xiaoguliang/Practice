var me=true;
var over=false;
var chessBox=[];
//赢法数组wins[][][]
var wins=[];
// 赢法的统计数组一维
var myWin=[];
var computerWin=[];
// 初始化赢法数组
for (var i = 0; i < 15; i++) {
	wins[i]=[];
	for (var j = 0; j < 15; j++) {
		wins[i][j]=[];
}
}
// 初始化棋盘数组
for (var i = 0; i < 15; i++) {
	chessBox[i]=[];
	for (var j = 0; j <15 ; j++) {
		chessBox[i][j]=0;
	}
}
// 初始化统计数组

var count=0;
//zhixian
for (var i = 0; i < 15; i++) {
	for(var j=0;j<11;j++){
		for(var k=0;k<5;k++){
			wins[i][j+k][count]=true;
		}
		count++;
	}
}
//shuxian
for (var i = 0; i < 15; i++) {
	for(var j=0;j<11;j++){
		for(var k=0;k<5;k++){
			wins[j+k][i][count]=true;
		}
		count++;
	}
}
//xiexian
for (var i = 0; i < 11; i++) {
	for(var j=0;j<11;j++){
		for(var k=0;k<5;k++){
			wins[i+k][j+k][count]=true;
		
		}
		count++;
	}
}

//fanxiexian
for (var i = 0; i < 11; i++) {
	for(var j=14;j>3;j--){
		for(var k=0;k<5;k++){
			wins[i+k][j-k][count]=true;
		}
		count++;
	}
}
console.log(count);

for (var i = 0; i <count; i++) {
	myWin[i]=0;
	computerWin[i]=0;
};
var chess=document.getElementById('chess');
var context=chess.getContext("2d");

context.strokeStyle="#BFBFBF";

var logoimg=new Image();
logoimg.src="images//back.jpg";
// logoimg.opacity='0.5';透明度怎么设置
logoimg.onload=function  () {

	context.drawImage(logoimg,0,0,450,450);
	drawchessBoard();
  
	
}
context.drawImage(logoimg,0,0,450,450);
// 绘制棋盘
var drawchessBoard=function(){
	for (var i = 0; i < 15; i++) {
	context.moveTo(15+30*i,15);
	context.lineTo(15+30*i,435);
	context.stroke();
	context.moveTo(15,15+30*i);
	context.lineTo(435,15+30*i);
	context.stroke();
}
}
// 落子的功能
var oneStep=function(i,j,me){
	context.beginPath();
	context.arc(15+30*i,15+30*j,13,0,2*Math.PI);
	context.closePath();
	var gradient=context.createRadialGradient(15+30*i+2,15+30*j-2,13,15+30*i+2,15+30*j-2,0);
	// 黑子
	if(me){
		gradient.addColorStop(0,"#0A0A0A");
	    gradient.addColorStop(1,"#636766");
	}
	// 白子
	else{
		gradient.addColorStop(0,"#D1D1D1");
	    gradient.addColorStop(1,"#F9F9F9");
	}
	
	context.fillStyle=gradient;
	context.fill();
}

var computerAi=function(){
	var myScore=[];
	var computerScore=[];
	var max=0;
	var u=0;
	var v=0;
	for (var i = 0; i <15; i++) {
	  myScore[i]=[];
	  computerScore[i]=[];
	  for(var j=0;j<15;j++){
	  	myScore[i][j]=0;
	  	computerScore[i][j]=0;
	  }
	}
	for(var i=0;i<15;i++){
		for(var j=0;j<15;j++){
			if (chessBox[i][j]==0) {
                for(var k=0;k<count;k++){
                	if (wins[i ][j][k]) {
                         if (myWin[k]==1) {
                         	myScore[i][j]+=200;
                         }else if(myWin[k]==2){
                         	myScore[i][j]+=400;
                         }
                         else if(myWin[k]==3){
                         	myScore[i][j]+=2000;
                         }
                         else if(myWin[k]==4){
                         	myScore[i][j]+=10000;
                         }

                           if (computerWin[k]==1) {
                         	computerScore[i][j]+=220;
                         }else if(computerWin[k]==2){
                         	computerScore[i][j]+=440;
                         }
                         else if(computerWin[k]==3){
                         	computerScore[i][j]+=2200;
                         }
                         else if(computerWin[k]==4){
                         	computerScore[i][j]+=22000;
                         }
                	}
                }
                if (myScore[i][j]>max) {
                	max=myScore[i][j];
                	u=i;
                	v=j;
                }
                else if(myScore[i][j]==max){
                	if (computerScore[i][j]>computerScore[u][v]) {
                		u=i;
                	    v=j;
                	};
                }
                if (computerScore[i][j]>max) {
                	max=computerScore[i][j];
                	u=i;
                	v=j;
                }
                else if(computerScore[i][j]==max){
                	if (myScore[i][j]>myScore[u][v]) {
                		u=i;
                	    v=j;
                	};
                }
			}
		}
	}
	oneStep(u,v,false);
	chessBox[u][v]=2;
    for (var k = 0; k < count; k++) {
   	      if(wins[u][v][k]){
   	      	computerWin[k]++;
   	      	myWin[k]=6;
   	      	if (computerWin[k]==5) {
   	      		window.alert("com[uterwin");
   	      		over=true;
   	      	}
   	      }
   	   }
   	   if (!over) {
   	   	  me=!me;
   	   
   	   };
}
chess.onclick=function(e){
	if (over) {
		return ;
	}
	if(!me){
		return ;
	}
   var i=e.offsetX;
   var j=e.offsetY;
   var x=Math.floor(i/30);
   var y=Math.floor(j/30);
   if (chessBox[x][y]==0) {
   	oneStep(x,y,me);
   chessBox[x][y]=1;
    for (var k = 0; k < count; k++) {
   	      if(wins[x][y][k]){
   	      	myWin[k]++;
   	      	computerWin[k]=6;
   	      	if (myWin[k]==5) {
   	      		window.alert("win");
   	      		over=true;
   	      	}
   	      }
   	   }
   	   if (!over) {
   	   	  me=!me;
   	   	computerAi();
   	   };
   }
}
