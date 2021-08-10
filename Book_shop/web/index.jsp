<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
  <meta charset="utf-8"/>
  <title>最家</title>
  <link rel="stylesheet" type="text/css" href="css/public.css"/>
  <link rel="stylesheet" type="text/css" href="css/index.css"/>
</head>
<body><!------------------------------head------------------------------>
<%@ include file="header.jsp"%>
<!-------------------------banner--------------------------->
<div class="block_home_slider">
  <div id="home_slider" class="flexslider">
    <ul class="slides">
      <li>
        <div class="slide"><img src="img/banner_C.jpg"/></div>
      </li>
      <li>
        <div class="slide"><img src="img/banner_java.jpeg"/></div>
      </li>
    </ul>
  </div>
</div><!------------------------------thImg------------------------------>
<div class="thImg">
  <div class="clearfix"><a href=""><img src="img/C.jpg"/></a><a href=""><img
          src="img/Java.jpg"/></a><a href=""><img src="img/PHP.jpg"/></a></div>
</div><!------------------------------news------------------------------>
<div class="news">
  <div class="wrapper"><h2><img src="img/ih1.jpg"/></h2>
    <div class="top clearfix"><a href="proDetail.html"><img src="img/n1.jpg"/>
      <p></p></a><a href="proDetail.html"><img src="img/n2.jpg"/>
      <p></p></a><a href="proDetail.html"><img src="img/n3.jpg"/>
      <p></p></a></div>
    <div class="bott clearfix"><a href="proDetail.html"><img src="img/n4.jpg"/>
      <p></p></a><a href="proDetail.html"><img src="img/n5.jpg"/>
      <p></p></a><a href="proDetail.html"><img src="img/n6.jpg"/>
      <p></p></a></div>
    <h2><img src="img/ih2.jpg"/></h2>
    <div class="flower clearfix tran">
      <c:forEach var="product" items="${list}">
      <a href="productDetail?id=${product.PRODUCT_ID}&cid=${product.PRODUCT_CID}" class="clearfix">
      <dl style="width: 356px">
        <dt><span class="abl"></span><img width="356px" height="356px" src="image/product/${product.PRODUCT_FILENAME}"/><span class="abr"></span></dt>
        <dd>【JAVA】${product.PRODUCT_NAME}</dd>
        <dd><span>¥ ${product.PRODUCT_PRICE}</span></dd>
      </dl>
      </a></c:forEach>
      
    </div>
    
  </div>
</div><!------------------------------ad------------------------------><a href="#" class="ad"><img
        src="img/ib1.jpg"/></a><!------------------------------people------------------------------>
<div class="people">
  <div class="wrapper"><h2><img src="img/ih3.jpg"/></h2>
    <div class="pList clearfix tran">
      <c:forEach var="product_C" items="${list_c}">
      <a href="productDetail?id=${product_C.PRODUCT_ID}&cid=${product_C.PRODUCT_CID}">
        <dl style="width: 268px">
          <dt><span class="abl"></span><img width="268px" height="268px" src="image/product/${product_C.PRODUCT_FILENAME}"/><span class="abr"></span></dt>
          <dd>【C语言】${product_C.PRODUCT_NAME}</dd>
          <dd><span>￥${product_C.PRODUCT_PRICE}</span></dd>
        </dl>
      </a>
      </c:forEach>
    </div>
    
    
  </div>
</div><!--返回顶部-->
<div class="gotop"><a href="cartShow">
  <dl>
    <dt><img src="img/gt1.png"/></dt>
    <dd>去购<br/>物车</dd>
  </dl>
</a><a href="#" class="dh">
  <dl>
    <dt><img src="img/gt2.png"/></dt>
    <dd>联系<br/>客服</dd>
  </dl>
</a><a href="mygxin.jsp">
  <dl>
    <dt><img src="img/gt3.png"/></dt>
    <dd>个人<br/>中心</dd>
  </dl>
</a><a href="#" class="toptop" style="display: none">
  <dl>
    <dt><img src="img/gt4.png"/></dt>
    <dd>返回<br/>顶部</dd>
  </dl>
</a>
  <p>400-800-8200</p></div><!-------------------login--------------------------><!--footer-->
<div class="footer">
  <div class="top">
    <div class="wrapper">
      <div class="clearfix"><a href="#2" class="fl"><img src="img/foot1.png"/></a><span class="fl">7天无理由退货</span>
      </div>
      <div class="clearfix"><a href="#2" class="fl"><img src="img/foot2.png"/></a><span class="fl">15天免费换货</span>
      </div>
      <div class="clearfix"><a href="#2" class="fl"><img src="img/foot3.png"/></a><span class="fl">满599包邮</span>
      </div>
      <div class="clearfix"><a href="#2" class="fl"><img src="img/foot4.png"/></a><span class="fl">手机特色服务</span>
      </div>
    </div>
  </div>
  <p class="dibu">最家家居&copy;2013-2017公司版权所有 京ICP备080100-44备0000111000号<br/>
    违法和不良信息举报电话：400-800-8200，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p></div>
<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="js/nav.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery.flexslider-min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">$(function () {
  $('#home_slider').flexslider({
    animation: 'slide',
    controlNav: true,
    directionNav: true,
    animationLoop: true,
    slideshow: true,
    slideshowSpeed: 2000,
    useCSS: false
  });
});</script>
</body>
</html>