//**************************************************分页管理******************************************************
function showPages(name) { //初始化属性
	this.name = name;      //对象名称
	this.page = 1;         //当前页数
	this.pageCount = 1;    //总页数
	this.argName = 'page'; //参数名
	this.showTimes = 1;    //打印次数
	this.showDiv ;
	this.url = "";
	this.args = "";
	this.formSubmit = "";
}
 
showPages.prototype.getPage = function(){ //丛url获得当前页数,如果变量重复只获取最后一个
	var args = location.search;
	var reg = new RegExp('[\?&]?' + this.argName + '=([^&]*)[&$]?', 'gi');
	var chk = args.match(reg);
	this.page = RegExp.$1;
}
showPages.prototype.checkPages = function(){ //进行当前页数和总页数的验证
	if (isNaN(parseInt(this.page))) this.page = 1;
	if (isNaN(parseInt(this.pageCount))) this.pageCount = 1;
	if (this.page < 1) this.page = 1;
	if (this.pageCount < 1) this.pageCount = 1;
	if (this.page > this.pageCount) this.page = this.pageCount;
	this.page = parseInt(this.page);
	this.pageCount = parseInt(this.pageCount);
}
showPages.prototype.createHtml = function(mode){ //生成html代码
	var strHtml = '', prevPage = this.page - 1, nextPage = this.page + 1;
	if (mode == '' || typeof(mode) == 'undefined') mode = 0;
	switch (mode) {
		case 0 : //模式1 (页数,首页,前页,后页,尾页)
			strHtml += '<span class="count">Pages: ' + this.page + ' / ' + this.pageCount + '</span>';
			strHtml += '<span class="number">';
			if (prevPage < 1) {
				strHtml += '<span title="First Page">&#171;</span>';
				strHtml += '<span title="Prev Page">&#139;</span>';
			} else {
				strHtml += '<span title="First Page"><a href="javascript:' + this.name + '.toPage(1);">&#171;</a></span>';
				strHtml += '<span title="Prev Page"><a href="javascript:' + this.name + '.toPage(' + prevPage + ');">&#139;</a></span>';
			}
			for (var i = 1; i <= this.pageCount; i++) {
				if (i > 0) {
					if (i == this.page) {
						strHtml += '<span title="Page ' + i + '">[' + i + ']</span>';
					} else {
						strHtml += '<span title="Page ' + i + '"><a href="javascript:' + this.name + '.toPage(' + i + ');">[' + i + ']</a></span>';
					}
				}
			}
			if (nextPage > this.pageCount) {
				strHtml += '<span title="Next Page">&#155;</span>';
				strHtml += '<span title="Last Page">&#187;</span>';
			} else {
				strHtml += '<span title="Next Page"><a href="javascript:' + this.name + '.toPage(' + nextPage + ');">&#155;</a></span>';
				strHtml += '<span title="Last Page"><a href="javascript:' + this.name + '.toPage(' + this.pageCount + ');">&#187;</a></span>';
			}
			strHtml += '</span><br />';
			break;
		case 1 : //模式1 (10页缩略,首页,前页,后页,尾页)
			strHtml += '<span class="count">Pages: ' + this.page + ' / ' + this.pageCount + '</span>';
			strHtml += '<span class="number">';
			if (prevPage < 1) {
				strHtml += '<span title="First Page">&#171;</span>';
				strHtml += '<span title="Prev Page">&#139;</span>';
			} else {
				strHtml += '<span title="First Page"><a href="javascript:' + this.name + '.toPage(1);">&#171;</a></span>';
				strHtml += '<span title="Prev Page"><a href="javascript:' + this.name + '.toPage(' + prevPage + ');">&#139;</a></span>';
			}
			if (this.page % 10 ==0) {
				var startPage = this.page - 9;
			} else {
				var startPage = this.page - this.page % 10 + 1;
			}
			if (startPage > 10) strHtml += '<span title="Prev 10 Pages"><a href="javascript:' + this.name + '.toPage(' + (startPage - 1) + ');">...</a></span>';
			for (var i = startPage; i < startPage + 10; i++) {
				if (i > this.pageCount) break;
				if (i == this.page) {
					strHtml += '<span title="Page ' + i + '">[' + i + ']</span>';
				} else {
					strHtml += '<span title="Page ' + i + '"><a href="javascript:' + this.name + '.toPage(' + i + ');">[' + i + ']</a></span>';
				}
			}
			if (this.pageCount >= startPage + 10) strHtml += '<span title="Next 10 Pages"><a href="javascript:' + this.name + '.toPage(' + (startPage + 10) + ');">...</a></span>';
			if (nextPage > this.pageCount) {
				strHtml += '<span title="Next Page">&#155;</span>';
				strHtml += '<span title="Last Page">&#187;</span>';
			} else {
				strHtml += '<span title="Next Page"><a href="javascript:' + this.name + '.toPage(' + nextPage + ');">&#155;</a></span>';
				strHtml += '<span title="Last Page"><a href="javascript:' + this.name + '.toPage(' + this.pageCount + ');">&#187;</a></span>';
			}
			strHtml += '</span><br />';
			break;
		case 2 : //模式2 (前后缩略,页数,首页,前页,后页,尾页)
			//strHtml += '<span class="count">Pages: ' + this.page + ' / ' + this.pageCount + '</span>';
			strHtml += '<span class="number" style="">';
			if (prevPage < 1) {
				strHtml += '<a class="this none"><span title="首  页" >首&nbsp;&nbsp;页</span></a>';
				strHtml += '<a class="this none"><span title="上一页">上一页</span></a>';
			} else {
				strHtml += '<a class="this" href="javascript:' + this.name + '.toPage(1);"><span title="首  页" >首&nbsp;&nbsp;页</span></a>';
				strHtml += '<a class="this" href="javascript:' + this.name + '.toPage(' + prevPage + ');"><span title="上一页" >上一页</span></a>';
			}
			if (this.page != 1) strHtml += '<a class="this" href="javascript:' + this.name + '.toPage(1);"><span >1</span></a>';
			if (this.page >= 5) strHtml += '<span>...</span>';
			if (this.pageCount > this.page + 2) {
				var endPage = this.page + 2;
			} else {
				var endPage = this.pageCount;
			}
			for (var i = this.page - 2; i <= endPage; i++) {
				if (i > 0) {
					if (i == this.page) {
						strHtml += '<a class="this selected"><span>' + i + '</span></a>';
					} else {
						if (i != 1 && i != this.pageCount) {
							strHtml += '<a class="this" href="javascript:' + this.name + '.toPage(' + i + ');"><span>' + i + '</span></a>';
						}
					}
				}
			}
			if (this.page + 3 < this.pageCount) strHtml += '<span>...</span>';
			if (this.page != this.pageCount) strHtml += '<a class="this" href="javascript:' + this.name + '.toPage(' + this.pageCount + ');"><span >' + this.pageCount + '</span></a>';
			if (nextPage > this.pageCount) {
				strHtml += '<a class="this none"><span  title="下一页"  >下一页</span></a>';
				strHtml += '<a class="this none"><span  title="末  页"  >末&nbsp;&nbsp;页</span></a>';
			} else {
				strHtml += '<a class="this" href="javascript:' + this.name + '.toPage(' + nextPage + ');"><span title="下一页"  > 下一页 </span></a>';
				strHtml += '<a class="this" href="javascript:' + this.name + '.toPage(' + this.pageCount + ');"><span title="末  页"  > 末&nbsp;&nbsp;页 </span></a>';
			}
			strHtml += '</span><br />';
			break;
		case 3 : //模式3 (箭头样式,首页,前页,后页,尾页) (only IE)
			strHtml += '<span class="count">Pages: ' + this.page + ' / ' + this.pageCount + '</span>';
			strHtml += '<span class="arrow">';
			if (prevPage < 1) {
				strHtml += '<span title="First Page">9</span>';
				strHtml += '<span title="Prev Page">7</span>';
			} else {
				strHtml += '<span title="First Page"><a href="javascript:' + this.name + '.toPage(1);">9</a></span>';
				strHtml += '<span title="Prev Page"><a href="javascript:' + this.name + '.toPage(' + prevPage + ');">7</a></span>';
			}
			if (nextPage > this.pageCount) {
				strHtml += '<span title="Next Page">8</span>';
				strHtml += '<span title="Last Page">:</span>';
			} else {
				strHtml += '<span title="Next Page"><a href="javascript:' + this.name + '.toPage(' + nextPage + ');">8</a></span>';
				strHtml += '<span title="Last Page"><a href="javascript:' + this.name + '.toPage(' + this.pageCount + ');">:</a></span>';
			}
			strHtml += '</span><br />';
			break;
		case 4 : //模式4 (下拉框)
			if (this.pageCount < 1) {
				strHtml += '<select name="toPage" disabled>';
				strHtml += '<option value="0">No Pages</option>';
			} else {
				var chkSelect;
				strHtml += '<select name="toPage" onchange="' + this.name + '.toPage(this);">';
				for (var i = 1; i <= this.pageCount; i++) {
					if (this.page == i) chkSelect=' selected="selected"';
					else chkSelect='';
					strHtml += '<option value="' + i + '"' + chkSelect + '>Pages: ' + i + ' / ' + this.pageCount + '</option>';
				}
			}
			strHtml += '</select>';
			break;
		case 5 : //模式5 (输入框)
			strHtml += '<span class="input">';
			if (this.pageCount < 1) {
				strHtml += '<input type="text" name="toPage" value="No Pages" class="itext" disabled="disabled">';
				strHtml += '<input type="button" name="go" value="GO" class="ibutton" disabled="disabled"></option>';
			} else {
				strHtml += '<input type="text" value="Input Page:" class="ititle" readonly="readonly">';
				strHtml += '<input type="text" id="pageInput' + this.showTimes + '" value="' + this.page + '" class="itext" title="Input page" onkeypress="return ' + this.name + '.formatInputPage(event);" onfocus="this.select()">';
				strHtml += '<input type="text" value=" / ' + this.pageCount + '" class="icount" readonly="readonly">';
				strHtml += '<input type="button" name="go" value="GO" class="ibutton" onclick="' + this.name + '.toPage(document.getElementById(\'pageInput' + this.showTimes + '\').value);"></option>';
			}
			strHtml += '</span>';
			break;
		default :
			strHtml = 'Javascript showPage Error: not find mode ' + mode;
			break;
	}
	return strHtml;
}
showPages.prototype.createUrl = function (page) { //生成页面跳转url
	if (isNaN(parseInt(page))) page = 1;
	if (page < 1) page = 1;
	if (page > this.pageCount) page = this.pageCount;
//	this.url = "./library/action/toViewCommentList?";
//	this.args = "&questionId="+document.getElementById("questionId").value;
	var reg = new RegExp('([\?&]?)' + this.argName + '=[^&]*[&$]?', 'gi');
	this.args =this.args.replace(reg,'$1');
	if (this.args == '' || this.args == null) {
		this.args += '?' + this.argName + '=' + page;
	} else if (this.args.substr(this.args.length - 1,1) == '?' || this.args.substr(this.args.length - 1,1) == '&') {
			this.args += this.argName + '=' + page;
	} else {
			this.args += '&' + this.argName + '=' + page;
	}
	this.page = page;
	document.getElementById('pages_' + this.name + '_' + this.showTimes).innerHTML = this.createHtml(2);
	this.formSubmit();
}
showPages.prototype.toPage = function(page){ //页面跳转
	var turnTo = 1;
	if (typeof(page) == 'object') {
		turnTo = page.options[page.selectedIndex].value;
	} else {
		turnTo = page;
	}
	this.createUrl(turnTo);
}
showPages.prototype.printHtml = function(mode){ //显示html代码
	this.getPage();
	this.checkPages();
	this.showTimes += 1;
	this.showDiv.innerHTML= '<div style="float:right;border:0px solid blue;" id="pages_' + this.name + '_' + this.showTimes + '" class="pages"></div>';
	document.getElementById('pages_' + this.name + '_' + this.showTimes).innerHTML = this.createHtml(mode);
}
showPages.prototype.formatInputPage = function(e){ //限定输入页数格式
	var ie = navigator.appName=="Microsoft Internet Explorer"?true:false;
	if(!ie) var key = e.which;
	else var key = event.keyCode;
	if (key == 8 || key == 46 || (key >= 48 && key <= 57)) return true;
	return false;
}
