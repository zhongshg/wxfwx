<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- saved from url=(0081)https://v.qq.com/iframe/player.html?width=670&height=502.5&auto=0&vid=p0342l590zk -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>腾讯视频</title>
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<script type="text/javascript" src="js/zepto.js"></script>
<script type="text/javascript" src="http://imgcache.qq.com/tencentvideo_v1/tvp/js/tvp.player_v2_zepto.js"></script>
</head>
<body>
	<div id="mod_player" class="mod_player" style="width:100%; height:250px;"></div>
	<script>
		var player_content = $('.mod_player'), $b, cid = 'mod_player'
				+ (+new Date);
		player_content.attr('id', cid);
		var video = new tvp.VideoInfo();
		window.player = new tvp.Player();
		video.setVid("${param.vid }");
		var config = {
			width : '100%',
			height : '250px',
			video : video,
			modId : cid,
			isHtml5UseFakeFullScreen : false,
			vodFlashSkin : 'http://imgcache.qq.com/minivideo_v1/vd/res/skins/TencentPlayerMiniSkin.swf',
			autoplay : true, //自动播放选项
			quality : 'high',//视频画质，但经测试貌似不管用
			isShowDurationLimit : false
		}
		window.player.create(config);
	</script>
</body>
</html>