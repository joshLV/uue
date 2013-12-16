var baidu=baidu||{version:"1.3.8",author:"phoenix"};baidu.phoenix=baidu.phoenix||{};baidu.phoenix.array=baidu.phoenix.array||{};baidu.phoenix.each=baidu.phoenix.array.forEach=baidu.phoenix.array.each=function(g,e,b){var d,f,c,a=g.length;if("function"==typeof e){for(c=0;c<a;c++){f=g[c];d=e.call(b||g,f,c);if(d===false){break}}}return g};baidu.phoenix.array.find=function(e,c){var d,b,a=e.length;if("function"==typeof c){for(b=0;b<a;b++){d=e[b];if(true===c.call(e,d,b)){return d}}}return null};baidu.phoenix.array.indexOf=function(e,b,d){var a=e.length,c=b;d=d|0;if(d<0){d=Math.max(0,a+d)}for(;d<a;d++){if(d in e&&e[d]===b){return d}}return -1};baidu.phoenix.array.remove=function(c,b){var a=c.length;while(a--){if(a in c&&c[a]===b){c.splice(a,1)}}return c};baidu.phoenix.dom=baidu.phoenix.dom||{};baidu.phoenix.dom.g=function(a){if("string"==typeof a||a instanceof String){return document.getElementById(a)}else{if(a&&a.nodeName&&(a.nodeType==1||a.nodeType==9)){return a}}return null};baidu.phoenix.g=baidu.phoenix.G=baidu.phoenix.dom.g;baidu.phoenix.dom.getAncestorByTag=function(b,a){b=baidu.phoenix.dom.g(b);a=a.toUpperCase();while((b=b.parentNode)&&b.nodeType==1){if(b.tagName==a){return b}}return null};baidu.phoenix.event=baidu.phoenix.event||{};baidu.phoenix.event.getTarget=function(a){return a.target||a.srcElement};baidu.phoenix.event._listeners=baidu.phoenix.event._listeners||[];baidu.phoenix.lang=baidu.phoenix.lang||{};baidu.phoenix.lang.isString=function(a){return"[object String]"==Object.prototype.toString.call(a)};baidu.phoenix.isString=baidu.phoenix.lang.isString;baidu.phoenix.dom._g=function(a){if(baidu.phoenix.lang.isString(a)){return document.getElementById(a)}return a};baidu.phoenix._g=baidu.phoenix.dom._g;baidu.phoenix.event.on=function(b,e,g){e=e.replace(/^on/i,"");b=baidu.phoenix.dom._g(b);var f=function(i){g.call(b,i)},a=baidu.phoenix.event._listeners,d=baidu.phoenix.event._eventFilter,h,c=e;e=e.toLowerCase();if(d&&d[e]){h=d[e](b,e,f);c=h.type;f=h.listener}if(b.addEventListener){b.addEventListener(c,f,false)}else{if(b.attachEvent){b.attachEvent("on"+c,f)}}a[a.length]=[b,e,g,f,c];return b};baidu.phoenix.on=baidu.phoenix.event.on;baidu.phoenix.lang.module=function(name,module,owner){var packages=name.split("."),len=packages.length-1,packageName,i=0;if(!owner){try{if(!(new RegExp("^[a-zA-Z_\x24][a-zA-Z0-9_\x24]*\x24")).test(packages[0])){throw""}owner=eval(packages[0]);i=1}catch(e){owner=window}}for(;i<len;i++){packageName=packages[i];if(!owner[packageName]){owner[packageName]={}}owner=owner[packageName]}if(!owner[packages[len]]){owner[packages[len]]=module}};baidu.phoenix.object=baidu.phoenix.object||{};baidu.phoenix.extend=baidu.phoenix.object.extend=function(c,a){for(var b in a){if(a.hasOwnProperty(b)){c[b]=a[b]}}return c};baidu.phoenix.string=baidu.phoenix.string||{};baidu.phoenix.string.decodeHTML=function(a){var b=String(a).replace(/&quot;/g,'"').replace(/&lt;/g,"<").replace(/&gt;/g,">").replace(/&amp;/g,"&");return b.replace(/&#([\d]+);/g,function(d,c){return String.fromCharCode(parseInt(c,10))})};baidu.phoenix.decodeHTML=baidu.phoenix.string.decodeHTML;var baidu=baidu||{};baidu.phoenix=baidu.phoenix||{};(function(){var a=baidu.phoenix;a.log=function(){if(window.console&&window.console.log){console.log(arguments)}};a.config={target:document.body,onAfterRender:function(){},onBindSuccess:function(){return true},onBindFailure:function(){return true},onAfterAuth:function(){return true},tpl:"passport",u:document.location.href,html:[],jumpUrl:null,display:"page",act:"login",openInSameWin:0};if(a._public_config.target&&typeof a._public_config.target=="string"){a._public_config.target=baidu.phoenix.g(a._public_config.target)}baidu.phoenix.extend(a.config,a._public_config)})();baidu.phoenix.lang.module("baidu.phoenix.ui",{popup:function(a,c,l,f){var d="";if(+l){var h=typeof window.screenX!="undefined"?window.screenX:window.screenLeft,e=typeof window.screenY!="undefined"?window.screenY:window.screenTop,m=typeof window.outerWidth!="undefined"?window.outerWidth:document.documentElement.clientWidth,j=typeof window.outerHeight!="undefined"?window.outerHeight:(document.documentElement.clientHeight-22),b=f&&+f.width||400,k=f&&+f.height||300,g=parseInt(h+((m-b)/2),10),i=parseInt(e+((j-k)/2.5),10);if(f.display=="popup"){i=100}d=("width="+b+",height="+k+",left="+g+",top="+i)}return window.open(a,("bd_phoenix_"+c),d)},render:function(a){var d=baidu.phoenix.acc.getConfig();var c=document.createElement("ul");c.className="bd-acc-list";var b="";baidu.phoenix.each(a,function(f){var g=baidu.phoenix.config.html[f];var e=baidu.phoenix.array.find(d,function(h){return h.type==f});b+='<li class="bd-acc-'+f+'" data-dialog="'+ +e.is_dialog+'" data-acc="'+baidu.phoenix.acc.getStatusIdWithName(f)+'" data-height="'+(e.height||0)+'" data-width="'+(e.width||0)+'">';if(!g){b+='<img src="'+e.url+'" />'}else{b+=g}b+="</li>"});c.innerHTML=b;baidu.phoenix.config.target.appendChild(c);baidu.phoenix.on(c,"click",baidu.phoenix.ui.click);baidu.phoenix.config.onAfterRender()},click:function(f){var g=baidu.phoenix.event.getTarget(f);if(g.tagName.toLowerCase()!="li"){g=baidu.phoenix.dom.getAncestorByTag(g,"li")}if(g){var j=g.getAttribute("data-dialog"),c=g.getAttribute("data-acc"),k=g.getAttribute("data-height"),b=g.getAttribute("data-width");var d=baidu.phoenix;var a=d._SERVER_CONFIG.login+"type="+c+"&tpl="+d.config.tpl+"&u="+encodeURIComponent(d.config.u)+"&display="+d.config.display+"&act="+d.config.act;if(d.config.jumpUrl){var i=d.config.jumpUrl+"#display=popup";a=a+"&xd="+encodeURIComponent(i)}if(d.config.onBindFailure&&typeof d.config.onBindFailure=="function"){a=a+"&fire_failure=1"}var h=d.config.openInSameWin?"phoenix":c;baidu.phoenix.ui._active=baidu.phoenix.ui.popup(a,h,j,{width:b,height:k,display:d.config.display})}},_active:null,close:function(){baidu.phoenix.ui._active&&baidu.phoenix.ui._active.close();baidu.phoenix.ui._active=null}});var BD=BD||{};BD.XD=BD.XD||{};BD.XD.postMessage=BD.XD.postMessage||function(b){baidu.phoenix.ui.close();if(b.success==1){var a=decodeURIComponent(b.next);if(baidu.phoenix.config.onAfterAuth(a)){b.isFirstBind=!!+b.isFirstBind;b.osType=b.os_type;if(!baidu.phoenix.config.onBindSuccess(a,b)){return}window.top.document.location.href=a}}else{baidu.phoenix.config.onBindFailure&&baidu.phoenix.config.onBindFailure(b)}};(function(){baidu.phoenix.lang.module("baidu.phoenix.acc",{need_all:false,_ACC_MAP:{"1":"renren","2":"tsina","3":"baidu","4":"tqq","7":"kaixin001","15":"qzone","16":"fetion"},_ICONS_CONFIG:[{type:"tsina",url:"http://passport.baidu.com/phoenix/static/images/ico_sina.png?t=0820170",is_dialog:true,width:800,height:669},{type:"renren",url:"http://passport.baidu.com/phoenix/static/images/ico_renren.png?t=0820170",is_dialog:true,width:600,height:356},{type:"baidu",url:"http://passport.baidu.com/phoenix/static/images/ico_baidu.png?t=0820170",is_dialog:false},{type:"tqq",url:"http://passport.baidu.com/phoenix/static/images/ico_tqq.png?t=0820170",is_dialog:true,width:825,height:633},{type:"kaixin001",url:"http://passport.baidu.com/phoenix/static/images/ico_kaixin001.png?t=0820170",is_dialog:true,width:560,height:376},{type:"qzone",url:"http://passport.baidu.com/phoenix/static/images/ico_qzone.png?t=0820170",is_dialog:true,width:750,height:450},{type:"fetion",url:"http://passport.baidu.com/phoenix/static/images/ico_fetion.png?t=0820170",is_dialog:true,width:560,height:351}],getConfig:function(){return this._ICONS_CONFIG},_getAcc:function(){var c=baidu.phoenix._icons,b=baidu.phoenix._icons_status;if(baidu.phoenix.array.indexOf(c,"*")!=-1){this.need_all=true;baidu.phoenix.array.remove(c,"*")}var a=[];baidu.phoenix.each(c,function(d){if(+b[d]==1){a.push(d)}});if(this.need_all){baidu.phoenix.each(this._ICONS_CONFIG,function(d){d=d.type;if(baidu.phoenix.array.indexOf(c,d)==-1){if(b[d]==1){a.push(d)}}})}return a},_mapStatus:function(){var b=baidu.phoenix._icons_status;if(+b.err_no){return false}b=b.os;var d={};for(var c in b){var a=baidu.phoenix.acc._ACC_MAP[c];d[a]=b[c]}baidu.phoenix._icons_status=d;return true},getStatusIdWithName:function(b){for(var a in this._ACC_MAP){if(this._ACC_MAP[a]==b){return a}}},init:function(){if(baidu.phoenix.acc._mapStatus()){var a=baidu.phoenix.acc._getAcc();baidu.phoenix.ui.render(a)}}})})();