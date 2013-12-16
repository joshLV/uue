<%@page import="org.springframework.security.core.userdetails.*"%>
<%@page import="org.springframework.security.core.*"%>
<%@page import="org.springframework.security.web.*"%>
<%@page import="org.springframework.security.authentication.*"%>
<%@page import="lab.s2jh.core.web.captcha.BadCaptchaException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/page-header.jsp"%>
<%@ include file="/common/index-header.jsp"%>
<title>Uue</title>
<link href="http://s1.pan.bdstatic.com/static/images/favicon.ico"
	rel="shortcut icon" type="images/x-icon">
<link href="${base}/resources/login/yun_infocenter_min.css"
	rel="stylesheet" type="text/css">
<style type="text/css"></style>
<script type="text/javascript" charset="UTF-8"
	src="${base}/resources/login/login_tangram_d3453aa0.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${base}/resources/login/saved_resource"></script>
<script type="text/javascript" charset="UTF-8"
	src="${base}/resources/login/jsapi"></script>
<script async="" src="${base}/resources/login/all.js" charset="utf-8"></script>
<script async="" src="${base}/resources/login/osavailable"
	charset="utf-8"></script>
</head>
<body ryt13675="1">
	<div id="login-body" style="width: 100%">
		<div class="header-container">
			<div class="clearfix" id="login-header">
				<div class="logo">
					<a class="yun-logo" href="http://yun.baidu.com/" target="_blank"
						title="Uue">Uue</a> <a class="pan-logo"
						href="${base}/resources/login/百度云 网盘-自由存，随心享.htm" title="网盘">网盘</a>
				</div>
				
			</div>
			<div id="login-middle">
				<div class="img-content">
					<a hidefocus="true" href="javascript:;"> <img
						class="index-slide-img current"
						src="${base}/resources/login/2t.jpg"
						style="opacity: 0.13031244102553594;">
					</a> <a hidefocus="true" href="javascript:;"> <img
						class="index-slide-img"
						src="${base}/resources/login/multiterminal.jpg"
						style="opacity: 0.1;">
					</a> <a hidefocus="true" href="javascript:;"> <img
						class="index-slide-img" src="${base}/resources/login/freeshare.jpg"
						style="opacity: 0.3;">
					</a>
				</div>
				<div class="yunbg">
					<ul class="focus-content clearfix">
						<li class="focus-content-item"><a
							class="focus-anchor current" hidefocus="true" href="javascript:;"
							idx="0"></a></li>
						<li class="focus-content-item"><a class="focus-anchor"
							hidefocus="true" href="javascript:;" idx="1"></a></li>
						<li class="focus-content-item"><a class="focus-anchor"
							hidefocus="true" href="javascript:;" idx="2"></a></li>
					</ul>
				</div>
				<div class="header-login">
					<ul class="login-tab">
						<li class="tab-item tab-item-left tab-selected"><a class="on"
							hidefocus="true" href="http://pan.baidu.com/#"
							onclick="return false">普通登录</a></li>
						<li class="tab-item tab-item-right"><a hidefocus="true"
							href="http://pan.baidu.com/#" onclick="return false">手机登录</a></li>
					</ul>
					<div class="tang-pass-login" id="netdisk_pass_login_form">
						<form id="TANGRAM__PSP_4__form" class="pass-form" method="POST">
							<p id="TANGRAM__PSP_4__errorWrapper"
								class="pass-generalErrorWrapper">
								<span id="TANGRAM__PSP_4__error"
									class="pass-generalError pass-generalError-error"></span>
							</p>
							<p id="TANGRAM__PSP_4__hiddenFields" style="display: none">
								<input type="hidden" id="TANGRAM__PSP_4__codeString"
									name="codeString" value=""><input type="hidden"
									id="TANGRAM__PSP_4__safeFlag" name="safeFlag" value="0"><input
									type="hidden" id="TANGRAM__PSP_4__u" name="u"
									value="http://pan.baidu.com/"><input type="hidden"
									id="TANGRAM__PSP_4__isPhone" name="isPhone" value="false"><input
									type="hidden" id="TANGRAM__PSP_4__staticPage" name="staticPage"
									value="http://pan.baidu.com/res/static/thirdparty/pass_v3_jump.html"><input
									type="hidden" id="TANGRAM__PSP_4__quick_user" name="quick_user"
									value="0"><input type="hidden"
									id="TANGRAM__PSP_4__logintype" name="logintype"
									value="basicLogin">
							</p>
							<p id="TANGRAM__PSP_4__userNameWrapper"
								class="pass-form-item pass-form-item-userName"
								style="display: block">
								<label for="TANGRAM__PSP_4__userName"
									id="TANGRAM__PSP_4__userNameLabel"
									class="pass-label pass-label-userName">邮箱</label><input
									id="TANGRAM__PSP_4__userName" type="text" name="userName"
									class="pass-text-input pass-text-input-userName"
									autocomplete="off"><span
									id="TANGRAM__PSP_4__userNameTip"
									class="pass-item-tip pass-item-tip-userName"
									style="display: none"><span
									id="TANGRAM__PSP_4__userNameTipText"
									class="pass-item-tiptext pass-item-tiptext-userName"></span></span>
							</p>
							<p id="TANGRAM__PSP_4__passwordWrapper"
								class="pass-form-item pass-form-item-password"
								style="display: block">
								<label for="TANGRAM__PSP_4__password"
									id="TANGRAM__PSP_4__passwordLabel"
									class="pass-label pass-label-password">密码</label><input
									id="TANGRAM__PSP_4__password" type="password" name="password"
									class="pass-text-input pass-text-input-password"><span
									id="TANGRAM__PSP_4__passwordTip"
									class="pass-item-tip pass-item-tip-password"
									style="display: none"><span
									id="TANGRAM__PSP_4__passwordTipText"
									class="pass-item-tiptext pass-item-tiptext-password"></span></span>
							</p>
							<p id="TANGRAM__PSP_4__verifyCodeImgWrapper"
								class="pass-form-item pass-form-item-verifyCode"
								style="display: none">
								<label for="TANGRAM__PSP_4__verifyCode"
									id="TANGRAM__PSP_4__verifyCodeLabel"
									class="pass-label pass-label-verifyCode">验证码</label><input
									id="TANGRAM__PSP_4__verifyCode" type="text" name="verifyCode"
									class="pass-text-input pass-text-input-verifyCode"
									maxlength="6"><span
									id="TANGRAM__PSP_4__verifyCodeImgParent"
									class="pass-verifyCodeImgParent"><img
									id="TANGRAM__PSP_4__verifyCodeImg" class="pass-verifyCode"
									src="${base}/resources/login/small_blank_9dbbfbb1.gif"></span><a
									id="TANGRAM__PSP_4__verifyCodeChange"
									href="#" class="pass-change-verifyCode">换一张</a><span
									id="TANGRAM__PSP_4__verifyCodeError"
									class="pass-error pass-error-verifyCode"></span><span
									id="TANGRAM__PSP_4__verifyCodeTip"
									class="pass-tip pass-tip-verifyCode"></span>
							</p>
							<p id="TANGRAM__PSP_4__memberPassWrapper"
								class="pass-form-item pass-form-item-memberPass">
								<input id="TANGRAM__PSP_4__memberPass" type="checkbox"
									name="memberPass"
									class="pass-checkbox-input pass-checkbox-memberPass"
									checked="checked"><label
									for="TANGRAM__PSP_4__memberPass"
									id="TANGRAM__PSP_4__memberPassLabel" class="">下次自动登录</label>
							</p>
							<p id="TANGRAM__PSP_4__submitWrapper"
								class="pass-form-item pass-form-item-submit">
								<input id="TANGRAM__PSP_4__submit" type="submit" value="登录"
									class="pass-button pass-button-submit"><a
									class="pass-reglink"
									href="#"
									target="_blank" style="display: none">立即注册</a><a
									class="pass-fgtpwd"
									href="#"
									target="_blank">忘记密码？</a>
							</p>
						</form>
					</div>
					<div id="pass-phoenix-login" class="tang-pass-login-phoenix">
						<div class="pass-phoenix-title">
							可以使用以下方式登录<span class="pass-phoenix-note"></span>
						</div>
						<div id="pass-phoenix-list-login" class="pass-phoenix-list">
							<div class="pass-phoenix-btn" id="pass_phoenix_btn">
								<ul class="bd-acc-list">
									<li class="bd-acc-tsina" data-dialog="1" data-acc="2"
										data-height="669" data-width="800" title="新浪微博">新浪微博</li>
									<li class="bd-acc-qzone" data-dialog="1" data-acc="15"
										data-height="450" data-width="750" title="QQ帐号">QQ帐号</li>
									<li class="bd-acc-renren" data-dialog="1" data-acc="1"
										data-height="356" data-width="600" title="人人网">人人网</li>
								</ul>
							</div>
						</div>
						<div class="clear"></div>
					</div>
					<div class="login-info">
						<p id="login-create">没有百度帐号？</p>
						<a class="link-create" hidefocus="true"
							href="#"
							id="pageSignupCtrl" target="_blank"></a>
					</div>
				</div>
			</div>
			<div id="login-download">
				<ul class="tab-download clearfix" id="tab-download">
					<li><a class="windows" hidefocus="true"
						href="#"
						id="windows-download">Windows</a></li>
					<li><a class="android" hidefocus="true"
						href="#"
						id="android-download">Android</a></li>
					<li><a class="iphone" hidefocus="true"
						href="#"
						id="iphone-download" target="_blank">iPhone</a></li>
					<li><a class="ipad" hidefocus="true"
						href="#"
						id="ipad-download" target="_blank">iPad</a></li>
					<li><a class="wphone" hidefocus="true"
						href="#"
						id="wphone-download" target="_blank">WPhone</a></li>
					<li><a class="tongbupan" hidefocus="true"
						href="#" id="tongbupan-download"
						target="_blank">同步盘</a></li>
					<li><a class="two-dimension-code" hidefocus="true"
						href="javascript:;">二维码</a></li>
				</ul>
			</div>
		</div>
		<div class="footer">
			<div xmlns="#">
				©2013 Baidu <a class="b-lnk-gy"
					href="#"
					target="_blank">开发者中心</a> | <a class="b-lnk-gy"
					href="#" target="_blank">服务协议</a> | <a
					class="b-lnk-gy" href="#"
					target="_blank">权利声明</a> | <a class="b-lnk-gy"
					href="#" target="_blank">版本更新</a> |
				<a class="b-lnk-gy" href="#"
					target="_blank">帮助中心</a> | <a class="b-lnk-gy"
					href="#" target="_blank">问题反馈</a>
			</div>
		</div>
	</div>
	<div style="display: none">
		<script type="text/javascript">
			var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://"
					: " http://");
			document
					.write(unescape("%3Cscript src='"
							+ _bdhmProtocol
							+ "hm.baidu.com/h.js%3F773fea2ac036979ebb5fcc768d8beb67' type='text/javascript'%3E%3C/script%3E"));
			document
					.write(unescape("%3Cscript src='"
							+ _bdhmProtocol
							+ "hm.baidu.com/h.js%3Fb181fb73f90936ebd334d457c848c8b5' type='text/javascript'%3E%3C/script%3E"));
		</script>
		<script src="${base}/resources/login/h.js" type="text/javascript"></script>
		<a
			href="#"
			target="_blank"></a>
	</div>
	<script src="${base}/resources/login/infocenter-all-min.js"
		type="text/javascript"></script>

</body>
</html>